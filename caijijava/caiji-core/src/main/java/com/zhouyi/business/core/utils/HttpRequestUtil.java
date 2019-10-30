package com.zhouyi.business.core.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {

	/**
	 * 获取远程访问IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIp(HttpServletRequest request) {

		String remoteIp = null;
		
		if (remoteIp == null || remoteIp.length() == 0) {
			remoteIp = request.getHeader("X-FORWARDED-FOR");
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getRemoteAddr();
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("X-Real-IP");
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("Proxy-Client-IP");
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("WL-Proxy-Client-IP");
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("HTTP_CLIENT_IP");
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (remoteIp == null || remoteIp.isEmpty() || "unknown".equalsIgnoreCase(remoteIp)) {
				remoteIp = request.getRemoteHost();
			}
		}
		return remoteIp;
	}
}
