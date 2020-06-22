package provider.detail.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.detail.entity.UserDetail;
import provider.detail.service.DetailService;

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
}
