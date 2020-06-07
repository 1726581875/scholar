package org.scholat.provider.user.service.impl;

import java.util.Optional;

import org.scholat.provider.user.pojo.UserLogin;
import org.scholat.provider.user.repository.UserLoginRepository;
import org.scholat.provider.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private String slat = "eriry479^^%^$^*9Fgdjjgs";

	@Autowired
	private UserLoginRepository userRepository;

	@Override
	public UserLogin loginByPhone(String phone, String password) {
		// 构造条件根据phone查出用户
		UserLogin user = new UserLogin();
		user.setUserPhone(phone);
		Optional<UserLogin> optional = userRepository.findOne(Example.of(user));
		if (optional.isPresent()) {// 用户名不存在
			UserLogin userLogin = optional.get();
			if (!userLogin.getPassword().equals(getMD5(password))) {// 如果密码不一致	         
	          return null;         
			}else{
				return userLogin;
			}
		}
		
		return null;
	}

	@Override
	public UserLogin loginByMail(String mail, String password) {

		return null;
	}

	@Override
	public UserLogin register() {

		return null;
	}

	private String getMD5(String str) {
		String base = slat + str;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}

}
