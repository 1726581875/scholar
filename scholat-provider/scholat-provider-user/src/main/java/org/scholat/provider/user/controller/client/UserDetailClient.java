package org.scholat.provider.user.controller.client;

import java.util.List;

import org.scholat.common.ResultMsg;
import org.scholat.common.exception.ComonEnum;
import org.scholat.provider.user.pojo.UserDetail;
import org.scholat.provider.user.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(allowCredentials = "true" ,allowedHeaders = "*")
public class UserDetailClient {

	@Autowired
	private UserDetailService userService;
	
	
   @GetMapping("/users") 
   public Object findAll(){		
	   List<UserDetail> userList = userService.findAll();	
	  return new ResultMsg<Object>(ComonEnum.SUCCESS, userList);
	}
	
   @GetMapping("/users/{userId}") 
   public Object findById(@PathVariable Integer userId){		
	   UserDetail user = userService.findById(userId);
	  return new ResultMsg<Object>(ComonEnum.SUCCESS, user);
	}
   
   @DeleteMapping("/users/delete") 
   public Object deleteById(@RequestParam("userId") Integer userId){		
	    userService.deleteById(userId);
	  return new ResultMsg<Object>(ComonEnum.SUCCESS, null);
	}
   
   
   @PostMapping("/users/update") 
   public Object update(UserDetail user){		
	    userService.updateSelected(user);
	  return new ResultMsg<Object>(ComonEnum.SUCCESS, null);
	}
   
   
}
