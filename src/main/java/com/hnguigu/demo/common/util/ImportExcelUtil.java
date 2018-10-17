package com.hnguigu.demo.common.util;

import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.basicModule.domain.SubArea;
import com.hnguigu.demo.basicModule.service.AreaService;
import com.hnguigu.demo.basicModule.service.SubAreaService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import sun.dc.pr.PRError;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
public class ImportExcelUtil {


    public static List<Area> readAreaExcel(InputStream fileInputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<Area> areas = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            Area area = new Area();
            String province = row.getCell(0).getStringCellValue();
            String shortCode = String.valueOf(row.getCell(1).getNumericCellValue());
            String city = row.getCell(2).getStringCellValue();
            String cityCode = String.valueOf(row.getCell(3).getNumericCellValue());
            String district = row.getCell(4).getStringCellValue();
            area.setCity(city);
            area.setCityCode(cityCode);
            area.setDistrict(district);
            area.setProvince(province);
            area.setPostCode("40000");
            area.setShortCode(shortCode);
            areas.add(area);
        }
        return areas;
    }

}
