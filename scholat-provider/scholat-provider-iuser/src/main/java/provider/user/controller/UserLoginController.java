package provider.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.user.entity.UserLogin;
import provider.user.service.impl.LoginServiceImpl;

import java.util.List;
import java.util.Map;


@RestController
public class UserLoginController {

    @Autowired
    LoginServiceImpl userServiceImpl;
    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("doLogin")
    public String doLogin(UserLogin user, Map<String,Object> map){
        String phone = user.getUserPhone();
        String password = user.getPassword();
        UserLogin user1 =  userServiceImpl.getUser(phone,password);
        if(user1 == null){
            map.put("msg","登入失败");
        }else{
            map.put("msg","登入成功");
        }
        return "success";
    }
    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("doRegist")
    public String doRegist(UserLogin user, Map<String,Object> map){
        userServiceImpl.insertUser(user);
        map.put("msg","注册成功");
        return "success";
    }


    //找出所有的学生
    @RequestMapping("/user/findAll")
    public List findAll() {
        return userServiceImpl.findAll();
    }




}