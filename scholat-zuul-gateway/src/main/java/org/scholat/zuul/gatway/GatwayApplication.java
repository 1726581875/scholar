package org.scholat.zuul.gatway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GatwayApplication 
{
    public static void main( String[] args )
    {
    SpringApplication.run(GatwayApplication.class, args);
    }
}
