package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.basicModule.domain.SubArea;
import com.hnguigu.demo.basicModule.service.SubAreaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

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
 * @create: 2018-10-11 15:11
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SubAreaServiceImplTest {

    @Autowired
    private SubAreaService subAreaService;

    @Test
    public void findAll() {
        SubArea one = this.subAreaService.findOne(1);
        SubArea ose = this.subAreaService.findOne(1);
        System.out.println(one);
        System.out.println(ose);
    }

    @Test
    public void findAll1() {
        SubArea subArea = new SubArea();
        Area area = new Area();
        area.setProvince("北京市");
        area.setCity("");
        subArea.setArea(area);
        PageRequest pageRequest = new PageRequest(0, 30);
        Page<SubArea> subAreas = this.subAreaService.findAll(subArea, pageRequest);
        List<SubArea> content = subAreas.getContent();
        for (SubArea subArea1 : content) {
            System.out.println(subArea1);
        }
    }
}