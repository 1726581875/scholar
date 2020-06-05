package org.scholat.provider.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class UserDetail {

	@Id
	private Integer userId;
	
	private String userImage;
	
	private String userName;
	
	private String userSex;
	
	private Integer userAge;
	
	private String userMajor;
	
	private String userField;
	
	private String userType;
	
	private String userSchool;
	
	private String description;
	
	//private Date createTime;
	
	
	
}
