package com.cmcc.iot.controller;

import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.Auth;
import com.cmcc.iot.model.Role;
import com.cmcc.iot.service.AuthService;
import com.cmcc.iot.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "role")
@Api(description = "角色")
public class RoleController {
    @Autowired
    RoleService roleService;

    @Autowired
    AuthService authService;



    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roletype", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rolename", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/rolelist",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getRoleList(
            @RequestParam(value = "roletype",required = false) Integer roletype,
            @RequestParam(value = "rolename",required = false) String rolename){
        List<Role> roleList=roleService.getRoleByCondition(roletype,rolename,1);
        for (Role role:roleList){
            List<String> authList= new ArrayList<>();
            String auth_str=role.getAuth();
            List<String> authidList= Arrays.asList(auth_str.split(","));
            for (String authid: authidList){
                Auth auth=authService.getAuthByID(Integer.parseInt(authid));
                authList.add(auth.getDescription());
            }
            role.setAuthList(authList);
        }
        return new ResponseModel(0L,"获取角色列表成功",roleList);
    }



    @ApiOperation(value = "根据ID删除角色", notes = "根据ID删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "removerolebyid" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel removeuserbyid(@RequestParam(value ="roleid", required = true) Integer roleid){
        int a= roleService.removeRoleById(roleid);
        return new ResponseModel(0L,"成功删除"+a+"条",a);
    }


    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roletype", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rolename", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "auth", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "addrole" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel addRole(@RequestParam(value = "roletype" )Integer roletype,
                                 @RequestParam(value = "rolename" )String rolename,
                                 @RequestParam(value = "auth" )String auth,
                                 @RequestParam(value = "description" )String description){
        Role role = new Role();
        role.setRoletype(roletype);
        role.setAuth(auth);
        role.setDescription(description);
        role.setRolename(rolename);
        role.setCreatetime(new Date());
        role.setUpdatetime(new Date());
        role.setIsvalid(1);
        int a=roleService.addRole(role);
        return new ResponseModel(0l,"成功添加"+a+"条角色",null);
    }


    @ApiOperation(value = "更新角色", notes = "更新角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "roletype", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "rolename", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "auth", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "updaterole" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel updaterole(
                                 @RequestParam(value = "roleid" )Integer roleid,
                                 @RequestParam(value = "roletype" )Integer roletype,
                                 @RequestParam(value = "rolename" )String rolename,
                                 @RequestParam(value = "auth" )String auth,
                                 @RequestParam(value = "description" )String description){
        Role role = new Role();
        role.setId(roleid);
        role.setRoletype(roletype);
        role.setAuth(auth);
        role.setDescription(description);
        role.setRolename(rolename);
//        role.setCreatetime(new Date());
        role.setUpdatetime(new Date());
        role.setIsvalid(1);
        int a=roleService.updateRole(role);
        return new ResponseModel(0l,"成功更新"+a+"条角色",null);
    }





    @ApiOperation(value = "获取权限列表", notes = "获取权限列表")
    @RequestMapping(value = "getallauth" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getallauth(){
        List<Auth> authList=authService.getAllAuth();
        return new ResponseModel(0l,"成功",authList);
    }



    @ApiOperation(value = "根据id获取角色", notes = "根据id获取角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleid", dataType = "int", paramType = "query")
    })
    @RequestMapping(value = "getrolebyid" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getRoleByID(@RequestParam(value = "roleid")Integer roleid){
       Role role = roleService.getroleById(roleid);
       return new ResponseModel(0l,"成功",role);

    }

}
