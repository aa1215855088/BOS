package com.hnguigu.demo.basicModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.common.util.ImportExcelUtil;
import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.common.exception.MyException;
import com.hnguigu.demo.basicModule.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
 * @create: 2018-10-06 16:06
 **/
@RestController
@RequestMapping("/base")
public class BaseAreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping("/area/addArea")
    public void addArea(Area area) {
        area.setName(area.getProvince() + "-" + area.getCity() + "-" + area.getDistrict());
        this.areaService.save(area);
    }

    @RequestMapping("/area/findAll")
    public Map findAll(Integer page, Integer rows) {
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        Page<Area> areas = this.areaService.findAll(pageRequest);
        JSONObject jo = new JSONObject();
        jo.put("rows", areas.getContent());
        //总页数
        jo.put("total", areas.getTotalElements());
        return jo;
    }

    @RequestMapping("/area/delArea")
    public ResponseResult delArea(@RequestParam("ids") Integer[] ids) {
        this.areaService.delArea(ids);
        return ResponseResult.success("删除成功");
    }

    @RequestMapping("/area/findAllArea")
    public List<Area> findAllArea() {
        List<Area> areas = this.areaService.findAll();
        return areas;
    }

    @RequestMapping("/area/findAreaById")
    public ResponseResult findAreaById(Integer id) {
        Area area = this.areaService.findAreaById(id);
        return ResponseResult.success(area);
    }

    @RequestMapping("/area/updateArea")
    public void updateArea(Area area) {
        //传入ID时修改
        this.areaService.save(area);
    }

    @RequestMapping("/area/importData")
    public ResponseResult importData(@RequestParam("file") MultipartFile file) throws MyException, IOException {
        if (file.isEmpty()) {
            throw new MyException("文件为空");
        }
        this.areaService.readAreaExcel(file.getInputStream());
        return ResponseResult.success("导入成功");
    }
}
