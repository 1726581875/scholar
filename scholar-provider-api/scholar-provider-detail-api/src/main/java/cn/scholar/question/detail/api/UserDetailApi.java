package cn.scholar.question.detail.api;

import cn.scholar.common.pojo.UserDetailInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value="scholat-userLogin")
public interface UserDetailApi {

    @PostMapping("/detail/insert")
    public Object insertUserDetail(@RequestBody UserDetailInfo userDetail);




}
