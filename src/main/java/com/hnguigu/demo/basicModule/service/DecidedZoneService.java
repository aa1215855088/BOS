package com.hnguigu.demo.basicModule.service;

import com.hnguigu.demo.basicModule.domain.DecidedZone;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface DecidedZoneService {
    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<DecidedZone> findAll(Pageable pageable);

    /**
     * 有条件的分页查询
     *
     * @param decidedZone
     * @param pageable
     * @param <S>
     * @return
     */
    <S extends DecidedZone> Page<S> findAll(DecidedZone decidedZone, Pageable pageable);

    /**
     * 跟据ID删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加修改
     *
     * @param s
     * @param <S>
     * @return
     */
    <S extends DecidedZone> S save(S s);

    boolean exists(Integer id);

    void deleteById(Integer id);
}
