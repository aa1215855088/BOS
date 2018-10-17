package com.hnguigu.demo.basicModule.controller;

import com.alibaba.fastjson.JSONObject;
import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.basicModule.service.DecidedZoneService;
import com.hnguigu.demo.common.exception.MyException;
import com.hnguigu.demo.common.util.PathNameUtil;
import com.hnguigu.demo.common.util.ResponseResult;
import com.hnguigu.demo.basicModule.domain.SubArea;
import com.hnguigu.demo.basicModule.service.AreaService;
import com.hnguigu.demo.basicModule.service.SubAreaService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　Json　　　┃
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
 * @create: 2018-10-07 16:14
 **/
@RestController
@RequestMapping("/base/subArea")
public class BaseSubAreaController {
    @Autowired
    private SubAreaService subAreaService;
    @Autowired
    private AreaService areaService;


    @RequestMapping("/findAll")
    public Map findAll(HttpServletRequest request, Integer page, Integer rows) {
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        Page<SubArea> subAreaPage = null;
        SubArea subArea = new SubArea();
        Area area = new Area();
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String district = request.getParameter("district");
        String addressKey = request.getParameter("addressKey");
        if (!StringUtils.isBlank(province)) {
            area.setProvince(province);
        }
        if (!StringUtils.isBlank(city)) {
            area.setCity(city);
        }
        if (!StringUtils.isBlank(district)) {
            area.setDistrict(district);
        }
        if (!StringUtils.isBlank(addressKey)) {
            subArea.setAddressKey(addressKey);
        }
        subArea.setArea(area);
        subAreaPage = this.subAreaService.findAll(subArea, pageRequest);

        JSONObject jo = new JSONObject();
        jo.put("rows", subAreaPage.getContent());
        //总页数
        jo.put("total", subAreaPage.getTotalElements());
        return jo;
    }

    @RequestMapping("/addSubArea")
    public ResponseResult addSubArea(SubArea subArea) {
        boolean exists = this.subAreaService.exists(subArea.getId());
        if (exists) {
            return ResponseResult.fail("添加失败,该分链编号已存在请重新输入");
        }
        this.subAreaService.save(subArea);
        return ResponseResult.success("添加成功");
    }

    @RequestMapping("/updateSubArea")
    public ResponseResult updateSubArea(SubArea subArea) {
        this.subAreaService.save(subArea);
        return ResponseResult.success("修改成功");
    }

    @RequestMapping("export")
    public ResponseResult export(String titles, HttpServletResponse response) {
        response.setContentType("text/html,charset=utf-8");

        response.setCharacterEncoding("UTF-8");
        //生成Workbook
        HSSFWorkbook wb = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错）
        @SuppressWarnings("unused")
        Sheet sheet = wb.createSheet();
        // 创建第一行
        HSSFRow row1 = (HSSFRow) sheet.createRow(0);
        int i = 0;
        for (String title : titles.split(",")) {
            HSSFCell cell = row1.createCell(i);
            i++;
            cell.setCellValue(new HSSFRichTextString(title));
        }
        List<SubArea> subAreas = this.subAreaService.findAll();
        int b = 1;
        for (SubArea subArea : subAreas) {
            HSSFRow row = (HSSFRow) sheet.createRow(b);

            HSSFCell cell0 = row.createCell(0);
            cell0.setCellValue(subArea.getId());

            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(new HSSFRichTextString(subArea.getArea().getProvince()));

            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(new HSSFRichTextString(subArea.getArea().getCity()));

            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(new HSSFRichTextString(subArea.getArea().getDistrict()));


            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(new HSSFRichTextString(subArea.getAddressKey()));

            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(new HSSFRichTextString(subArea.getStartNum()));


            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(new HSSFRichTextString(subArea.getEndNum()));


            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(new HSSFRichTextString(subArea.getSingle()));


            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(new HSSFRichTextString(subArea.getPosition()));
            b++;
        }
        //保存为Excel文件
        FileOutputStream out = null;

        try {
            //默认保存到E盘
            String exportPath = PathNameUtil.exportPathName();
            out = new FileOutputStream("e:\\" + exportPath);
            wb.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                return ResponseResult.fail("导出失败");
            }
        }
        return ResponseResult.success("导出成功");
    }

    @RequestMapping("/delSubArea")
    public ResponseResult deleSubArea(@RequestParam("ids") Integer[] ids) {

        for (Integer id : ids) {
            this.subAreaService.delete(id);
        }
        return ResponseResult.success("删除成功");
    }

    @RequestMapping("/importData")
    public ResponseResult importData(@RequestParam("file") MultipartFile file) throws MyException, IOException {
        if (file.isEmpty()) {
            throw new MyException("文件为空");
        }
        this.subAreaService.readSubAreaExcel(file.getInputStream());
        return ResponseResult.success("导入成功");
    }

    @RequestMapping("/findAllSubArea")
    public List<SubArea> findAllSubArea() {
        List<SubArea> subAreas = this.subAreaService.findSubAreaByDecidedZoneIsNull();
        return subAreas;
    }

    @RequestMapping("/findSubAreaByDzId/{id}")
    public Map findSubAreaByDzId(@PathVariable("id") Integer id) {
        List<SubArea> subAreaByDecidedZoneId = this.subAreaService.findSubAreaByDecidedZoneId(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rows", subAreaByDecidedZoneId);
        return jsonObject;
    }
}
