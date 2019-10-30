package com.zhouyi.business.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class HttpCall {
    private static final Logger LOG = LoggerFactory.getLogger(HttpCall.class);
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpClientContext localContext = HttpClientContext.create();
    private String accessToken;
    
    public String sendPost(String url, Map<String, String> params){
        try {
            HttpPost post = new HttpPost(url);
            // 封装参数
            List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> item : params.entrySet()) {
                    BasicNameValuePair pair = new BasicNameValuePair(item.getKey(), item.getValue());
                    parameters.add(pair);
                }
                HttpEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
                post.setEntity(entity);
            }
            // 提交请求
            HttpResponse response = httpclient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 将返回的实体转换为String
                String result = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(result);
                return result;
            }else{
                System.out.println(response.getStatusLine().getStatusCode());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Object httpGet(String apiUrl) throws IllegalStateException {
        localContext.setCookieStore(cookieStore);
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            response = httpclient.execute(httpGet, localContext);
            LOG.info("wx-httpcall={}",response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            return responseContext(entity.getContent());
        } catch (Exception e) {
            LOG.error("wx-httpcall_err={}",e);
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
    
    /**发送json数据的post请求**/
    public Object httpPostCall(String apiUrl,String json) throws IllegalStateException {
        localContext.setCookieStore(cookieStore);
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(apiUrl);
            StringEntity s = new StringEntity(json.toString(),Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            httpPost.setEntity(s);
            response = httpclient.execute(httpPost, localContext);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY){
                HttpEntity entity = response.getEntity();
                JSONObject resultJsonObject = JSONObject.parseObject(responseContext(entity.getContent()));
                String location = resultJsonObject.getString("location");
                if(location == null) {
                     LOG.error("Redirect URL no find");
                     throw new IllegalStateException("Redirect URL no find");
                }
                return  new URL(location);
            } else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                return responseContext(entity.getContent());
            } else if(response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND){
                LOG.error("Page: " + apiUrl + " no find");
                throw new IllegalStateException("404 Page no find");
            } else {
                LOG.error(response.getStatusLine().getStatusCode()+" Business is not supported");
                throw new IllegalStateException(response.getStatusLine().getStatusCode()+" Business is not supported");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    
    private String responseContext(InputStream input) {
        try {
            BufferedReader readContent = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            String outStr = readContent.readLine();
            StringBuilder sb = new StringBuilder();
            while (outStr != null) {
                sb.append(outStr);
                outStr = readContent.readLine();
            }
            LOG.info("httpcall_responseContext={}",sb);
            return sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("httpcall_responseContext_err=",e.toString());
                }
            }
        }
        return "";
    }
    
    public void closeHttpclient() {
        try {
            httpclient.close();
        } catch (Exception e) {

        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                LOG.error(e.toString());
            }
        }

    }

    public CloseableHttpClient getHttpclient() {
        return httpclient;
    }

    public void setHttpclient(CloseableHttpClient httpclient) {
        this.httpclient = httpclient;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public HttpClientContext getLocalContext() {
        return localContext;
    }

    public void setLocalContext(HttpClientContext localContext) {
        this.localContext = localContext;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
}
