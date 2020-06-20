package org.scholat.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfo {

	private  Integer userId;
	
	private String userPhone;
	
	private String userMail;
	
	private String password;

	private Integer role;
	
    public static UserLoginInfo invalid() {
        return new UserLoginInfo(-1, "","", "", null);
    }
	
}
