package com.cttdoje.user.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


@Entity
@Table(name = "tbl_phone_attr")
public class PhoneAttrEntity implements Serializable {
    private int id;
    private Integer prefix;
    private Integer phone;
    private String province;
    private String city;
    private String isp;
    private Integer postCode;
    private Integer cityCode;
    private Integer areaCode;
    private Timestamp createDate;
    private Timestamp updateDate;

    public PhoneAttrEntity(){}

    public PhoneAttrEntity(String prefix,String phone,String province,String city,String isp,String postCode,String cityCode,String areaCode){
        this.prefix = Integer.valueOf(prefix);
        this.phone = Integer.valueOf(phone);
        this.province = province;
        this.city = city;
        this.isp = isp;
        this.postCode = Integer.valueOf(postCode);
        this.cityCode = Integer.valueOf(cityCode);
        this.areaCode = Integer.valueOf(areaCode);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "prefix")
    public Integer getPrefix() {
        return prefix;
    }

    public void setPrefix(Integer prefix) {
        this.prefix = prefix;
    }

    @Basic
    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "isp")
    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    @Basic
    @Column(name = "post_code")
    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "city_code")
    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    @Basic
    @Column(name = "area_code")
    public Integer getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "update_date")
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneAttrEntity that = (PhoneAttrEntity) o;
        return id == that.id &&
                Objects.equals(prefix, that.prefix) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(province, that.province) &&
                Objects.equals(city, that.city) &&
                Objects.equals(isp, that.isp) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(cityCode, that.cityCode) &&
                Objects.equals(areaCode, that.areaCode) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prefix, phone, province, city, isp, postCode, cityCode, areaCode, createDate, updateDate);
    }


    @Override
    public String toString() {
        return "PhoneAttrEntity{" +
                "id=" + id +
                ", prefix=" + prefix +
                ", phone=" + phone +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isp='" + isp + '\'' +
                ", postCode=" + postCode +
                ", cityCode=" + cityCode +
                ", areaCode=" + areaCode +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
