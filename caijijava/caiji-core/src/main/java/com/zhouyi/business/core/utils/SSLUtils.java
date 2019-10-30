package com.zhouyi.business.core.utils;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class SSLUtils {
		public static void setNoCheckSSLCert() throws Exception{
			TrustManager[] tm = {new MyX509TrustManager ()}; 
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE"); 
			sslContext.init(null, tm, new java.security.SecureRandom()); 
			
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			SSLContext.setDefault(sslContext);
			HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
			HttpsURLConnection.setDefaultHostnameVerifier(new TrustAnyHostnameVerifier());
		}
}


class MyX509TrustManager implements X509TrustManager{

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	
}

 class TrustAnyHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}