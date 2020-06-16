package ontice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients(basePackages={"cn.scholat.service"})
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = {"cn.scholat.service","ontice"})
@RestController
public class OnticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnticeApplication.class,args);
    }

    @GetMapping("/")
    public String hello(){
        return "hello word";
    }

}
