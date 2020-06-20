package org.scholat.privider.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yrk
 * @date 2020/6/12 - 16:38
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableEurekaClient
@MapperScan("org.scholat.privider.task.mapper")
public class TaskProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskProviderApplication.class, args);
    }
}
