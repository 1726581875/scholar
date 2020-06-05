package org.scholat.provider.user.service;

import org.scholat.provider.user.pojo.UserLogin;

public interface UserLoginService {

	public UserLogin loginByPhone(String phone,String password);
	
	public UserLogin loginByMail(String mail,String password);
	
	public UserLogin register();
	
}
