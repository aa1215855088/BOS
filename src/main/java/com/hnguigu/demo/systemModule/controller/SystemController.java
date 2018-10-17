package com.hnguigu.demo.systemModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.systemModule.domain.SysPrivilege;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.domain.Unit;
import com.hnguigu.demo.systemModule.service.SysPrivilegeService;
import com.hnguigu.demo.systemModule.service.SysRoleService;
import com.hnguigu.demo.systemModule.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * @create: 2018-10-09 08:56
 **/
@Controller
@RequestMapping("/admin")
public class SystemController {


    @Autowired
    private UnitService unitService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @RequestMapping("/userList")
    public String userList() {
        return "/admin/userlist";
    }

    @RequestMapping("/function")
    public String function() {
        return "/admin/function";
    }

    @RequestMapping("/role")
    public String role() {
        return "/admin/role";
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo() {
        return "/admin/userinfo";
    }

    @RequestMapping("/addFunction")
    public String addFunction() {
        return "/admin/function_add";
    }

    @RequestMapping("/addRole")
    public String addRole() {
        return "/admin/role_add";
    }

    @ModelAttribute("units")
    public List<Unit> unit() {
        List<Unit> units = this.unitService.findAll();
        return units;
    }

    @ModelAttribute("roles")
    public List<SysRole> roles() {
        List<SysRole> sysRoles = this.sysRoleService.findAll();
        return sysRoles;
    }

    @RequestMapping("privilegeTree")
    @ResponseBody
    public List<Map> privilegeTree() {
        List<Map> mapList = new ArrayList<>();
        List<SysPrivilege> sysPrivilegeList = this.sysPrivilegeService.findAll();
        for (SysPrivilege sysPrivilege : sysPrivilegeList) {
            JSONObject jo = new JSONObject();
            jo.put("id", sysPrivilege.getPrivilegeId());
            jo.put("name", sysPrivilege.getDescription());
            if (sysPrivilege.getFatherNode() == null) {
                jo.put("pId", 0);
            } else {
                jo.put("pId", sysPrivilege.getFatherNode().getPrivilegeId());
            }
            mapList.add(jo);
        }
        return mapList;
    }

}
