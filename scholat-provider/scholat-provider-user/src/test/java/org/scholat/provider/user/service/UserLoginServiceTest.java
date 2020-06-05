package org.scholat.provider.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserLoginServiceTest {

	@Autowired
	private UserLoginService userLoginService;
	
	@Test
	public void testLogin(){
		userLoginService.loginByPhone("11", "11");
	}
	
	
}
