package org.scholat.provider.user.controller.client;

import org.scholat.provider.user.pojo.UserLogin;
import org.scholat.provider.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class UserLoginClient {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/user/phone/login")
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Object loginByPhone(String phone,String password){
		
		UserLogin user = userLoginService.loginByPhone(phone, password);
		
		if(user==null){
			throw new RuntimeException("啊啊1");
		}
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
