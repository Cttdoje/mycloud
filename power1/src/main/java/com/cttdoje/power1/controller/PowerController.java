package com.cttdoje.power1.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PowerController {

    @RequestMapping("/getPower")
    public String getPower(){
        System.err.println("power1"+ LocalDateTime.now());
        Map<String,String> map = new HashMap<>();
        map.put("key","'power1");
        return JSON.toJSONString(map);
    }
}
