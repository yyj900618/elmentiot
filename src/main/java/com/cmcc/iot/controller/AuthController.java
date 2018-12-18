package com.cmcc.iot.controller;

import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.Auth;
import com.cmcc.iot.model.Company;
import com.cmcc.iot.model.Role;
import com.cmcc.iot.model.User;
import com.cmcc.iot.service.AuthService;
import com.cmcc.iot.service.CompanyService;
import com.cmcc.iot.service.RoleService;
import com.cmcc.iot.utills.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class AuthController {

    @Autowired
    RoleService roleService;

    @Autowired
    AuthService authService;

    @Autowired
    CompanyService companyService;

    @Resource(name = "ehCacheManager")
    EhCacheManager ehCacheManager;

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "login" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel login(@RequestParam(value = "loginname") String loginname,
                                @RequestParam(value = "password") String password,
                               HttpSession session){
        Subject subject = SecurityUtils.getSubject();

        Set<String> permissionList= new HashSet<>();
        List<String> authidList= new ArrayList<>();
        if (!subject.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(loginname,password);
                subject.login(token);
                User loginedUser=(User)subject.getPrincipal();
                loginedUser.setPassword(null);
                String rolestr=  loginedUser.getRoleids();
                List<String> roleList= Arrays.asList(rolestr.split(","));
                for (String roleid:roleList){
                    Role role=roleService.getroleById(Integer.parseInt(roleid));
                    if(role!=null&&role.getAuth()!=null){
                        authidList.addAll(Arrays.asList(role.getAuth().split(",")));
                    }
                }
                for (String authid:authidList){
                    Auth auth=authService.getAuthByID(Integer.parseInt(authid));
                    if (auth!=null&&!StringUtils.isNullOrNone(auth.getShriostr()))
                        permissionList.add(auth.getShriostr());
                }
                loginedUser.setPermissionList(permissionList);
                Integer companyid=loginedUser.getCompanyid(); //登录用户所属公司


                session.setAttribute("userinfo",loginedUser);
                return new ResponseModel(0l,"登录成功",loginedUser);
            }catch (UnknownAccountException une){
                return new ResponseModel(401l,"登录失败",une.getMessage());
            }catch (LockedAccountException locke){
                return new ResponseModel(401l,"登录失败",locke.getMessage());
            }catch (AuthenticationException ae){
                return new ResponseModel(401l,"登录失败","密码错误");
            }
            catch (Exception e){
                e.printStackTrace();
                return new ResponseModel(401l,"登录失败，未知错误",e.getMessage());
            }
        }else{
            User loginedUser=(User)session.getAttribute("userinfo");
            return new ResponseModel(0l,"账号已登录",loginedUser);
        }

    }

    @ApiOperation(value = "注销", notes = "注销")
    @RequestMapping(value = "logout" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel logout(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionid = session.getId().toString();
        User userinfo = (User) session.getAttribute("userinfo");
        if (userinfo != null) {
            subject.logout();
            Cache<String, Deque<String>> cache= ehCacheManager.getCache("shiro-activeSessionCache");
            Deque<String> deque = cache.get(userinfo.getLoginname());
            if (deque != null && deque.contains(sessionid))
                deque.remove(sessionid);
            return new ResponseModel(0L,"注销成功",null);
        }else {
            return new ResponseModel(0L,"您尚未登录",null);
        }
    }

    @ApiOperation(value = "是否已登录", notes = "是否已登录")
    @RequestMapping(value = "islogin" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel islogin(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Object kickout=session.getAttribute("kickout");

        if(!subject.isAuthenticated())
            return new ResponseModel(1001L,"未登录",null);
        else if(kickout!=null)
            return new ResponseModel(10086L,"该账号已在其他地方登陆，您已被踢出",null);
        else
            return new ResponseModel(0L,"已登录",null);
    }


}
