package org.scholat.consumer.user.service;

import org.scholat.common.pojo.UserLogin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCHOLAT-USER",fallback = UserLoginHystrix.class)
public interface UserLoginClient {

	@PostMapping("/user/phone/login")
	public UserLogin login(@RequestParam("phone") String phone, @RequestParam("password") String password);
	
	
	
}
