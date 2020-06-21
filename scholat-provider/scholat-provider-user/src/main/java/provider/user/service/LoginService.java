package provider.user.service;

import org.apache.catalina.User;
import org.scholat.common.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import provider.user.entity.UserLogin;
import provider.user.repository.UserLoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserLoginRepository userLoginRepository;

    public List findAll() {
        return userLoginRepository.findAll();
    }

    public Optional<UserLogin> findById(Integer id) {
        return userLoginRepository.findById(id);
    }

    public UserLogin insertUser(UserLogin user) {
       user.setRole(1);
       return userLoginRepository.save(user);
    }

  /*  public UserLogin updateUser(UserLogin user) {
        return userLoginRepository.save(user);
    }*/
    public UserLogin findUser(Integer id) {
        return userLoginRepository.findById(id).get();
    }

    public  UserLogin updateUser(UserLogin user) {
        UserLogin user1 = findUser(user.getUserId());
        if(user.getPassword() != null) {
            String password = user.getPassword();
            user1.setPassword(password);
        }if(user.getUserPhone() != null) {
            String phone = user.getUserPhone();
            user1.setUserPhone(phone);
        }if(user.getUserMail() != null) {
            String mail = user.getUserMail();
            user1.setUserMail(mail);
        }if(user.getRole() != null){
            user1.setRole(user.getRole());
        }
        return userLoginRepository.save(user1);
    }

    public void deleteUser(UserLogin user) {
        userLoginRepository.delete(user);
    }

    public UserLogin findOne(String inputStr, String password) {
        //设置条件
        UserLogin user = new UserLogin();
        if (CheckUtil.isNumber(inputStr)) {//如果是手机号
            user.setUserPhone(inputStr);
        } else {
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
    public UserLogin getUser(String phone, String password) {
        return userLoginRepository.findByPasswordAndUserPhone(phone, password);
    }


}
