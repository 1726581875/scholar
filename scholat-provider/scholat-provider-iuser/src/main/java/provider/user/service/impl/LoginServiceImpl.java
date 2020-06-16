package provider.user.service.impl;

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



    public UserLogin findOne(String phone, String password) {
        UserLogin user = new UserLogin();
        user.setUserPhone(phone);
        user.setPassword(password);
        Optional<UserLogin> op = userLoginRepository.findOne(Example.of(user));
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }
    }


    //登录
    public UserLogin getUser(String phone, String password){
        return userLoginRepository.findByPasswordAndUserPhone(phone,password);
    }


    public void insertUser(UserLogin user) {
        userLoginRepository.save(user);
    }


}
