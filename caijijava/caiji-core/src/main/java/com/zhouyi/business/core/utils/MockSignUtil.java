package com.zhouyi.business.core.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Headers;
import okhttp3.Request;


public class MockSignUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockSignUtil.class);
	
	//所有参与签名的header的key
    private static String CA_PROXY_SIGN_HEADERS = "X-Ca-Signature-Headers";
    //签名方式
    private static String SHA256 = "HmacSHA256";
    //HTTP POST
    private static final String HTTP_METHOD_POST = "POST";
    //HTTP PUT
    private static final String HTTP_METHOD_PUT = "PUT";
    private static String LF = "\n";
    private static String MOCK_SECRET="0ce6462fe7c312e2f8cd9c8a7a1538d9";
 
 
    /***
     * 将字符串签名
     * 签名格式 HmacSHA256
     *
     * @param request okHttp Request
     * @return 返回签名后的字符串
     * @throws Exception
     */
    public static String getSign(Request request) {
        String buildToSign = buildStringToSign(request);
        String sign = "";
        try {
            sign = getSign(buildToSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }
    /***
     * 将字符串签名
     * 签名格式 HmacSHA256
     *
     * @param stringToSign 拼接好的字符串
     * @return 返回签名后的字符串
     * @throws Exception
     */
    public static String getSign(String stringToSign) throws Exception {
        Mac hmacSha256 = Mac.getInstance(SHA256);
        byte[] keyBytes = MOCK_SECRET.getBytes("UTF-8");
        hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, SHA256));
        return new String(Base64.encodeBase64(hmacSha256.doFinal(stringToSign.getBytes("UTF-8"))), "UTF-8");
    }
 
    /***
     * 组织等待签名的字符串
     * 按以下规则排序拼接
     *  String stringToSign=HTTPMethod + "\n" +Accept + "\n" + Content-MD5 + "\n"Content-Type + "\n" +Date + "\n" +Headers +Url
     *
     *
     * @param mMethedType 请求的类型
     * @param mAccept Accept头的value
     * @param mContent_Md5  Content-MD5 是指 Body 的 MD5 值，只有当 Body 非 Form 表单时才计算 MD5，
     * @param mContent_Type Content_Type头的value
     * @param mDate Date头的value
     * @param mStringHeaders 参与签名的头
     * @param mUrl Url 指 Path + Query + Body 中 Form 参数，组织方法：对 Query+Form 参数按照字典对 Key 进行排序后按照如下方法拼接，如果 Query 或 Form 参数为空，则 Url = Path，不需要添加 ？，如果某个参数的 Value 为空只保留 Key 参与签名，等号不需要再加入签名。
     * @return
     */
 
    public static String organizationStringToSign(String mMethedType, String mAccept,
                                                  String mContent_Md5,
                                                  String mContent_Type,
                                                  String mDate,
                                                  String mStringHeaders,
                                                  String mUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append(mMethedType).append(LF);
 
        sb.append(mAccept).append(LF);
 
        if (mContent_Md5 != null) {
            sb.append(mContent_Md5);
        }
        sb.append(LF);
 
        sb.append(mContent_Type).append(LF);
 
        sb.append(mDate).append(LF);
 
        sb.append(mStringHeaders);
        sb.append(mUrl);
        return sb.toString();
 
    }
 
    public static String buildStringToSign(Request request) {
 
 
        Headers headers = request.headers();
        String mMethod = request.method();
 
        String mAccept = headers.get("Accept");
 
        byte[] inputStreamBytes = new byte[]{};
 
 
        String mContent_Md5 = null;
        try {
            mContent_Md5 = buildBodyMd5(mMethod, inputStreamBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        String mContent_Type = headers.get("Content-Type");
 
        String mDate = headers.get("Date");
 
        //Headers
        Map<String, String> hedersToSign = buildHeadersToSign(headers);
        String headersString = buildHeaders(hedersToSign);
 
        String mUrl = request.url().url().getPath();
 
        if (request.url().query() != null) {
            mUrl += "?" + request.url().query();
        }
 
        return organizationStringToSign(mMethod, mAccept, mContent_Md5, mContent_Type, mDate, headersString, mUrl);
    }
 
 
    /**
     * 组织Headers签名签名字符串
     *
     * @param headers HTTP请求头
     * @return Headers签名签名字符串
     */
    private static String buildHeaders(Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : headers.entrySet()) {
            if (e.getValue() != null) {
                sb.append(e.getKey()).append(':').append(e.getValue()).append(LF);
            }
        }
        return sb.toString();
    }
 
    /**
     * 构建参与签名的HTTP头
     * <pre>
     * 传入的Headers必须将默认的ISO-8859-1转换为UTF-8以支持中文
     * </pre>
     *
     * @param headers HTTP请求头
     * @return 所有参与签名计算的HTTP请求头
     */
    private static Map<String, String> buildHeadersToSign(Headers headers) {
        Map<String, String> headersToSignMap = new TreeMap<String,String>();
 
        String headersToSignString = headers.get(CA_PROXY_SIGN_HEADERS);
 
        if (headersToSignString != null) {
            for (String headerKey : headersToSignString.split("\\,")) {
                headersToSignMap.put(headerKey, headers.get(headerKey));
            }
        }
 
        return headersToSignMap;
    }
 
    /**
     * 构建BodyMd5
     *
     * @param httpMethod       HTTP请求方法
     * @param inputStreamBytes HTTP请求Body体字节数组
     * @return Body Md5值
     * @throws IOException
     */
    private static String buildBodyMd5(String httpMethod, byte[] inputStreamBytes) throws Exception {
        if (inputStreamBytes == null) {
            return null;
        }
 
        if (!httpMethod.equalsIgnoreCase(HTTP_METHOD_POST) && !httpMethod.equalsIgnoreCase(HTTP_METHOD_PUT)) {
            return null;
        }
 
        InputStream inputStream = new ByteArrayInputStream(inputStreamBytes);
        byte[] bodyBytes = IOUtils.toByteArray(inputStream);
        if (bodyBytes != null && bodyBytes.length > 0) {
            return base64AndMD5(bodyBytes).trim();
        }
        return null;
    }
 
    /**
     * 先进行MD5摘要再进行Base64编码获取摘要字符串
     *
     * @param bytes 待计算字节数组
     * @return
     */
    public static String base64AndMD5(byte[] bytes) throws Exception {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can not be null");
        }
 
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bytes);
            final Base64 base64 = new Base64();
 
            return new String(base64.encode(md.digest()));
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("unknown algorithm MD5");
        }
    }
 
    /**
     * 组织Uri+请求参数的签名字符串
     * Url 指 Path + Query + Body 中 Form 参数，组织方法：对 Query+Form 参数按照字典对 Key 进行排序后按照如下方法拼接，如果 Query 或 Form 参数为空，则 Url = Path，不需要添加 ？，如果某个参数的 Value 为空只保留 Key 参与签名，等号不需要再加入签名。
     *
     * @param url       HTTP请求url,不包含Query
     * @param paramsMap HTTP请求所有参数（Query+Form参数）
     * @return Uri+请求参数的签名字符串
     */
    private static String buildResource(String url, Map<String, Object> paramsMap) {
        StringBuilder builder = new StringBuilder();
 
        // url
        builder.append(url);
 
        if (paramsMap == null)
            return builder.toString();
        // Query+Form
        TreeMap<String, Object> sortMap = new TreeMap<String, Object>();
        sortMap.putAll(paramsMap);
 
 
        // 有Query+Form参数
        if (sortMap.size() > 0) {
            builder.append('?');
            builder.append(buildMapToSign(sortMap));
        }
 
        return builder.toString();
    }
 
    /**
     * 将Map转换为用&及=拼接的字符串
     */
    private static String buildMapToSign(Map<String, Object> paramMap) {
        StringBuilder builder = new StringBuilder();
 
        for (Map.Entry<String, Object> e : paramMap.entrySet()) {
            if (builder.length() > 0) {
                builder.append('&');
            }
 
            String key = e.getKey();
            Object value = e.getValue();
 
            if (value != null) {
                if (value instanceof List) {
                    List list = (List) value;
                    if (list.size() == 0) {
                        builder.append(key);
                    } else {
                        builder.append(key).append("=").append(String.valueOf(list.get(0)));
                    }
                } else if (value instanceof Object[]) {
                    Object[] objs = (Object[]) value;
                    if (objs.length == 0) {
                        builder.append(key);
                    } else {
                        builder.append(key).append("=").append(String.valueOf(objs[0]));
                    }
                } else {
                    builder.append(key).append("=").append(String.valueOf(value));
                }
            }
        }
 
        return builder.toString();
    }

}