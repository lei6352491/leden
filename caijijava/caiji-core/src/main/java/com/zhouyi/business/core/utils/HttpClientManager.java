package com.zhouyi.business.core.utils;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * HTTPClient管理器
 * 
 * 
 */
class HttpClientManager {

    public static HttpClient httpClient;
    /**
     * 链接超时
     */
    public static int contectTimeout = 30000;
    /**
     * Socket超时
     */
    public static int socketTimeout = 30000;

    /**
     * 创建一个HTTP Client
     */
    public static void createHttpClient() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpConnectionParams.setConnectionTimeout(params, contectTimeout);
        HttpConnectionParams.setSoTimeout(params, socketTimeout);

        PoolingClientConnectionManager pcm = new PoolingClientConnectionManager();  
    	//MaxTotal 值不应该太大  
    	pcm.setMaxTotal(200);  
    	//DefaultMaxPerRoute 是路由的默认最大连接（该值默认为2），限制数量实际使用DefaultMaxPerRoute并非MaxTotal。  
    	//设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)，路由是对maxTotal的细分。  
    	pcm.setDefaultMaxPerRoute(pcm.getMaxTotal());//（目前只有一个路由，因此让他等于最大值）  
    	  
    	httpClient = new DefaultHttpClient(pcm, params);  
    }

    public static void shutdownHttpClient() {
        if (httpClient != null && httpClient.getConnectionManager() != null) {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public static HttpClient getHttpClient() {
        if (httpClient == null) {
            createHttpClient();
        }
        return httpClient;
    }
    
}
