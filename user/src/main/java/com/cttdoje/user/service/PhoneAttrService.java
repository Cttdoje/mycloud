package com.cttdoje.user.service;

import com.cttdoje.user.entity.PhoneAttrEntity;

public interface PhoneAttrService {

    PhoneAttrEntity findPhoneAttrEntityByPrefixAndProvince(int prefix, String province);

    int updatePhoneByPrefixAndProvince(int prefix,String province,int phone);
}
