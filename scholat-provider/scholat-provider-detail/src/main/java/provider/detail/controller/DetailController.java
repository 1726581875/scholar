package provider.detail.controller;


import org.scholat.common.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.detail.entity.UserDetail;
import provider.detail.service.DetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/detail")
@CrossOrigin
public class DetailController {
    @Autowired
    private DetailService detailService;


    @PostMapping("/all")
    public List<UserDetail> findAll(){
        return  detailService.findAll();

    }
}
