package com.cttdoje.user.dao;

import com.cttdoje.user.entity.PhoneAttrEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneAttrRepository  extends JpaRepository<PhoneAttrEntity, Integer> {

    List<PhoneAttrEntity> findByProvinceAndPrefix(String province,int prefix);

    @Query(value = "select p from  PhoneAttrEntity p where p.isp = :isp and p.prefix = :prefix ",nativeQuery = false)
    List<PhoneAttrEntity> getPhoneAttrEntity(@Param("isp") String isp,@Param("prefix") int prefix);
}
