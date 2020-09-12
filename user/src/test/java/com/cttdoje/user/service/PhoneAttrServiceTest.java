package com.cttdoje.user.service;

import com.alibaba.fastjson.JSONObject;
import com.cttdoje.user.entity.PhoneAttrEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneAttrServiceTest {

    @Autowired
    PhoneAttrService phoneAttrService;

    @Test
    public void getPhoneAttrByPrefixAndProvince() {
        PhoneAttrEntity entity = phoneAttrService.findPhoneAttrEntityByPrefixAndProvince(155, "福建");
        System.out.println(JSONObject.toJSONString(entity, true));
    }

    @Test
    public void updatePhone() {
        int count = phoneAttrService.updatePhoneByPrefixAndProvince(155, "福建", 1551079);
        System.out.println("update count:" + count);
    }

}
