package com.hnguigu.demo.basicModule.dao;

import com.hnguigu.demo.basicModule.domain.DecidedZone;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
 * @create: 2018-10-14 15:46
 **/
public interface DecidedZoneDao extends JpaRepository<DecidedZone,Integer> {
    /**
     * 分页查询
     * @param pageable
     * @return
     */
    @Override
    Page<DecidedZone> findAll(Pageable pageable);

    /**
     * 有条件的分页查询
     * @param example
     * @param pageable
     * @param <S>
     * @return
     */
    @Override
    <S extends DecidedZone> Page<S> findAll(Example<S> example, Pageable pageable);

    /**
     * 跟据ID删除
     * @param id
     */
    @Override
    void delete(Integer id);

    /**
     * 添加修改
     * @param s
     * @param <S>
     * @return
     */
    @Override
    <S extends DecidedZone> S save(S s);

    @Override
    boolean exists(Integer id);

    void deleteById(Integer id);
}
