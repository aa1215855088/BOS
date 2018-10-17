package com.hnguigu.demo.systemModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.systemModule.domain.SysPrivilege;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.service.SysPrivilegeService;
import com.hnguigu.demo.systemModule.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @create: 2018-10-12 17:11
 **/
@Controller
@RequestMapping("/admin/function")
public class SysPrivilegeController {
    @Autowired
    private SysPrivilegeService sysPrivilegeService;

    @RequestMapping("findAll")
    @ResponseBody
    public Map findAll() {
        List<SysPrivilege> sysUserList = this.sysPrivilegeService.findAll();
        JSONObject jo = new JSONObject();
        jo.put("rows", sysUserList);
        return jo;
    }
}
