package provider.detail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("provider.detail.mapper")
public class UserDetailApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDetailApplication.class, args);
    }
}
