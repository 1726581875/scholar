package org.scholat.provider.common;

import lombok.Data;

@Data
public class UserLogin {

	private  int userId;
	
	private String userPhone;
	
	private String usermail;
	
	private String password;
	
}
