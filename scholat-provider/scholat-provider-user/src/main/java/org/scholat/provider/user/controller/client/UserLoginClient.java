package org.scholat.provider.user.controller.client;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.scholat.provider.user.pojo.UserLogin;
import org.scholat.provider.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class UserLoginClient {

	@Autowired
	private UserLoginService userLoginService;
	
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	@PostMapping("/user/phone/login")
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Object loginByPhone(String phone,String password,HttpServletResponse response){

		UserLogin user = userLoginService.loginByPhone(phone, password);
		//随机生成一个token标识
		String token = UUID.randomUUID().toString();
		//存到redis
		redisTemplate.opsForValue().set(token,user.getPassword(),3600);
		//设置cookie
		//setCookie(response,TOKEN_NAME, token, EXPIRE);
	  //setCookie(response,"foolId",String.valueOf(fool.getFoolId()), EXPIRE);
				
		return user;
	}
	
	public UserLogin processHystrix_Get(String phone,String password)
	  {
		UserLogin Login = new UserLogin();
		Login.setUserId(-2);
	   return Login;
	  }

	
	@PostMapping("/user/mail/login")
	public Object loginByMail(String mail,String password){
		
		return userLoginService.loginByMail(mail, password);
	}
	
	
}
