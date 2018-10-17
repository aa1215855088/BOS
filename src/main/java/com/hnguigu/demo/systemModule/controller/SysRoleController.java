package com.hnguigu.demo.systemModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.systemModule.domain.SysPrivilege;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.domain.SysUser;
import com.hnguigu.demo.systemModule.service.SysPrivilegeService;
import com.hnguigu.demo.systemModule.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.management.relation.Role;
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
 * @create: 2018-10-12 17:13
 **/
@Controller
@RequestMapping("/admin/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPrivilegeService privilegeService;

    @RequestMapping("/findAll")
    @ResponseBody
    public Map findAll() {
        List<SysRole> sysUserList = this.sysRoleService.findAll();
        JSONObject jo = new JSONObject();
        jo.put("rows", sysUserList);
        return jo;
    }

    @RequestMapping("addRole")
    @ResponseBody
    public ResponseResult addRole(SysRole role, Integer[] privilegeIds) {
        try {
            List<SysPrivilege> privilegeList = new ArrayList<>();
            for (Integer id : privilegeIds) {
                SysPrivilege sysPrivilege = this.privilegeService.findOne(id);
                privilegeList.add(sysPrivilege);
            }
            role.setPrivilege(privilegeList);
            this.sysRoleService.save(role);
        } catch (Exception e) {
            return ResponseResult.fail("添加失败");
        }
        return ResponseResult.success();
    }
}
