package org.scholat.provider.user.controller;

import org.scholat.provider.user.pojo.UserDetail;
import org.scholat.provider.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xmz
 *对其他服务提供的接口
 */
@RestController
@RequestMapping("/user-api")
public class UserDetailController {

	@Autowired
	private UserDetailService userDetailService;
	
	@GetMapping("/user/{userId}")
	public UserDetail findUserDetail(@PathVariable int userId){		
		return userDetailService.findById(userId);
	}	
	
	
	@PostMapping("/user/delete")
	public void delete(@RequestParam("userId") int userId){		
		 userDetailService.deleteById(userId);
	}
}
