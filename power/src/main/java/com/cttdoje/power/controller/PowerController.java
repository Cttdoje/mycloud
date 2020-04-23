package com.cttdoje.power.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PowerController {

    @RequestMapping("/getPower")
    public String getPower(String name) throws Exception{
        System.err.println("power:"+LocalDateTime.now());
        Map<String,String> map = new HashMap<>();
        map.put("key","power");

//        Thread.sleep(2000);
        if(name == null){
            throw new Exception("自定义异常");
        }
        return JSON.toJSONString(map);
    }
}
