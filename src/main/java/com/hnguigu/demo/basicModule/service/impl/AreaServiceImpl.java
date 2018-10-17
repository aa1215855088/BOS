package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.dao.AreaDao;
import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.basicModule.service.AreaService;
import com.hnguigu.demo.common.util.EhCacheUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
 * @create: 2018-10-06 15:56
 **/
@Service
@Transactional
@CacheConfig(cacheNames = "areaService")
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public <S extends Area> S save(Area area) {
        //清除缓存
        Area save = this.areaDao.save(area);
        if (save != null) {
            EhCacheUtil.removeAll("areaService");
        }
        return (S) area;
    }

    @Override
    @Cacheable
    public Page<Area> findAll(Pageable pageable) {
        return this.areaDao.findAll(pageable);
    }

    @Override
    public void delArea(Integer[] ids) {
        for (Integer id : ids) {
            if (id != null) {
                this.areaDao.delete(id);
            }
        }
        EhCacheUtil.removeAll("areaService");
    }

    @Override
    public Area findAreaById(Integer id) {
        return this.areaDao.findOne(id);
    }

    @Override
    @Cacheable
    public List<Area> findAll() {
        return this.areaDao.findAll();
    }

    @Override
    public void readAreaExcel(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
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
            save(area);
        }
    }
}
