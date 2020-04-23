package com.cttdoje.user.controller;

import com.cttdoje.user.service.PowerFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 在eureka中应用名称
     */
    private static final String POWER_SERVER = "http://SERVER-POWER";

    private static final String ORDER_SERVER = "http://SERVER-ORDER";

    @Autowired
    PowerFeignClient powerFeignClient;


    @RequestMapping("/getFeignPower")
    public String getFeignPower() {
        System.err.println("feign result:" + powerFeignClient.getPower());
        return "success";
    }

    /**
     * 此方法名需要与 fallbackMethod中字符串一致
     */
    public String getFeignPowerFallBack() {
        System.err.println("服务降级->调用getFeignPowerFallBack");
        return "服务降级";
    }


    @RequestMapping("/getPower")
    @HystrixCommand(fallbackMethod = "getFeignPowerFallBack", threadPoolKey = "power",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "1")}
    )
    public Object getPower() {
        Object obj = restTemplate.getForObject(POWER_SERVER + "/getPower", Object.class);
        System.err.println("result:" + obj);
        return null;
    }

    @RequestMapping("/getOrder")
    public Object getOrder() {
        Object obj = restTemplate.getForObject(ORDER_SERVER + "/getOrder", Object.class);
        System.err.println("query order:" + obj);
        return obj;
    }

    @RequestMapping("/getUser")
    public String getUser() {
        return "helloWord";
    }
}
