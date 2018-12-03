package com.cmcc.iot.controller;

import com.cmcc.iot.common.MyLoginException;
import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.Company;
import com.cmcc.iot.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "company")
@Api("公司")
public class CompanyController {

    @Autowired
    CompanyService companyService;


    @RequestMapping(value = "getcompanylist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyname", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "companyid", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "isvalid", dataType = "int", paramType = "query")
    })
    @ResponseBody
    public ResponseModel getCompanyList(
            @RequestParam(value = "companyname",required = false)String companyname,
            @RequestParam(value = "companyid",required = false)Integer companyid,
            @RequestParam(value = "isvalid",required = false,defaultValue = "1")Integer isvalid){
        try {
            List<Company> companyList=companyService.getCompanyList(companyname,companyid,isvalid);
            return new ResponseModel(0L,"获取公司列表成功",companyList);
        }catch (MyLoginException e){
            e.printStackTrace();
            return new ResponseModel(1001L,e.getMessage(),null);
        }


    }


















}
