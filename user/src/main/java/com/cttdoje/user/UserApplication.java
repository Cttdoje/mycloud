package com.cttdoje.user;

import RibbonConfig.OrderRuleConfig;
import RibbonConfig.PowerRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@RibbonClients({
        @RibbonClient(name = "SERVER-POWER",configuration = PowerRuleConfig.class),
        @RibbonClient(name = "SERVER-ORDER",configuration = OrderRuleConfig.class)
})
@EnableFeignClients
@EnableHystrix
@EnableCaching
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}


