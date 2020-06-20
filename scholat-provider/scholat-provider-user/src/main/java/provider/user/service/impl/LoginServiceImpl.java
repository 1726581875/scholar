package provider.user.service.impl;

import org.scholat.common.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import provider.user.entity.UserLogin;
import provider.user.repository.UserLoginRepository;
import provider.user.service.UserLoginService;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements UserLoginService{

    @Autowired
    UserLoginRepository userLoginRepository;

    public List findAll()
    {
        return userLoginRepository.findAll();
    }



    public UserLogin findOne(String inputStr, String password) {
        //设置条件
        UserLogin user = new UserLogin();
        if(CheckUtil.isMobile(inputStr)) {//如果是手机号
            user.setUserPhone(inputStr);
        }else{
            user.setUserMail(inputStr);//否则就是按邮箱查
        }
        user.setPassword(password);
        //查询出来看是否有该用户
        Optional<UserLogin> op = userLoginRepository.findOne(Example.of(user));
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }


    //登录
    public UserLogin getUser(String phone, String password){
        return userLoginRepository.findByPasswordAndUserPhone(phone,password);
    }


    public void insertUser(UserLogin user) {
        userLoginRepository.save(user);
    }


}
