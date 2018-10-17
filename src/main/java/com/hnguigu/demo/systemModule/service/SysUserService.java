package com.hnguigu.demo.systemModule.service;

import com.hnguigu.demo.systemModule.domain.SysUser;

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
 * @create: 2018-10-09 16:14
 **/
public interface SysUserService {
    /**
     * 添加
     *
     * @param entity
     * @param <S>
     * @return
     */
    <S extends SysUser> S save(S entity);


    /**
     * 查询所有
     *
     * @return
     */
    List<SysUser> findAll();

    /**
     * 跟据ID删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 跟据ID删除
     *
     * @param id
     * @return
     */
    SysUser findOne(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param loginName
     * @return
     */
    SysUser findSysUserByLoginName(String loginName);

    boolean exists(Integer id);

    boolean existsByLoginName(String loginName);
}
