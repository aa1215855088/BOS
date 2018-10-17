package com.hnguigu.demo.basicModule.dao;

import com.hnguigu.demo.basicModule.domain.SubArea;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
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
 * @create: 2018-10-07 16:05
 **/
public interface SubAreaDao extends JpaRepository<SubArea, Integer> {
    /**
     * 添加和修改
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends SubArea> S save(S entity);

    /**
     * 跟据ID查询分区
     *
     * @param id
     * @return
     */
    @Override
    SubArea findOne(Integer id);

    /**
     * 跟据ID删除
     *
     * @param id
     */
    @Override
    @Transactional
    void delete(Integer id);

    /**
     * 查询所有
     *
     * @return page
     */
    @Override
    Page<SubArea> findAll(Pageable pageable);

    /**
     * 没有分页
     *
     * @return
     */
    @Override
    List<SubArea> findAll();

    /**
     * 有条件的分页查询
     *
     * @param example
     * @param pageable
     * @param <S>
     * @return
     */
    @Override
    <S extends SubArea> Page<S> findAll(Example<S> example, Pageable pageable);

    /**
     * 判断是否存在
     *
     * @param id
     * @return
     */
    @Override
    boolean exists(Integer id);

    /**
     * 根据定区ID查询分区
     *
     * @param id
     * @return
     */
    List<SubArea> findSubAreaByDecidedZoneId(Integer id);

    /**
     * 查询没有定区的分区
     * @return
     */
    List<SubArea> findSubAreaByDecidedZoneIsNull();
}
