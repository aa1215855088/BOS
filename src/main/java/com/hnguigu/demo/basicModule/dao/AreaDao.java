package com.hnguigu.demo.basicModule.dao;

import com.hnguigu.demo.basicModule.domain.Area;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
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
 * @create: 2018-10-06 15:48
 **/
public interface AreaDao extends JpaRepository<Area, Integer> {
    /**
     * 添加and修改
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends Area> S save(S entity);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    @Override
    Page<Area> findAll(Pageable pageable);

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    void delete(Integer id);

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<Area> findAll();

    Area findAreaByName(String name);
}
