package com.hnguigu.demo.systemModule.service.impl;

import com.hnguigu.demo.systemModule.dao.SysRoleDao;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.service.SysRoleService;
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
 * @create: 2018-10-13 15:12
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {


    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public List<SysRole> findAll() {
        return this.sysRoleDao.findAll();
    }


    @Override
    @Transactional
    public <S extends SysRole> S save(S s) {
        return this.sysRoleDao.save(s);
    }

    @Override
    public SysRole findOne(Integer id) {
        return this.sysRoleDao.findOne(id);
    }
}
