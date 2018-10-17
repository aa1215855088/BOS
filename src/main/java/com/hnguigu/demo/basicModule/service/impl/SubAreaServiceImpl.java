package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.dao.AreaDao;
import com.hnguigu.demo.basicModule.dao.SubAreaDao;
import com.hnguigu.demo.basicModule.domain.SubArea;
import com.hnguigu.demo.basicModule.service.SubAreaService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * @create: 2018-10-07 16:12
 **/
@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {
    @Autowired
    private SubAreaDao subAreaDao;
    @Autowired
    private AreaDao areaDao;

    @Override
    public <S extends SubArea> S save(S entity) {

        return this.subAreaDao.save(entity);
    }

    @Override
    public SubArea findOne(Integer id) {

        return this.subAreaDao.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        this.subAreaDao.delete(id);
    }

    @Override
    public Page<SubArea> findAll(Pageable pageable) {

        return this.subAreaDao.findAll(pageable);
    }

    @Override
    public List<SubArea> findAll() {
        return this.subAreaDao.findAll();
    }

    @Override
    public void readSubAreaExcel(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<SubArea> subAreas = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {
            HSSFRow row = sheet.getRow(i);
            SubArea subArea = new SubArea();
            double numericCellValue = row.getCell(0).getNumericCellValue();
            int id = (int) numericCellValue;
            SubArea serviceOne = this.subAreaDao.findOne(id);
            if (serviceOne != null) {
                continue;
            } else {
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String addressKey = row.getCell(4).getStringCellValue();
                String startNum = row.getCell(5).getStringCellValue();
                String endNum = row.getCell(6).getStringCellValue();
                String single = row.getCell(7).getStringCellValue();
                String position = row.getCell(8).getStringCellValue();
                subArea.setId(id);
                subArea.setAddressKey(addressKey);
                subArea.setEndNum(endNum);
                subArea.setStartNum(startNum);
                subArea.setSingle(single);
                String name = province + "-" + city + "-" + district;
                subArea.setArea(this.areaDao.findAreaByName(name));
                this.subAreaDao.save(subArea);
            }

        }
    }

    @Override
    public <S extends SubArea> Page<S> findAll(SubArea subArea, Pageable pageable) {
        Example<SubArea> subAreaExample = Example.of(subArea);
        return (Page<S>) this.subAreaDao.findAll(subAreaExample, pageable);
    }

    @Override
    public boolean exists(Integer id) {
        return this.subAreaDao.exists(id);
    }

    @Override
    public List<SubArea> findSubAreaByDecidedZoneId(Integer id) {
        return this.subAreaDao.findSubAreaByDecidedZoneId(id);
    }

    @Override
    public List<SubArea> findSubAreaByDecidedZoneIsNull() {
        return this.subAreaDao.findSubAreaByDecidedZoneIsNull();
    }


}
