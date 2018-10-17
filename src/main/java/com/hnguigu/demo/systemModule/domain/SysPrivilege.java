package com.hnguigu.demo.systemModule.domain;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * @create: 2018-10-09 09:12
 **/
@Entity
public class SysPrivilege implements Serializable {

    private static final long serialVersionUID = 4075313044814385285L;

    @Id
    private int privilegeId;
    /**
     * 权限名称
     */
    @Column(length = 20)
    private String name;

    /**
     * 权限字符串如 user:list user:update
     */
    @Column(length = 20)
    private String privilegeCode;
    /**
     * 权限描述
     */

    private String description;
    /**
     * 是否显示菜单
     */
    @Column(length = 20)
    private String createMenu;
    /**
     * 优先级
     */
    @Column(length = 20)
    private String priority;
    /**
     * 资源路径
     */
    private String url;
    @JSONField(serialize = false)
    @ManyToMany
    @JoinTable(name = "SysRolePrivilege", joinColumns = {@JoinColumn(name = "privilegeId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> role;

    @ManyToOne
    private SysPrivilege fatherNode;

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public String getCreateMenu() {
        return createMenu;
    }

    public void setCreateMenu(String createMenu) {
        this.createMenu = createMenu;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonIgnore
    public List<SysRole> getRole() {
        return role;
    }

    public void setRole(List<SysRole> role) {
        this.role = role;
    }

    public SysPrivilege getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(SysPrivilege fatherNode) {
        this.fatherNode = fatherNode;
    }

    @Override
    public String toString() {
        return "SysPrivilege{" +
                "privilegeId=" + privilegeId +
                ", name='" + name + '\'' +
                ", privilegeCode='" + privilegeCode + '\'' +
                ", description='" + description + '\'' +
                ", createMenu='" + createMenu + '\'' +
                ", priority='" + priority + '\'' +
                ", url='" + url + '\'' +
                ", role=" + +
                '}';
    }
}

