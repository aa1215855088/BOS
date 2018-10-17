package com.hnguigu.demo.systemModule.service.impl;

import com.hnguigu.demo.systemModule.dao.SysUserDao;
import com.hnguigu.demo.systemModule.domain.SysUser;
import com.hnguigu.demo.systemModule.service.SysUserService;
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
 * @create: 2018-10-09 16:14
 **/
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public <S extends SysUser> S save(S entity) {
        return this.sysUserDao.save(entity);
    }

    @Override
    public List<SysUser> findAll() {
        return this.sysUserDao.findAll();
    }

    @Override
    public void delete(Integer id) {
        this.sysUserDao.delete(id);
    }

    @Override
    public SysUser findOne(Integer id) {
        return this.sysUserDao.findOne(id);
    }

    @Override
    public SysUser findSysUserByLoginName(String loginName) {
        return this.sysUserDao.findSysUserByLoginName(loginName);
    }

    @Override
    public boolean exists(Integer id) {
        return this.sysUserDao.exists(id);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return this.sysUserDao.existsByLoginName(loginName);
    }
}
