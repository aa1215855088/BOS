package com.hnguigu.demo.basicModule.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 区域
 */
@Entity
public class Area implements Serializable {

    private static final long serialVersionUID = 4118109460254637469L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String district;

    // 邮编
    private String postCode;

    // 简码
    private String shortCode;

    // 城市编码
    private String cityCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", postCode='" + postCode + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                '}';
    }
}
