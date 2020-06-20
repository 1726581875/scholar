package org.scholat.proviser.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@EnableFeignClients(basePackages={"org.scholat.provider.api","cn.scholat.service"})
@ComponentScan(basePackages={"org.scholat.provider.api","org.scholat.proviser.course","cn.scholat.service"})
@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient
@MapperScan("org.scholat.proviser.course.mapper")
public class CourseProviserApplication 
{
    public static void main( String[] args )
    {
      SpringApplication.run(CourseProviserApplication.class, args);
    }
}
