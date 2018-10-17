package com.hnguigu.demo.systemModule.dao;

import com.hnguigu.demo.systemModule.domain.SysRole;
import org.springframework.data.repository.CrudRepository;

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
 * @create: 2018-10-13 15:09
 **/
public interface SysRoleDao extends CrudRepository<SysRole,Integer> {

    /**
     * 查询所有角色信息
     * @return
     */
    @Override
    List<SysRole> findAll();

    @Override
    <S extends SysRole> S save(S s);
}
