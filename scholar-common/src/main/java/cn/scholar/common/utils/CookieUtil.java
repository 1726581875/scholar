package cn.scholar.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 封装cookie操作
 * 给浏览器设置cookie 和获取浏览器传来的cookie
 *
 */
public class CookieUtil {
	
	/**
	 * 设置cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletResponse response,
			String name ,String value ,int maxAge){
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);		
	}
	
	/**
	 * 获取cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request,String name){
		Map<String, Cookie> cookieMap = readCookieMap(request);		
	  return cookieMap.containsKey(name) ? cookieMap.get(name) : null;
		
	}
	
	
	public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
		Map<String,Cookie> cookieMap = new HashMap<>();
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		} 
		return cookieMap;
	}
	
	
}
