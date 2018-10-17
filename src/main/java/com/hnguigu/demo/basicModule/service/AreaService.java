package com.hnguigu.demo.basicModule.service;

import com.hnguigu.demo.basicModule.domain.Area;
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
 * @create: 2018-10-06 15:55
 **/
public interface AreaService {
    /**
     * 添加and修改
     *
     * @param area
     * @param <S>
     * @return
     */
    <S extends Area> S save(Area area);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    Page<Area> findAll(Pageable pageable);

    /**
     * 删除区域
     *
     * @param ids
     */
    void delArea(Integer[] ids);

    /**
     * 根据ID查询区域
     *
     * @param id
     * @return
     */
    Area findAreaById(Integer id);

    /**
     * 查询所有
     *
     * @return
     */
    List<Area> findAll();

    void readAreaExcel(InputStream inputStream) throws IOException;
}
