package cn.scholat.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "SCHOLAT-COURSE")
public interface NoticeServiceApi {

    @PostMapping("/send/to/all")
    public void sendMessagetoAll(String message);

}
