package com.example.car.realm;

import com.example.car.pojo.SysUser;
import com.example.car.service.MenuSerivce;
import com.example.car.service.RoleService;
import com.example.car.service.UserService;
import com.example.car.utils.MD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuSerivce menuSerivce;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String username = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        String pass = new String(password);
        String md5Pass = MD5Utils.md5(pass,username,1024);
        //根据用户名到DB查询
        SysUser pojo = userService.findByUsername(username);
        if (pojo==null){
            throw  new UnknownAccountException("账号不存在！！");
        }
        if (!pojo.getPassword().equals(md5Pass)){
            throw new IncorrectCredentialsException("密码不正确！");
        }
        if (pojo.getStatus()==0){
            throw new LockedAccountException("账号被冻结！");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(pojo,pass,getName());
        return simpleAuthenticationInfo;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser pojo = (SysUser)principals.getPrimaryPrincipal();
        Long userId = pojo.getUserId();
        //根据用户id查询角色信息和权限信息
        List<String> roleNamesByUid = roleService.findRoleNamesByUid(userId);

        List<String> menuByUid = menuSerivce.findMenuByUid(userId);

        //封装角色和权限返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleNamesByUid);
        simpleAuthorizationInfo.addStringPermissions(menuByUid);
        return simpleAuthorizationInfo;
    }


}
