package com.hnguigu.demo.basicModule.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 定区
 */
@Entity
public class DecidedZone implements Serializable {


    private static final long serialVersionUID = -3811501740689820416L;
    @Id
    private Integer id;

    private String name;

    /**
     * 取派负责人，这个定区的所有的物件都由该人负责派送
     *
     * @hibernate-many-to-one
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Staff staff;


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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "DecidedZone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
