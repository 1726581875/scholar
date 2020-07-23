package cn.scholar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
//@EnableFeignClients(basePackages={"cn.scholat.service"})
@EnableCircuitBreaker
//@SpringBootApplication(scanBasePackages = {"cn.scholat.service","ontice"})
@SpringBootApplication
public class OnticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnticeApplication.class,args);
    }

}
