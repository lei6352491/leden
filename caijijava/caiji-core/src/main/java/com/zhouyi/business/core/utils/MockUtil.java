package com.zhouyi.business.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import com.zhouyi.business.core.common.ReturnCode;
import com.zhouyi.business.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zhouyi.business.core.common.WXConstant;
import com.zhouyi.business.core.vo.ResponseVo;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MockUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(MockUtil.class);
	
	public static final Object lock = new Object();
	
	public static final String APP_KEY="24933762";
    /**
     * HttpClient
     */
    private static HttpClient httpclient;
    
    private static HttpClient httpclientSSL;


    private MockUtil()
    {
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
    	if (httpclientSSL == null) {
    		synchronized (lock) {
    			if (httpclientSSL == null) {
    				try {
    					
    					// 指定读取证书格式为PKCS12
        		        KeyStore keyStore  = KeyStore.getInstance(WXConstant.CERT_FORMAT);
        		        // 读取本机存放的PKCS12证书文件
//        		        FileInputStream instream = new FileInputStream(new File(HttpUtil.class.getClassLoader().getResource(WXConstant.CERT_ADDR).getPath()));
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
        		        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },
        		                null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        		        httpclientSSL = HttpClients.custom().setSSLSocketFactory(sslsf).build();
					} catch (Exception e) {
						LOGGER.error("init HttpUtil.ssl is error. {}", e);
						
						throw new BusinessException(ReturnCode.ERROR_01.getCode(), ReturnCode.ERROR_01.getMsg());
					}
    		    	
    			}
			}
        }
    	
    	
    	return httpclientSSL;
    }
    
    
    public static String sendPost(String url, String json) {
    	try{
    		Date now=new Date();
			OkHttpClient client = new OkHttpClient().newBuilder().hostnameVerifier(new HostnameVerifier() {

			    @Override
			    public boolean verify(String hostname, SSLSession session) {
			        //强行返回true 即验证成功
			        return true;
			    }
			}).build();
			RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
			Request oldRequest=new Request.Builder().url(url).build();
			Request request=oldRequest.newBuilder()
			.addHeader("Host", "api.zhouyilive.com")
			.addHeader("Date", now.toString())
			.addHeader("Authorization",  "APPCODE 83359fd73fe94948385f570e3c139105")
			.addHeader("User-Agent", "Apache-HttpClient/4.1.2 (java 1.6)")
			.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
			.addHeader("Accept", "application/json")
			.addHeader("X-Ca-Request-Mode", "debug")
			.addHeader("X-Ca-Version", "1")
			.addHeader("X-Ca-Signature-Headers", "X-Ca-Request-Mode,X-Ca-Version,X-Ca-Stage,X-Ca-Key,X-Ca-Timestamp")
			.addHeader("X-Ca-Stage", "RELEASE")
			.addHeader("X-Ca-Timestamp",String.valueOf(now.getTime()))
			.addHeader("X-Ca-Nonce",UUID.randomUUID().toString())
			.addHeader("X-Ca-Key",APP_KEY)
			.build();
			String sign=MockSignUtil.getSign(request);
			request=request.newBuilder().addHeader("X-Ca-Signature", sign).post(body).build();
			Response response = client.newCall(request).execute();
			// 返回结果处理
			
			return JSON.toJSONString(response);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
	}
    
    
    public static ResponseVo sendPostSSL(String url, HttpEntity httpEntity, String merId) throws Exception {
		LOGGER.info("HttpUtil sendPost method invoke url is :" + url + "|<- params ->|" + httpEntity == null ? httpEntity.toString() : null);
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(httpEntity);
		

		// 请求
		HttpResponse response = getClientSSL(merId).execute(httpPost);
		
		if(response == null) {
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
		
		if(response == null) {
			throw new Exception("Response is null");
		}
		
		LOGGER.error("response code:" + response.getStatusLine().getStatusCode());
		
		
		ResponseVo responseVo = new ResponseVo();
		
		responseVo.setStatus(response.getStatusLine().getStatusCode());
		responseVo.setData(EntityUtils.toString(response.getEntity(), "UTF-8"));
		
		
		return responseVo;
	}
	
	
	// 发送post请求
//	public static ResponseVo sendPost(String url, Map<String, String> params) throws Exception {
//		HttpEntity httpEntity = null;
//		// 参数组装
//		if(params != null && !params.isEmpty()) {
//			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//			
//			Iterator<String> parameIterator = params.keySet().iterator();
//			String parameName;
//			
//			while(parameIterator.hasNext()) {
//				parameName = parameIterator.next();
//				
//				nvps.add(new BasicNameValuePair(parameName, (String)params.get(parameName)));
//			}
//			
//			httpEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
//		}
//		
//		
//		return sendPost(url, httpEntity);
//	}
	
//	public static ResponseVo sendPostByJson(String url, Map<String, String> params) throws Exception {
//		StringEntity httpEntity = null;
//		// 参数组装
//		if(params != null && !params.isEmpty()) {
//			JSONObject data = new JSONObject(); 
//			Iterator<String> parameIterator = params.keySet().iterator();
//			String parameName;
//			
//			while(parameIterator.hasNext()) {
//				parameName = parameIterator.next();
//				data.put(parameName, params.get(parameName));
//			}
//			
//			httpEntity = new StringEntity(data.toJSONString());
//			httpEntity.setContentEncoding("utf-8");  
//			httpEntity.setContentType("application/json");  
//		}
//		
//		
//		return sendPost(url, httpEntity);
//	}
	
	/**
	 * 发送get请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public static ResponseVo sendGet(String url, Map<String,Object> params) throws Exception {
		List<String> strList = new ArrayList<String>();
		
		
		// 参数组装
		if(params != null && !params.isEmpty()) {
			Iterator<String> parameIterator = params.keySet().iterator();
			String parameName;
			
			while(parameIterator.hasNext()) {
				parameName = parameIterator.next();
				
				strList.add(parameName + "=" + params.get(parameName));
			}
			
			url = url + "?" + StringUtils.join(strList, "&");
		}
		
		
		return sendGet(url);
	}
}