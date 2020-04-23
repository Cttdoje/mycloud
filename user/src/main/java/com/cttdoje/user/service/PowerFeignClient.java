package com.cttdoje.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "SERVER-POWER",fallbackFactory = PowerFeignFallBack.class)
public interface PowerFeignClient {
    /**
     * getPower
     * @return
     */
    @RequestMapping("/getPower")
    String getPower();
}
