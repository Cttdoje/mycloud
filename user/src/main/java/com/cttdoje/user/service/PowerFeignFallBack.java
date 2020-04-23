package com.cttdoje.user.service;


import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class PowerFeignFallBack implements  FallbackFactory<PowerFeignClient> {

    @Override
    public PowerFeignClient create(Throwable throwable) {
        return new PowerFeignClient() {
            @Override
            public String getPower() {
                System.err.println("导致回退异常" + throwable);
                return "服务降级-> feign调用降级 && 打印错误";
            }
        };
    }



    /*@Override
    public String getPower() {
        return "服务降级-> feign调用降级";
    }*/
}
