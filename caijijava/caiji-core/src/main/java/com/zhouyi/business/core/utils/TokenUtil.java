package com.zhouyi.business.core.utils;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;

import com.zhouyi.business.core.common.OSSConfigure;

/**
 * token工具类
 * @modifyBy 李秸康
 */
public class TokenUtil {

	/**
	 *非JWT生成方式
	 * @return
	 */
	@Deprecated
	public static String createToken() {
		String visitorToken = UUID.randomUUID().toString().replaceAll("-", "");
		visitorToken += RandomStringUtils.randomAlphanumeric(20);
		return visitorToken;
	}

	/**
	 * 从客户端获取token信息
	 * @param request
	 * @param isCookie
	 * @param tokenType
	 * @return
	 */
	public static String getToken(HttpServletRequest request, boolean isCookie, String tokenType) {
		if (isCookie) {
			return getCookie(request, tokenType);
		} else {
			return getHeader(request, tokenType);
		}
	}

	private static String getCookie(HttpServletRequest request, String name) {
		if (name == null || "".equals(name)) {
			return null;
		}
		String visitorToken = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					visitorToken = cookie.getValue();
					break;
				}
			}
		}
		return visitorToken;
	}

	private static String getHeader(HttpServletRequest request, String name) {
		String headVal = request.getHeader(name);
		return headVal;
	}

	public static void setCookie(String name, String token, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, token);
		cookie.setMaxAge(OSSConfigure.TOKEN_EXPIRE);
		cookie.setPath(OSSConfigure.TOKEN_PATH);
//		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	public static void setCookie(String name, String token, HttpServletResponse response, int expires) {
		Cookie cookie = new Cookie(name, token);
		cookie.setMaxAge(expires);
		cookie.setPath(OSSConfigure.TOKEN_PATH);
//		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	/**
	 * 设置用户登录的token信息
	 * 
	 * @Title: setUserLoginCookie
	 * @author zenglongyao
	 * @param
	 * @return void
	 * @throws @date:
	 *             2016年7月31日
	 * @time: 上午10:56:30
	 * @Description:
	 */

	public static void setUserLoginCookie(String name, String token, HttpServletResponse response) {
		Cookie usercookie = new Cookie(name, token);
		usercookie.setPath(OSSConfigure.TOKEN_PATH);
		usercookie.setHttpOnly(true);
		// 设置cookie 最后访问的时间
		// Cookie mscookie = new
		// Cookie(OSSConfigure.M_S,System.currentTimeMillis()+"");
		// mscookie.setPath(OSSConfigure.TOKEN_PATH);
		response.addCookie(usercookie);
		// response.addCookie(mscookie);
	}

	public static void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {

		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(value);
					cookie.setPath("/");
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					break;
				}
			}
		}

	}

	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					cookie.setValue(null);
					cookie.setMaxAge(0);// 立即销毁cookie
					cookie.setPath("/");
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					break;
				}
			}
		}
	}


}
