package com.cmcc.iot.service;

import com.cmcc.iot.common.MyLoginException;
import com.cmcc.iot.mapper.CompanyMapper;
import com.cmcc.iot.model.Company;
import com.cmcc.iot.model.CompanyExample;
import com.cmcc.iot.model.User;
import com.cmcc.iot.utills.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    CompanyMapper companyMapper;


    public Company getCompanyByid(Integer id){
        Company company= companyMapper.selectByPrimaryKey(id);
        if (company!=null){
            if (company.getIsvalid()==null||company.getIsvalid()==0)
                return null;
            else
                return company;
        }
        return null;
    }


    public List<Company> getCompanyList(String companyname,Integer companyid,Integer isvalid) throws MyLoginException {
        CompanyExample example = new CompanyExample();
        CompanyExample.Criteria criteria = example.createCriteria();
        User loginuser=(User) SecurityUtils.getSubject().getPrincipal();
        if (loginuser==null)
            throw new MyLoginException("请登录后重试");
        Integer roletype=loginuser.getRoletype();
        if(isvalid!=null)
            criteria.andIsvalidEqualTo(isvalid);
        if (!StringUtils.isNullOrNone(companyname))
            criteria.andCompanynameLike("%"+companyname+"%");
        if (roletype!=1)
            criteria.andIdEqualTo(loginuser.getCompanyid());
        else if(companyid!=null)
            criteria.andIdEqualTo(companyid);



        example.setOrderByClause("id asc");
        return companyMapper.selectByExample(example);
    }

    public int removeCompanyByID(Integer companyid){
       return companyMapper.deleteByPrimaryKey(companyid);
    }

    public int addCompany(Company company){
        return companyMapper.insertSelective(company);
    }

    public int updateCompany(Company company){
        return companyMapper.updateByPrimaryKeySelective(company);
    }

}
