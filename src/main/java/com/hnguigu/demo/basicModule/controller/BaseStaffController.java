package com.hnguigu.demo.basicModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.basicModule.domain.Staff;
import com.hnguigu.demo.common.exception.MyException;
import com.hnguigu.demo.basicModule.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-10-06 08:56
 **/
@Controller
@RequestMapping("/base/staff")
public class BaseStaffController {
    @Autowired
    private StaffService staffService;

    @RequestMapping("/addStaff")
    @ResponseBody
    public ResponseResult addStaff(Staff staff) throws MyException {
        this.staffService.save(staff);
        return ResponseResult.success("添加成功");
    }

    @RequiresPermissions("staff:delete")
    @RequestMapping("/delStaff")
    @ResponseBody
    public ResponseResult delStaff(@RequestParam("ids") Integer[] ids) {

        this.staffService.updateDeleteTagById(ids);
        return ResponseResult.success("作废成功");

    }

    @RequestMapping("/restoreStaff")
    @ResponseBody
    public ResponseResult restoreStaff(@RequestParam("ids") Integer[] ids) {
        this.staffService.restoreDeleteTagById(ids);
        return ResponseResult.success("还原成功");
    }

    @RequestMapping("/findStaffById/{id}")
    @ResponseBody
    public ResponseResult findStaffById(@PathVariable("id") Integer id) {
        Staff staff = this.staffService.findStaffById(id);
        return ResponseResult.success(staff);
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Map staffFindAll(@RequestParam(required = false, defaultValue = "0", value = "page") Integer page,
                            @RequestParam(required = false, defaultValue = "3", value = "rows") Integer rows,
                            String name, String phone) {
        Staff staff = new Staff();
        if (!StringUtils.isBlank(name)) {
            staff.setName(name);
        }
        if (!StringUtils.isBlank(phone)) {
            staff.setTelephone(phone);
        }
        PageRequest pageable = new PageRequest(page - 1, rows);
        Page<Staff> staffServiceAll = this.staffService.findAll(staff, pageable);
        JSONObject jo = new JSONObject();
        jo.put("rows", staffServiceAll.getContent());
        //总页数
        jo.put("total", staffServiceAll.getTotalElements());
        return jo;
    }

    @RequestMapping("/updateStaff")
    @ResponseBody
    public ResponseResult updateStaff(Staff staff) throws MyException {
        Staff staffById = this.staffService.findStaffById(staff.getId());
        staff.setDeleteTag(staffById.getDeleteTag());
        this.staffService.updateStaff(staff);
        return ResponseResult.success("修改成功");
    }

    @RequestMapping("/findAllStaff")
    @ResponseBody
    public List<Staff> findAllStaff() {
        List<Staff> staffList = this.staffService.findAll();
        return staffList;
    }


}

