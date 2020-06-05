package org.scholat.provider.user.controller;

import org.scholat.provider.user.pojo.UserLogin;
import org.scholat.provider.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class UserLoginController {

	
}
