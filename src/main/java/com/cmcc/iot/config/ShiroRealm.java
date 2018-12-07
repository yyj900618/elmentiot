package com.cmcc.iot.config;

import com.cmcc.iot.model.Auth;
import com.cmcc.iot.model.Role;
import com.cmcc.iot.model.User;
import com.cmcc.iot.service.AuthService;
import com.cmcc.iot.service.CompanyService;
import com.cmcc.iot.service.RoleService;
import com.cmcc.iot.service.UserService;
import com.cmcc.iot.utills.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    @Lazy
    UserService userservice;

    @Autowired
    @Lazy
    RoleService roleService;

    @Autowired
    @Lazy
    AuthService authService;

    @Autowired
    @Lazy
    CompanyService companyService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User userinfo=(User)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String roleids=userinfo.getRoleids();
        List<String> roleidList=new ArrayList<>();
        Set<String> rolestr= new HashSet<>();
        Set<String> permissionsstr= new HashSet<>();
        List<String> authidList= new ArrayList<>();

        if (StringUtils.isNullOrNone(roleids))
             roleidList= Arrays.asList(roleids.split(","));
        for (String roleid: roleidList){
           Role role= roleService.getroleById(Integer.parseInt(roleid));
           if (role!=null) {
               rolestr.add(role.getRolename());
               String authstr=role.getAuth();
               if (StringUtils.isNullOrNone(authstr))
                    authidList.addAll(Arrays.asList(authstr.split(","))) ;
           }
        }
        for (String authid:authidList){
            Auth auth=authService.getAuthByID(Integer.parseInt(authid));
            if (auth!=null&&!StringUtils.isNullOrNone(auth.getShriostr()))
                permissionsstr.add(auth.getShriostr());
        }
        simpleAuthorizationInfo.setRoles(rolestr);
        simpleAuthorizationInfo.setStringPermissions(permissionsstr);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("验证当前Subject时获取到token为：" + token.toString());
        String loginname=token.getUsername();
        User user=userservice.getUserByLoginname(loginname);
        if (user==null)
            throw new UnknownAccountException("没有找到账号信息");//没有找到账号信息
        if(user.getIsvalid()!=1||user.getIsvalid()==null)
            throw new LockedAccountException("该账号已被禁用");
        if (companyService.getCompanyByid(user.getCompanyid())==null)
            throw new LockedAccountException("未找到该账号所属公司或该账号所属公司已被禁用");
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getLoginname());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return authenticationInfo;
    }
}
