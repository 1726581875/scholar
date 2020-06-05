package org.scholat.consumer.user.service;

import org.scholat.common.pojo.UserLogin;
import org.springframework.stereotype.Component;

@Component
public class UserLoginHystrix implements UserLoginClient{

	@Override
	public UserLogin login(String phone, String password) {
		System.out.println("11111111111111111111111");
		UserLogin user = new UserLogin();
		user.setUserId(-1);
		user.setPassword("-1");
		return user;
	}

}
