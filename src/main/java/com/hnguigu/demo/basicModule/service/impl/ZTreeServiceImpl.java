package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.dao.ZTreeDao;
import com.hnguigu.demo.basicModule.domain.ZTree;
import com.hnguigu.demo.basicModule.service.ZTreeService;
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
 * @create: 2018-10-16 16:31
 **/
@Service
@Transactional
public class ZTreeServiceImpl implements ZTreeService {
    @Autowired
    private ZTreeDao zTreeDao;


    @Override
    public List<ZTree> systemsManagement() {
        return this.zTreeDao.findAllByPId(100);
    }

    @Override
    public List<ZTree> basicFunction() {
        return this.zTreeDao.findByPIdNot(100);
    }
}
