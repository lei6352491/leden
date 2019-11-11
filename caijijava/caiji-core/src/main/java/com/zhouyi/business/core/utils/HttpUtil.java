package com.zhouyi.business.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.zhouyi.business.core.common.WXConstant;
import com.zhouyi.business.core.vo.ResponseVo;


@Slf4j
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static final Object lock = new Object();

    /**
     * HttpClient
     */
    private static HttpClient httpclient;

    private static HttpClient httpclientSSL;


    private HttpUtil() {
    }


    public static HttpClient getClient() {
        if (httpclient == null) {
            synchronized (lock) {
                if (httpclient == null) {
                    httpclient = HttpClientManager.getHttpClient();
                }
            }
        }


        return httpclient;
    }

    public static HttpClient getClientSSL(String merId) {
        try {

            // 指定读取证书格式为PKCS12
            KeyStore keyStore = KeyStore.getInstance(WXConstant.CERT_FORMAT);
            // 读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream(new File(WXConstant.CERT_ADDR));
            try {
                // 指定PKCS12的密码(商户ID)
                keyStore.load(instream, merId.toCharArray());
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs 自签名CA
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, merId.toCharArray()).build();
            // Allow TLSv1 protocol only 指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"},
                    null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclientSSL = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            LOGGER.error("init HttpUtil.ssl is error. {}", e);

            throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
        }


        return httpclientSSL;
    }


    public static ResponseVo sendPost(String url, HttpEntity httpEntity) throws Exception {
        LOGGER.info("HttpUtil sendPost method invoke url is :" + url + "|<- params ->|" + httpEntity == null ? httpEntity.toString() : null);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(httpEntity);

        LOGGER.info(httpEntity.toString());
        // 请求
        HttpResponse response = getClient().execute(httpPost);

        if (response == null) {
            throw new Exception(" response is null");
        }

        LOGGER.error("response code:" + response.getStatusLine().getStatusCode());


        // 返回结果处理
        ResponseVo responseVo = new ResponseVo();

        responseVo.setStatus(response.getStatusLine().getStatusCode());
        responseVo.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));


        return responseVo;
    }

    public static HttpClient getAppClientSSL(String merId) {
        try {

            // 指定读取证书格式为PKCS12
            KeyStore keyStore = KeyStore.getInstance(WXConstant.CERT_FORMAT);
            // 读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream(new File(WXConstant.APP_CERT_ADDR));
            try {
                // 指定PKCS12的密码(商户ID)
                keyStore.load(instream, merId.toCharArray());
            } finally {
                instream.close();
            }

            // Trust own CA and all self-signed certs 自签名CA
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, merId.toCharArray()).build();
            // Allow TLSv1 protocol only 指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"},
                    null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclientSSL = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            LOGGER.error("init HttpUtil.ssl is error. {}", e);

            throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
        }


        return httpclientSSL;
    }

    public static ResponseVo sendPostSSL(String url, HttpEntity httpEntity, String merId) throws Exception {
        LOGGER.info("HttpUtil sendPost method invoke url is :" + url + "|<- params ->|" + httpEntity == null ? httpEntity.toString() : null);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(httpEntity);


        // 请求
        HttpResponse response = getClientSSL(merId).execute(httpPost);

        if (response == null) {
            throw new Exception(" response is null");
        }

        LOGGER.error("response code:" + response.getStatusLine().getStatusCode());


        // 返回结果处理
        ResponseVo responseVo = new ResponseVo();

        responseVo.setStatus(response.getStatusLine().getStatusCode());
        responseVo.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));


        return responseVo;
    }

    public static ResponseVo sendAppPostSSL(String url, HttpEntity httpEntity, String merId) throws Exception {
        LOGGER.info("HttpUtil sendPost method invoke url is :" + url + "|<- params ->|" + httpEntity == null ? httpEntity.toString() : null);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(httpEntity);


        // 请求
        HttpResponse response = getAppClientSSL(merId).execute(httpPost);

        if (response == null) {
            throw new Exception(" response is null");
        }

        LOGGER.error("response code:" + response.getStatusLine().getStatusCode());


        // 返回结果处理
        ResponseVo responseVo = new ResponseVo();

        responseVo.setStatus(response.getStatusLine().getStatusCode());
        responseVo.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));


        return responseVo;
    }

    // 发送get请求
    public static ResponseVo sendGet(String url) throws Exception {
        LOGGER.info("HttpUtil sendGet method invoke url is :" + url);


        HttpResponse response = getClient().execute(new HttpGet(url));

        if (response == null) {
            throw new Exception("Response is null");
        }

        LOGGER.error("response code:" + response.getStatusLine().getStatusCode());


        ResponseVo responseVo = new ResponseVo();

        responseVo.setStatus(response.getStatusLine().getStatusCode());
        responseVo.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));


        return responseVo;
    }


    // 发送post请求
    public static ResponseVo sendPost(String url, Map<String, String> params) throws Exception {
        HttpEntity httpEntity = null;
        // 参数组装
        if (params != null && !params.isEmpty()) {
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

            Iterator<String> parameIterator = params.keySet().iterator();
            String parameName;

            while (parameIterator.hasNext()) {
                parameName = parameIterator.next();

                nvps.add(new BasicNameValuePair(parameName, (String) params.get(parameName)));
            }

            httpEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
        }


        return sendPost(url, httpEntity);
    }

    public static ResponseVo sendPostByJson(String url, Map<String, String> params) throws Exception {
        StringEntity httpEntity = null;
        // 参数组装
        if (params != null && !params.isEmpty()) {
            JSONObject data = new JSONObject();
            Iterator<String> parameIterator = params.keySet().iterator();
            String parameName;

            while (parameIterator.hasNext()) {
                parameName = parameIterator.next();
                data.put(parameName, params.get(parameName));
            }

            httpEntity = new StringEntity(data.toJSONString());
            httpEntity.setContentEncoding("utf-8");
//			httpEntity.setContentType("application/json");
            httpEntity.setContentType("application/x-www-form-urlencoded");
        }


        return sendPost(url, httpEntity);
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static ResponseVo sendGet(String url, Map<String, Object> params) throws Exception {
        List<String> strList = new ArrayList<String>();


        // 参数组装
        if (params != null && !params.isEmpty()) {
            Iterator<String> parameIterator = params.keySet().iterator();
            String parameName;

            while (parameIterator.hasNext()) {
                parameName = parameIterator.next();

                strList.add(parameName + "=" + params.get(parameName));
            }

            url = url + "?" + StringUtils.join(strList, "&");
        }


        return sendGet(url);
    }


    public static ResponseVo sendPostByform(String url, Map<String, String> params) throws IOException {

        PostMethod postMethod = null;
        postMethod = new PostMethod(url);

        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        List<org.apache.commons.httpclient.NameValuePair> data = new ArrayList<>();

        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            data.add(new org.apache.commons.httpclient.NameValuePair(next.getKey(), next.getValue()));
        }
        log.info("传入参数:");
        NameValuePair[] nameValuePairs = new NameValuePair[data.size()];
        data.forEach(x -> System.out.println(x));
        postMethod.setRequestBody(data.toArray(nameValuePairs));
        org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
        int response = httpClient.executeMethod(postMethod);
        log.info("调用结果:" + response);

        ResponseVo responseVo = new ResponseVo();
        String result = postMethod.getResponseBodyAsString();
        log.info("结果为:" + result);
        responseVo.setStatus(response);
        responseVo.setData(result);
        return responseVo;

    }
}