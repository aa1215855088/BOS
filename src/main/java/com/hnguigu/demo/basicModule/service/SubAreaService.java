package com.hnguigu.demo.basicModule.service;

import com.hnguigu.demo.basicModule.domain.SubArea;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.InputStream;
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
 * @create: 2018-10-07 16:11
 **/
public interface SubAreaService {
    /**
     * 添加和修改
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends SubArea> S save(S entity);

    /**
     * 跟据ID查询分区
     *
     * @param id
     * @return
     */
    SubArea findOne(Integer id);

    /**
     * 跟据ID删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 分页查询
     *
     * @return page
     */
    Page<SubArea> findAll(Pageable pageable);

    /**
     * 查询所有
     *
     * @return
     */
    List<SubArea> findAll();

    /**
     * 导入Excel
     *
     * @param inputStream
     * @throws IOException
     */
    void readSubAreaExcel(InputStream inputStream) throws IOException;

    /**
     * 有条件的分页查询
     *
     * @param subArea
     * @param pageable
     * @param <S>
     * @return
     */
    <S extends SubArea> Page<S> findAll(SubArea subArea, Pageable pageable);

    boolean exists(Integer id);

    /**
     * 根据定区ID查询分区
     * @param id
     * @return
     */
    List<SubArea> findSubAreaByDecidedZoneId(Integer id);

    List<SubArea> findSubAreaByDecidedZoneIsNull();
}
