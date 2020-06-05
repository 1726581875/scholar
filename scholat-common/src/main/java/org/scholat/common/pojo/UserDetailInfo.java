package org.scholat.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailInfo {

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
	
	 public static UserDetailInfo invalid() {
	        return new UserDetailInfo(-1, "", "", "", 0 ,"","","","","");
	    }
	
}
