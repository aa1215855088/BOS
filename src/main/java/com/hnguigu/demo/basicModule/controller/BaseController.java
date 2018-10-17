package com.hnguigu.demo.basicModule.controller;

import com.hnguigu.demo.basicModule.domain.Staff;
import com.hnguigu.demo.basicModule.service.StaffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-28 19:55
 **/
@Controller
@RequestMapping("/base")
public class BaseController {
    @Autowired
    private StaffService staffService;

    /**
     * @return
     */
    @RequestMapping("/decidedzone")
    public String baseDecidedzone() {

        return "base/decidedzone";
    }

    /**
     * @return
     */
    @RequestMapping("/region")
    public String baseRegion() {

        return "base/region";
    }

    /**
     * @return 取派员模块
     */
    @RequestMapping("/staff")
    @RequiresPermissions("staff:list")
    public String baseStaff() {
        return "base/staff";
    }

    @RequestMapping("/subarea")
    public String baseSubarea() {
        return "base/subarea";
    }


}
