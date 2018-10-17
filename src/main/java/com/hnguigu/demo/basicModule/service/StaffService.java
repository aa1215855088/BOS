package com.hnguigu.demo.basicModule.service;

import com.hnguigu.demo.basicModule.domain.Staff;
import com.hnguigu.demo.common.exception.MyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-29 08:03
 **/
public interface StaffService {
    /**
     * 添加取派员
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends Staff> S save(S entity) throws MyException;

    Page<Staff> findAll(Pageable pageable);


    /**
     * 作废
     *
     * @param ids
     */
    void updateDeleteTagById(Integer[] ids);

    /**
     * 还原
     * @param ids
     */
    void restoreDeleteTagById(Integer[] ids);

    /**
     * 跟据ID查询取派员
     * @param id
     * @return 取派员
     */
    Staff findStaffById(Integer id);

    /**
     * 修改取派员信息
     * @param staff
     */
    void updateStaff(Staff staff) throws MyException;

    <S extends Staff> Page<S> findAll(Staff staff, Pageable pageable);

    List<Staff> findAll();
}
