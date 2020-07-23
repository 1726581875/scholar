package cn.scholar.controller;


import cn.scholar.common.utils.ResultUtil;
import cn.scholar.entity.UserDetail;
import cn.scholar.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detail")
@CrossOrigin
public class DetailController {
    @Autowired
    private DetailService detailService;


    @GetMapping("/all")
    public List<UserDetail> findAll(){
        return  detailService.findAll();

    }

    @PostMapping("/insert")
    public Object insertUserDetail(@RequestBody UserDetail userDetail){
        int flag = detailService.insertDetail(userDetail);
        return flag==1 ? ResultUtil.success() : ResultUtil.fail("插入用户详情失败");
    }

}
