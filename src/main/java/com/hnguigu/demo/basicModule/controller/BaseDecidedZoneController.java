package com.hnguigu.demo.basicModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.basicModule.domain.DecidedZone;
import com.hnguigu.demo.basicModule.domain.SubArea;
import com.hnguigu.demo.basicModule.service.DecidedZoneService;
import com.hnguigu.demo.basicModule.service.SubAreaService;
import com.hnguigu.demo.common.util.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
 * @create: 2018-10-14 15:52
 **/
@RestController
@RequestMapping("/base/dz")
public class BaseDecidedZoneController {
    @Autowired
    private DecidedZoneService decidedZoneService;
    @Autowired
    private SubAreaService subAreaService;

    @RequestMapping("addDz")
    public ResponseResult addDz(DecidedZone decidedZone, @RequestParam(value = "subAreaIds") String subAreaIds) {
        boolean exists = this.decidedZoneService.exists(decidedZone.getId());
        if (exists) {
            return ResponseResult.fail("定区编号已存在，请重新输入");
        }
        DecidedZone dz = this.decidedZoneService.save(decidedZone);
        String[] subAreaIdArray = subAreaIds.split(",");
        for (String subAreaId : subAreaIdArray) {
            SubArea subArea = this.subAreaService.findOne(Integer.valueOf(subAreaId));
            subArea.setDecidedZone(dz);
            this.subAreaService.save(subArea);
        }
        return ResponseResult.success("添加成功");
    }

    @RequestMapping("findAll")
    public Map findAll(Integer page, Integer rows, String id) {
        DecidedZone dz = new DecidedZone();
        if (!StringUtils.isEmpty(id)) {
            dz.setId(Integer.valueOf(id));
        }
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        Page<DecidedZone> decidedZones = this.decidedZoneService.findAll(dz, pageRequest);
        JSONObject jo = new JSONObject();
        jo.put("rows", decidedZones.getContent());
        //总页数
        jo.put("total", decidedZones.getTotalElements());
        return jo;
    }

    @RequestMapping("/delDz")
    public ResponseResult delDz(@RequestParam("ids") Integer[] ids) {
        for (Integer id : ids) {
            if (id != null) {
                List<SubArea> subAreas = this.subAreaService.findSubAreaByDecidedZoneId(id);
                for (SubArea subArea : subAreas) {
                    subArea.setDecidedZone(null);
                    this.subAreaService.save(subArea);
                }
                this.decidedZoneService.delete(id);
            }
        }
        return ResponseResult.success("删除成功");
    }
}
