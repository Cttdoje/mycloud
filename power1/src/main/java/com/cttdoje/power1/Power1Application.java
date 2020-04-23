package com.cttdoje.power1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Power1Application {

    public static void main(String[] args) {
        SpringApplication.run(Power1Application.class, args);
    }

}
