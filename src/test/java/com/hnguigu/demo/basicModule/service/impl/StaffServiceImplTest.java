package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.domain.Staff;
import com.hnguigu.demo.basicModule.service.StaffService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-29 13:44
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StaffServiceImplTest {

    @Autowired
    private StaffService staffService;


    @Test
    public void findAll() {
        Pageable pageable = new PageRequest(1, 5);
        Page<Staff> staffServiceAll = this.staffService.findAll(pageable);
        for (Staff staff : staffServiceAll) {
            System.out.println(staff);
        }
    }

    @Test
    @Rollback
    public void deleteStaffByIds() {
        Integer[] ids = {14, 15};
        this.staffService.updateDeleteTagById(ids);
    }

    @Test
    public void test() {
        //生成Workbook
        HSSFWorkbook wb = new HSSFWorkbook();

        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错）
        @SuppressWarnings("unused")
        Sheet sheet = wb.createSheet();
        // 创建第一行
        HSSFRow row = (HSSFRow) sheet.createRow(0);

        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(new HSSFRichTextString("分拣编号"));

        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(new HSSFRichTextString("省"));

        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(new HSSFRichTextString("市"));

        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(new HSSFRichTextString("区"));


        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue(new HSSFRichTextString("关键字"));

        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue(new HSSFRichTextString("起始号"));


        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue(new HSSFRichTextString("终止号"));


        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue(new HSSFRichTextString("单双号"));


        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue(new HSSFRichTextString("位置"));
        //保存为Excel文件
        FileOutputStream out = null;

        try {
            out = new FileOutputStream("e:\\text.xls");
            wb.write(out);
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }
}