package com.hnguigu.demo.basicModule.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 取派员
 */
@Entity
public class Staff implements Serializable {


    private static final long serialVersionUID = 459476516221739013L;

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String telephone;
    private String pda;

    // 是否作废
    private String deleteTag;

    // 单位
    private String station;

    // 取派标准
    private String standard;



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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPda() {
        return pda;
    }

    public void setPda(String pda) {
        this.pda = pda;
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }



    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", pda=" + pda +
                ", deleteTag='" + deleteTag + '\'' +
                ", station='" + station + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }
}
