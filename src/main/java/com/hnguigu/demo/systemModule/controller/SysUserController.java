package com.hnguigu.demo.systemModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.common.util.UUIDUtil;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.domain.SysUser;
import com.hnguigu.demo.systemModule.domain.Unit;
import com.hnguigu.demo.systemModule.service.SysRoleService;
import com.hnguigu.demo.systemModule.service.SysUserService;
import com.hnguigu.demo.systemModule.service.UnitService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @create: 2018-10-09 16:29
 **/
@Controller
@RequestMapping("/admin/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("save")
    public String save(SysUser sysUser, Integer unitId, Integer sysRoleId) {
        Unit unit = this.unitService.findOne(unitId);
        SysRole sysRole = this.sysRoleService.findOne(sysRoleId);
        List<SysRole> sysRoleList = new ArrayList<>();
        sysRoleList.add(sysRole);
        sysUser.setUnit(unit);
        sysUser.setRole(sysRoleList);
        // 将UUID+用户名作为盐值
        String oneUUID = UUIDUtil.getOneUUID();
        sysUser.setSalt(oneUUID);
        /**
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", sysUser.getPassword(), oneUUID + sysUser.getLoginName(), 1024).toHex();
        sysUser.setPassword(newPs);
        this.sysUserService.save(sysUser);
        return "redirect:/admin/userList";
    }

    @RequestMapping("findAll")
    @ResponseBody
    public Map findAll() {
        List<SysUser> sysUserList = this.sysUserService.findAll();
        JSONObject jo = new JSONObject();
        jo.put("rows", sysUserList);
        return jo;
    }

    @RequestMapping("del")
    @ResponseBody
    public ResponseResult del(@RequestParam("ids") Integer[] ids) {
        for (Integer id : ids) {
            if (id != null) {
                this.sysUserService.delete(id);
            }
        }
        return ResponseResult.success("删除成功");
    }

    @RequestMapping("checkLoginName")
    @ResponseBody
    public ResponseResult checkLoginName(String loginName) {
        boolean b = this.sysUserService.existsByLoginName(loginName);
        if (b) {
            return ResponseResult.fail("该用户名已存在,请重新输入!");
        }
        return ResponseResult.success();
    }
}
