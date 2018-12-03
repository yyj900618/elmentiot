package com.cmcc.iot.service;

import com.cmcc.iot.mapper.AuthMapper;
import com.cmcc.iot.model.Auth;
import com.cmcc.iot.model.AuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    AuthMapper authMapper;

    @Cacheable(value = "auth",key = "#authid")
    public Auth getAuthByID(Integer authid){
        Auth auth=authMapper.selectByPrimaryKey(authid);
        if(auth.getIsvalid()==1)
            return auth;
        else
            return null;
    }

    @Cacheable(value = "authlist")
    public List<Auth> getAllAuth(){
        AuthExample example = new AuthExample();
        example.createCriteria().andIsvalidEqualTo(1);
        List<Auth> authList=authMapper.selectByExample(example);
        return authList;
    }



}
