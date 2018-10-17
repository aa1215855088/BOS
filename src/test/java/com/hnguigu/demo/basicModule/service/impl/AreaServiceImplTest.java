package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.domain.Area;
import com.hnguigu.demo.basicModule.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * @create: 2018-10-11 17:20
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AreaServiceImplTest {

    @Autowired
    private AreaService areaService;
    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(1,2);
        Page<Area> serviceAll = this.areaService.findAll(pageRequest);
        Page<Area> serviceAll1 = this.areaService.findAll(pageRequest);
        Page<Area> serviceAll2 = this.areaService.findAll(pageRequest);
        serviceAll.forEach(System.out::println);
        serviceAll1.forEach(System.out::println);
        serviceAll2.forEach(System.out::println);
    }
}