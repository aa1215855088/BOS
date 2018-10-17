package com.hnguigu.demo.common.Realm;

import com.hnguigu.demo.systemModule.domain.SysPrivilege;
import com.hnguigu.demo.systemModule.domain.SysRole;
import com.hnguigu.demo.systemModule.domain.SysUser;
import com.hnguigu.demo.systemModule.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
 * @create: 2018-10-09 19:33
 **/
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SysUser sysUser = (SysUser) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        for (SysRole sysRole : sysUser.getRole()) {
            authenticationInfo.addRole(sysRole.getName());
            for (SysPrivilege sysPrivilege : sysRole.getPrivilege()) {
                if (!StringUtils.isBlank(sysPrivilege.getPrivilegeCode())) {
                    authenticationInfo.addStringPermission(sysPrivilege.getPrivilegeCode());
                }
            }
        }
        return authenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = (String) token.getPrincipal();
        SysUser user = this.sysUserService.findSysUserByLoginName(loginName);
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()
        );
        return simpleAuthenticationInfo;
    }
}
