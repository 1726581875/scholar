package provider.user.controller;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.scholat.common.constant.MyConstant;
import org.scholat.common.pojo.UserLoginInfo;
import org.scholat.common.utils.CookieUtil;
import org.scholat.common.utils.JwtUtil;
import org.scholat.common.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import provider.user.entity.UserLogin;
import provider.user.service.impl.LoginServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
public class UserLoginController {

    @Autowired
    LoginServiceImpl userServiceImpl;

    @PostMapping("/login")
    public Object login(String inputStr , String password ,HttpServletResponse resp){
        //1.校验参数 。。 略

        //2.调用服务层方法
        UserLogin userLogin = userServiceImpl.findOne(inputStr, password);
        if(userLogin == null){//如果没查到，返回失败
            return ResultUtil.fail("登录失败");
        }
        //3.生成jwt
        UserLoginInfo userInfo = new UserLoginInfo();
        BeanUtils.copyProperties(userLogin,userInfo);
        log.info("加密的用户信息 ======> {}",userInfo);
        String token = JwtUtil.generateJsonWebToken(userInfo);
        log.info("生成的jwt======>{}",token);
       //4.存到cookie
        CookieUtil.setCookie(resp,MyConstant.TOKEN_NAME,token,JwtUtil.EXPIRITION);
        CookieUtil.setCookie(resp,"userId",userLogin.getUserId().toString(),JwtUtil.EXPIRITION);
        return ResultUtil.success();
    }

    @PostMapping("/loginout")
    public Object loginOut(HttpServletRequest req , HttpServletResponse resp){
        //1.cookie里查看看是否存在
       Cookie cookie = CookieUtil.getCookie(req , MyConstant.TOKEN_NAME);
        if(cookie == null) {
            return ResultUtil.fail("没有登录");
        }
        //获取到jwt
        String token = cookie.getValue();
        Claims claims = JwtUtil.checkJWT(token);
        //2.jwt看看验证是否通过
        if(claims == null) {
            return ResultUtil.fail("验证不通过");
        }else{
            //3.设置cookie过期
            String userId = claims.get("userId").toString();
            CookieUtil.setCookie(resp,"userId",null,0);
            CookieUtil.setCookie(resp,MyConstant.TOKEN_NAME,null,0);
        }
        return ResultUtil.success();
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