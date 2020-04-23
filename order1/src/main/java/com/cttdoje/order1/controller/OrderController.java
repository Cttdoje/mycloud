package com.cttdoje.order1.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderController {

    @RequestMapping("/getOrder")
    public String getPower(){
        System.err.println("order"+ LocalDateTime.now());
        Map<String,String> map = new HashMap<>();
        map.put("key","'order1");
        return JSON.toJSONString(map);
    }
}
