package com.cttdoje.user.service.impl;

import com.cttdoje.user.dao.PhoneAttrRepository;
import com.cttdoje.user.entity.PhoneAttrEntity;
import com.cttdoje.user.service.PhoneAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PhoneAttrServiceImpl implements PhoneAttrService {
    @Autowired
    private PhoneAttrRepository phoneAttrRepository;

    @Override
    @Cacheable(value = "phone", key = "#prefix+'_'+ #province")
    public PhoneAttrEntity findPhoneAttrEntityByPrefixAndProvince(int prefix, String province) {
        return phoneAttrRepository.findPhoneAttrEntityByPrefixAndProvince(prefix, province);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "phone",key = "#prefix + '_' + #province")
    public int updatePhoneByPrefixAndProvince(int prefix, String province, int phone) {
        return phoneAttrRepository.updatePhoneAttrEntityByPrefixAndProvince(prefix, province, phone);
    }
}
