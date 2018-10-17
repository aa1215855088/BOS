package com.hnguigu.demo.systemModule.service.impl;

import com.hnguigu.demo.systemModule.dao.SysPrivilegeDao;
import com.hnguigu.demo.systemModule.domain.SysPrivilege;
import com.hnguigu.demo.systemModule.service.SysPrivilegeService;
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
 * @create: 2018-10-13 15:18
 **/
@Service
@Transactional
public class SysPrivilegeServiceImpl implements SysPrivilegeService {

    @Autowired
    private SysPrivilegeDao sysPrivilegeDao;

    @Override
    public List<SysPrivilege> findAll() {
        return this.sysPrivilegeDao.findAll();
    }

    @Override
    public <S extends SysPrivilege> S save(S s) {
        return this.sysPrivilegeDao.save(s);
    }

    @Override
    public SysPrivilege findOne(Integer id) {
        return this.sysPrivilegeDao.findOne(id);
    }
}
