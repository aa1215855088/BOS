package com.hnguigu.demo.basicModule.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.hnguigu.demo.basicModule.dao.StaffDao;
import com.hnguigu.demo.basicModule.domain.Staff;
import com.hnguigu.demo.common.exception.MyException;
import com.hnguigu.demo.basicModule.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-29 08:04
 **/
@Service
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffDao staffDao;

    @Override
    public <S extends Staff> S save(S entity) throws MyException {
        if (entity == null) {
            throw new MyException("没有参数");
        }
        entity.setDeleteTag("0");
        if (StringUtils.isEmpty(entity.getPda())) {
            entity.setPda("否");
        }
        return this.staffDao.save(entity);
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return this.staffDao.findAll(pageable);
    }

    @Override
    public void updateDeleteTagById(Integer[] ids) {

        for (Integer id : ids) {
            if (id != null) {
                this.staffDao.updateDeleteTagById(id);
            }
        }

    }

    @Override
    public void restoreDeleteTagById(Integer[] ids) {
        for (Integer id : ids) {
            if (id != null) {

                this.staffDao.restoreDeleteTagById(id);
            }
        }
    }

    @Override
    public Staff findStaffById(Integer id) {
        return this.staffDao.findStaffById(id);
    }

    @Override
    public void updateStaff(Staff staff) throws MyException {
        if (staff == null) {
            throw new MyException("没有参数");
        }
        if (StringUtils.isEmpty(staff.getPda())) {
            staff.setPda("否");
        }
        this.staffDao.save(staff);
    }

    @Override
    public <S extends Staff> Page<S> findAll(Staff staff, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Staff> example = Example.of(staff, matcher);
        return (Page<S>) this.staffDao.findAll(example, pageable);
    }

    @Override
    public List<Staff> findAll() {
        return this.staffDao.findAll();
    }
}
