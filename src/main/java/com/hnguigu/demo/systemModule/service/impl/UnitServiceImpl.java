package com.hnguigu.demo.systemModule.service.impl;

import com.hnguigu.demo.systemModule.dao.UnitDao;
import com.hnguigu.demo.systemModule.domain.Unit;
import com.hnguigu.demo.systemModule.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @create: 2018-10-09 16:27
 **/
@Service
@Transactional
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;

    @Override
    public List<Unit> findAll() {
        return this.unitDao.findAll();
    }

    @Override
    public Unit findOne(Integer id) {
        return this.unitDao.findOne(id);
    }
}
