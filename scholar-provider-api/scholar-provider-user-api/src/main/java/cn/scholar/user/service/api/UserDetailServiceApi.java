package cn.scholar.user.service.api;

import cn.scholar.common.pojo.UserDetailInfo;
import cn.scholar.user.service.api.hystrix.UserDetailHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "SCHOLAT-USER",fallback = UserDetailHystrix.class)
public interface UserDetailServiceApi {

	@GetMapping("/user-api/user/{userId}")
	public UserDetailInfo findUserDetail(@PathVariable("userId") int userId);
	
	@PostMapping("/user-api/user/delete")
	public void delete(@RequestParam("userId") int userId);
}
