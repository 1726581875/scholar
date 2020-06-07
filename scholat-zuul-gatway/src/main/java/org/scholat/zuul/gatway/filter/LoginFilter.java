package org.scholat.zuul.gatway.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xmz
 * 2020年6月6日
 * 全局拦截器
 */
@Component
@Slf4j
public class LoginFilter extends ZuulFilter{

	 //@Autowired
	//private StringRedisTemplate redisTemplate;
	
	@Override
	public Object run() throws ZuulException {
/*		//拦截所有请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));       
        
        //不拦截登录
        String login = "http://localhost:8001/scholat/user/user/phone/login";
        if(request.getRequestURL().toString().equals(login)) return null;
        
        //获取请求里的token参数
        Cookie cookie = CookieUtil.getCookie(request, MyConstant.TOKEN_NAME);
		if(cookie != null){
			//redis里获取该值
		  String token = redisTemplate.opsForValue().get(cookie.getValue());
		  if(token != null){
			  return null;//如果存在放行
		  }			
		}
        log.warn("token is empty");
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        try {
        	
            ctx.getResponse().sendRedirect("/errPage.html");
        } catch (Exception e) {
        }*/
        return null;
	}

	@Override
	public boolean shouldFilter() {
		
		return true;
	}

	@Override
	public int filterOrder() {
		
		return 0;
	}

	@Override
	public String filterType() {
		
		return "pre";
	}

}
