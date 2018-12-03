package com.cmcc.iot.controller;

import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.User;
import com.cmcc.iot.service.UserService;
import com.cmcc.iot.utills.MySecurityUtil;
import com.cmcc.iot.utills.RedisUtil;
import com.cmcc.iot.utills.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("user")
@Api(description = "用户")
public class UserController {

    @Autowired
    @Qualifier(value = "redisUtil")
    RedisUtil redisUtil;

    @Autowired
    UserService userService;


//    @ApiOperation(value = "测试redis", notes = "测试redis")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userid", dataType = "int", paramType = "query")
//    })
//    @RequestMapping(value = "testredis" ,method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseModel testredis(@RequestParam(value ="userid", required = false,defaultValue = "1") Long userid){
//        System.out.println(userid);
//        User user = new User();
//        user.setCompanyid(1);
//        user.setCreatetime(new Date());
//        user.setLoginname("登录名");
//        user.setName("名字");
//        redisUtil.set("userinfo",user);
//        User a =(User)redisUtil.get("userinfo");
//        return new ResponseModel(0L,"成功",a);
//    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "password", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phonenum", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roleids", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roletype", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "companyid", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "adduser" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel adduser(@RequestParam(value ="loginname", required = false) String loginname,
                                 @RequestParam(value ="password", required = false) String password,
                                 @RequestParam(value ="phonenum", required = false) String phonenum,
                                 @RequestParam(value ="name", required = false) String name,
                                 @RequestParam(value ="companyid", required = false) Integer companyid,
                                 @RequestParam(value ="roleids", required = false ,defaultValue = "") String roleids,
                                 @RequestParam(value ="roletype", required = false,defaultValue = "2") Integer roletype ){

        List<User> haduserList=userService.listUserByCondition(loginname,null,null,null,null);
        if(haduserList.size()>0)
            return new ResponseModel(500l,"用户名已存在",null);
        if (StringUtils.isNullOrNone(loginname))
            return new ResponseModel(500l,"登录名不能未空",null);
        if (StringUtils.isNullOrNone(password))
            return new ResponseModel(500l,"密码不能为空",null);
        if (StringUtils.isNullOrNone(phonenum))
            return new ResponseModel(500l,"手机号码不能为空",null);
        if (StringUtils.isNullOrNone(name))
            return new ResponseModel(500l,"姓名不能为空",null);
        if(companyid==null)
            return new ResponseModel(500l,"公司id不能为空",null);




        User user = new User();
        user.setLoginname(loginname);
        user.setPassword(MySecurityUtil.encode(password,loginname));
        user.setName(name);
        user.setPhonenum(phonenum);
        user.setRoleids(roleids);
        user.setRoletype(roletype);
        user.setCompanyid(companyid);
        user.setIsvalid(1);
        user.setCreatetime(new Date());
        userService.addUser(user);
        return new ResponseModel(0l,"添加用户成功",user.getLoginname());
    }





    @ApiOperation(value = "根据ID获取用户", notes = "根据ID获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "getuserbyid" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getuserbyid(@RequestParam(value ="userid", required = true) Integer userid){
       User user= userService.getUserById(userid);
       return new ResponseModel(0L,"成功",user);
    }


    @ApiOperation(value = "根据ID删除用户", notes = "根据ID删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "removeuserbyid" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel removeuserbyid(@RequestParam(value ="userid", required = true) Integer userid){
        int a= userService.removeUserById(userid);
        return new ResponseModel(0L,"成功删除"+a+"条",a);
    }




    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginname", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phonenum", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "isvalid", dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "getuserlist" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getuserlist(@RequestParam(value ="loginname", required = false) String loginname,
                                     @RequestParam(value ="name", required = false) String name,
                                     @RequestParam(value ="phonenum", required = false) String phonenum,
                                     @RequestParam(value ="companyid", required = false) Integer companyid,
                                     @RequestParam(value ="isvalid", required = false,defaultValue = "1") String isvalid
                                     ){
        List<User> userList= userService.listUserByCondition(loginname,name,phonenum,isvalid,companyid);
        return new ResponseModel(0L,"成功",userList);
    }


    @ApiOperation(value = "根据ID更新用户", notes = "根据ID更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "name", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phonenum", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "updateuserbyid" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel updateuserbyid(@RequestParam(value ="userid", required = true) Integer userid,
                                        @RequestParam(value ="name", required = false) String name,
                                        @RequestParam(value ="phonenum", required = false) String phonenum,
                                        @RequestParam(value ="password", required = false) String password,
                                        @RequestParam(value ="companyid", required = false) Integer companyid,
                                        @RequestParam(value ="roleids", required = false ,defaultValue = "") String roleids,
                                        @RequestParam(value ="roletype", required = false,defaultValue = "2") Integer roletype){





        User user = userService.getUserById(userid);
        user.setName(name);
        user.setPhonenum(phonenum);
        user.setCompanyid(companyid);
        user.setRoleids(roleids);
        user.setRoletype(roletype);
        if(!StringUtils.isNullOrNone(phonenum))
            user.setPassword(MySecurityUtil.encode(password,user.getLoginname()));

        int a= userService.updateUserById(user);
        return new ResponseModel(0L,"成功更新"+a+"条",a);
    }

}
