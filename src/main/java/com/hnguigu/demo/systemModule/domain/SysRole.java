package com.hnguigu.demo.systemModule.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
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
public class SysRole implements Serializable {

    private static final long serialVersionUID = 8866896642369559697L;

    @Id
    private int roleId;
    /**
     * 权限名称
     */
    @Column(length = 20)
    private String name;
    /**
     * 权限描述
     */
    @Column(length = 20)
    private String description;

    /**
     * 角色用户
     */
    @JSONField(serialize=false)
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<SysUser> user;

    /**
     * 角色权限关系
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePrivilege", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "privilegeId")})
    private List<SysPrivilege> privilege;

    public int getId() {
        return roleId;
    }

    public void setId(int id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SysUser> getUser() {
        return user;
    }

    public void setUser(List<SysUser> user) {
        this.user = user;
    }

    public List<SysPrivilege> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<SysPrivilege> privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" +
                ", privilege=" + privilege +
                '}';
    }
}
