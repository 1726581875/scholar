package provider.user.entity;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;

/**
 * @author osy
 * @create 2020-06-13 21:33
 */
//使用JPA注解配置映射关系
//告诉JPA这是一个实体类（和数据表映射的类）
//@Table(name = "user_login") //@Table来指定和哪个数据表对应;如果省略默认表名就是user；
@Entity
@Data
public class UserLogin {
    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer userId;
    private String userPhone;
    private String userMail;
    private String password;
}
