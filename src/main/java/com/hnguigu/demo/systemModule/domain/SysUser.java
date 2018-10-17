package com.hnguigu.demo.systemModule.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.mapping.ToOne;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @program: BOS
 * @description:
 * @author: 徐子楼
 * @create: 2018-10-09 09:11
 **/
@Entity
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1548142589089464134L;
    @Id
    @GeneratedValue
    private int uid;

    /**
     * 登录账号
     */
    @Column(length = 20)
    private String loginName;

    /**
     * 密码
     */
    private String password;
    /**
     * 密码加密盐
     */
    private String salt;

    /**
     * 工资
     */
    private Float salary;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 性别
     */
    @Column(length = 2)
    private String sex;
    /**
     * 工作单位
     */
    @ManyToOne
    private Unit unit;
    /**
     * 手机号码
     */
    @Column(length = 20)
    private String phone;
    /**
     * 备注
     */
    private String remark;

    /**
     * 用户角色
     */
    @JSONField(serialize=false)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> role;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public List<SysRole> getRole() {
        return role;
    }

    public void setRole(List<SysRole> role) {
        this.role = role;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.salt + this.loginName;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "uid=" + uid +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", unit=" + unit +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", role=" + role +
                '}';
    }

}
