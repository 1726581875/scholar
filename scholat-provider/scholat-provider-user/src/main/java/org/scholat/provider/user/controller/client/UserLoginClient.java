package org.scholat.provider.user.controller.client;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scholat.common.ResultMsg;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.exception.CommonEnum;
import org.scholat.common.utils.CookieUtil;
import org.scholat.common.utils.ResultUtil;
import org.scholat.provider.user.pojo.UserLogin;
import org.scholat.provider.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class UserLoginClient {

	@Autowired
	private UserLoginService userLoginService;
	
	
	//@Autowired
	//private StringRedisTemplate redisTemplate;
	
	
	/**
	 * 手机登录接口
	 * @param phone
	 * @param password
	 * @param response
	 * @return
	 *Object
	 */
	@PostMapping("/user/phone/login")
	public Object loginByPhone(String phone,String password,HttpServletResponse response){		
		UserLogin user = userLoginService.loginByPhone(phone, password);
/*		if(user != null){//登录成功
		//1.随机生成一个token标识
		String token = UUID.randomUUID().toString();	
		//2.存到redis
		redisTemplate.opsForValue().set(token,user.getUserPhone(),MyConstant.EXPIRE_TIME);
		//3.设置cookie
		CookieUtil.setCookie(response,MyConstant.TOKEN_NAME, token, MyConstant.EXPIRE_TIME);
		}else{
		 return ResultUtil.fail("登录失败");
		}*/
		return ResultUtil.success();
	}
	
	
		
//	@PostMapping("/user/mail/login")
//	public Object loginByMail(String mail,String password){
//		
//		return userLoginService.loginByMail(mail, password);
//	}
	
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 *Object
	 */
/*	@PostMapping("/user/loginout")
	public Object loginOut(HttpServletRequest request,HttpServletResponse response){		
		//从cookie里查询
		Cookie cookie = CookieUtil.getCookie(request, MyConstant.TOKEN_NAME);	
		if(cookie != null){
			//清除redis里的token
			redisTemplate.delete(cookie.getValue());
			//清除cookie
			CookieUtil.setCookie(response, MyConstant.TOKEN_NAME, null, 0);
		}
		return ResultUtil.success();
	}*/
	
	
}
