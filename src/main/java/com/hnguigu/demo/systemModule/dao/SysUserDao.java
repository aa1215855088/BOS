package com.hnguigu.demo.systemModule.dao;

import com.hnguigu.demo.systemModule.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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
 * @create: 2018-10-09 16:07
 **/
public interface SysUserDao extends JpaRepository<SysUser, Integer> {
    /**
     * 添加
     *
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends SysUser> S save(S entity);


    /**
     * 查询所有
     *
     * @return
     */
    @Override
    List<SysUser> findAll();

    /**
     * 跟据ID删除
     *
     * @param id
     */
    @Override
    void delete(Integer id);

    /**
     * 跟据ID删除
     *
     * @param id
     * @return
     */
    @Override
    SysUser findOne(Integer id);

    /**
     * 根据用户名查询用户
     *
     * @param loginName
     * @return
     */
    @Transactional
    SysUser findSysUserByLoginName(String loginName);

    /**
     * 判断用户名是否存在
     *
     * @param id
     * @return
     */
    @Override
    boolean exists(Integer id);

    boolean existsByLoginName(String loginName);
}
