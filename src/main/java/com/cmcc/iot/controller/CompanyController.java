package com.cmcc.iot.controller;

import com.cmcc.iot.common.MyLoginException;
import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.Company;
import com.cmcc.iot.service.CompanyService;
import com.cmcc.iot.utills.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "company")
@Api("公司")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @ApiOperation(value = "获取公司列表", notes = "获取公司列表")
    @RequestMapping(value = "getcompanylist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "companyid", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "isvalid", dataType = "int", paramType = "query")
    })
    @ResponseBody
    public ResponseModel getCompanyList(
            @RequestParam(value = "companyname",required = false)String companyname,
            @RequestParam(value = "companyid",required = false)Integer companyid,
            @RequestParam(value = "isvalid",required = false)Integer isvalid){
        try {
            List<Company> companyList=companyService.getCompanyList(companyname,companyid,isvalid);
            return new ResponseModel(0L,"获取公司列表成功",companyList);
        }catch (MyLoginException e){
            e.printStackTrace();
            return new ResponseModel(1001L,e.getMessage(),null);
        }


    }


    @ApiOperation(value = "根据ID删除公司", notes = "根据ID删除公司")
    @RequestMapping(value = "deletecompany")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyid", dataType = "string", paramType = "query"),

    })
    @ResponseBody
    public ResponseModel deleteCompany( @RequestParam(value = "companyid")Integer companyid){
         int a =   companyService.removeCompanyByID(companyid);
         if(a>0)
            return new ResponseModel(0L,"成功删除"+a+"条公司",null);
         else
             return new ResponseModel(500L,"删除失败，未找到该公司ID",null);
    }

    @ApiOperation(value = "新增公司", notes = "新增公司")
    @RequestMapping(value = "addcompany")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "apikey", dataType = "string", paramType = "query"),

    })
    @ResponseBody
    public ResponseModel addCompany(
            @RequestParam(value = "companyname")String companyname,
            @RequestParam(value = "apikey",required = false)String apikey){
        Company company = new Company();
        company.setCompanyname(companyname);
        company.setIsvalid(1);
        company.setCreatetime(new Date());
        if (!StringUtils.isNullOrNone(apikey))
            company.setApikey(apikey);
        int a= companyService.addCompany(company);
        if(a>0)
            return new ResponseModel(0L,"成功添加"+a+"条公司记录",null);
        else
            return new ResponseModel(500L,"添加失败",null);
    }


    @ApiOperation(value = "更新公司", notes = "更新公司")
    @RequestMapping(value = "updatecompany")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "apikey", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "companyid", dataType = "int", paramType = "query")

    })
    @ResponseBody
    public ResponseModel updateCompany(
            @RequestParam(value = "companyid")Integer companyid,
            @RequestParam(value = "companyname")String companyname,
            @RequestParam(value = "apikey",required = false)String apikey
    ){
        Company company=companyService.getCompanyByid(companyid);
        company.setUpdatetime(new Date());
        company.setCompanyname(companyname);
        company.setApikey(apikey);
        int a = companyService.updateCompany(company);
        if(a>0)
            return new ResponseModel(0L,"成功更新"+a+"条公司记录",null);
        else
            return new ResponseModel(500L,"更新失败",null);
    }




    @ApiOperation(value = "根据ID获取公司详情", notes = "根据ID获取公司详情")
    @RequestMapping(value = "getcompanybyid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyid", dataType = "int", paramType = "query")
    })
    @ResponseBody
    public ResponseModel getCompanyByid( @RequestParam(value = "companyid")Integer companyid){
        Company company=companyService.getCompanyByid(companyid);
        return new ResponseModel(0L,"成功",company);

    }

    @ApiOperation(value = "锁定、解锁公司", notes = "锁定、解锁公司")
    @RequestMapping(value = "lockcompanybyid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyid", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "isvalid", dataType = "int", paramType = "query")
    })
    @ResponseBody
    public ResponseModel lockCompany(@RequestParam(value = "companyid")Integer companyid,
                                     @RequestParam(value = "isvalid")Integer isvalid){
        Company company=new Company();
        company.setId(companyid);
        company.setIsvalid(isvalid);
        int a=companyService.updateCompany(company);
        if(a>0)
            return new ResponseModel(0L,"成功锁定/解锁ID为："+companyid+"的公司",null);
        else
            return new ResponseModel(500L,"锁定失败/解锁",null);

    }












}
