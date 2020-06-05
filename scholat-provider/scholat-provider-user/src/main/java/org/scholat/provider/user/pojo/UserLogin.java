package org.scholat.provider.user.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserLogin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer userId;
	
	private String userPhone;
	
	private String userMail;
	
	private String password;
	
	
}
