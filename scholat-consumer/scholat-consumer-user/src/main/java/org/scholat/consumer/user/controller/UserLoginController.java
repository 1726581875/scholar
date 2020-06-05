package org.scholat.consumer.user.controller;

import org.scholat.consumer.user.service.UserLoginClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {

	@Autowired
	private UserLoginClient userLoginClient;
	
	@PostMapping("/login")
	@ResponseBody
	public Object login(String phone ,String password){
		
		return userLoginClient.login(phone, password);
	}
	
	@GetMapping("/to")
	public String toindex(){
    return "hello";
	}
	
}
