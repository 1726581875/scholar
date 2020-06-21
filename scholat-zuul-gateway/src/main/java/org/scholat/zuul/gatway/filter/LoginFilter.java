package org.scholat.zuul.gatway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author xmz
 * 2020年6月6日
 * 全局拦截器
 */
@Component
@Slf4j
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class LoginFilter extends ZuulFilter{

	@Override
	public Object run() throws ZuulException {
	/*
		//拦截所有请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));       
        
        //不拦截登录
        String login = "http://localhost:8001/scholat/user/login";
        if(request.getRequestURL().toString().equals(login)) return null;
        
        //获取请求里的cookie参数
        Cookie cookie = CookieUtil.getCookie(request, MyConstant.TOKEN_NAME);
		if(cookie != null){
			//获取到jwt
			String token = cookie.getValue();
			Claims claims = JwtUtil.checkJWT(token);
			//2.jwt看看验证是否通过
			if(claims == null) {
				log.warn("token验证不通过=======>错误token：{}",token);
			}else{
				return null;
			}
		}
        log.warn("token is empty");
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        try {
        	
            ctx.getResponse().sendRedirect("/errPage.html");
        } catch (Exception e) {
        }

	 */
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
