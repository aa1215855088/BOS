package com.hnguigu.demo.basicModule.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 分区
 */
@Entity
public class SubArea implements Serializable {

    private static final long serialVersionUID = -613460827159598756L;

    @Id
    private Integer id;

    // 地址关键字
    private String addressKey;

    private String startNum;

    private String endNum;

    // 单双号
    private String single;

    // 位置
    private String position;

    /**
     * 所属的定区
     *
     * @hibernate.many-to-one
     */
    @ManyToOne
    private DecidedZone decidedZone;

    /**
     * 所属的区域
     *
     * @hibernate.many-to-one
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Area area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    public String getStartNum() {
        return startNum;
    }

    public void setStartNum(String startNum) {
        this.startNum = startNum;
    }

    public String getEndNum() {
        return endNum;
    }

    public void setEndNum(String endNum) {
        this.endNum = endNum;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    @JsonIgnore
    public DecidedZone getDecidedZone() {
        return decidedZone;
    }

    public void setDecidedZone(DecidedZone decidedZone) {
        this.decidedZone = decidedZone;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "SubArea{" +
                "id=" + id +
                ", addressKey='" + addressKey + '\'' +
                ", startNum='" + startNum + '\'' +
                ", endNum='" + endNum + '\'' +
                ", single='" + single + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
