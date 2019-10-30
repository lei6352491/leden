package com.zhouyi.business.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 通过httpclient调用接口的方法
 */
public class ClientSSL {

	private static final Logger LOG = LoggerFactory.getLogger(ClientSSL.class);

	/**
	 * 
	 * post请求
	 */
	public static Object httpClientPost(String url) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			LOG.info("httpClientPost_url=" + url);
			response = httpclient.execute(httpPost);
//			LOG.info("httpClientPost_resp={}",JSON.toJSONString(response));
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
				HttpEntity entity = response.getEntity();

				JSONObject resultJsonObject = JSONObject.parseObject(responseContext(entity.getContent()));
				String location = resultJsonObject.getString("location");
				if (location == null) {
					throw new IllegalStateException("Redirect URL no find");
				}

				String errcode = resultJsonObject.getString("errcode");
				if (errcode != null) {
					LOG.error("接口返回错误：" + resultJsonObject.getString("errmsg"));
					throw new IllegalStateException(resultJsonObject.getString("errmsg"));
				}
			}
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_PERMANENTLY) {
				HttpEntity entity = response.getEntity();
				System.out.println("header:" + response.getAllHeaders().toString());
				String contentType = entity.getContentType().getValue();
				System.out.println("contentType=" + contentType);

				JSONObject resultJsonObject = JSONObject.parseObject(responseContext(entity.getContent()));
				String location = resultJsonObject.getString("location");
				if (location == null) {
					throw new IllegalStateException("Redirect URL no find");
				}
				return new URL(location);

			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String contentType = entity.getContentType().getValue();// application/json;charset=ISO-8859-1
				System.out.println("contentType=" + contentType);
				return responseContext(entity.getContent());
			} else if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				LOG.error("Page: " + url + " no find");
				throw new IllegalStateException("404 Page no find");
			} else {
				LOG.error(response.getStatusLine().getStatusCode() + " Business is not supported");
				throw new IllegalStateException(response.getStatusLine().getStatusCode() + " Business is not supported");
			}
		} catch (Exception e) {
			LOG.error("httpClientPost_err",e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					LOG.error(e.toString());
				}
			}
		}
		return "";

	}

	private static String responseContext(InputStream input) {
		try {
			BufferedReader readContent = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			String outStr = readContent.readLine();
			StringBuilder sb = new StringBuilder();
			while (outStr != null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug(outStr);
				}
				sb.append(outStr);
				outStr = readContent.readLine();
			}
			return sb.toString();
		} catch (Exception e) {
			LOG.error("responseContext_err=",e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error(e.toString());
				}
			}
		}
		return "";
	}

}
