package com.hnguigu.demo.basicModule.service.impl;

import com.hnguigu.demo.basicModule.dao.DecidedZoneDao;
import com.hnguigu.demo.basicModule.domain.DecidedZone;
import com.hnguigu.demo.basicModule.service.DecidedZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 * @create: 2018-10-14 15:49
 **/
@Service
@Transactional
public class DecidedZoneServiceImpl implements DecidedZoneService {
    @Autowired
    private DecidedZoneDao decidedZoneDao;

    @Override
    public Page<DecidedZone> findAll(Pageable pageable) {
        return this.decidedZoneDao.findAll(pageable);
    }

    @Override
    public <S extends DecidedZone> Page<S> findAll(DecidedZone decidedZone, Pageable pageable) {
        Example<DecidedZone> decidedZoneExample = Example.of(decidedZone);
        return (Page<S>) this.decidedZoneDao.findAll(decidedZoneExample, pageable);
    }

    @Override
    public void delete(Integer id) {
        this.decidedZoneDao.delete(id);
    }

    @Override
    public <S extends DecidedZone> S save(S s) {
        return this.decidedZoneDao.save(s);
    }

    @Override
    public boolean exists(Integer id) {
        return this.decidedZoneDao.exists(id);
    }

    @Override
    public void deleteById(Integer id) {
        this.decidedZoneDao.delete(id);
    }
}
