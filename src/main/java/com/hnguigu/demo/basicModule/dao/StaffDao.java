package com.hnguigu.demo.basicModule.dao;


import com.hnguigu.demo.basicModule.domain.Staff;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: BOS
 * @description: ${description}
 * @author: 徐子楼
 * @create: 2018-09-29 08:01
 **/
public interface StaffDao extends JpaRepository<Staff, Integer> {
    /**
     * 添加取派员
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends Staff> S save(S entity);

    /**
     * 作废
     *
     * @param id
     */
    @Modifying
    @Transactional
    @Query("update Staff s set s.deleteTag='1' where s.id =?1")
    void updateDeleteTagById(Integer id);

    /**
     * 分页查询l
     *
     * @param pageable
     * @return
     */
    @Override
    Page<Staff> findAll(Pageable pageable);

    /**
     * 还原
     *
     * @param id
     */
    @Modifying
    @Transactional
    @Query("update Staff s set s.deleteTag='0' where s.id =?1")
    void restoreDeleteTagById(Integer id);

    /**
     * 跟据ID查询
     * @param id
     * @return
     */
    @Query("from Staff s where s.id=:id ")
    Staff findStaffById(@Param("id") Integer id);

    /**
     * 有条件的分页查询
     * @param example
     * @param pageable
     * @param <S>
     * @return
     */
    @Override
    <S extends Staff> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    List<Staff> findAll();
}
