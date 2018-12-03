package com.cmcc.iot.service;

import com.cmcc.iot.mapper.UserMapper;
import com.cmcc.iot.model.User;
import com.cmcc.iot.model.UserExample;
import com.cmcc.iot.utills.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    @Cacheable(value = "user",key = "#userid")
    public User getUserById(Integer userid){
        System.out.println("进行了数据库的查找");
        return userMapper.selectByPrimaryKey(userid);
    }



    public User getUserByLoginname(String loginname){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginnameEqualTo(loginname);
        List<User> userList= userMapper.selectByExample(userExample);
        if (userList.isEmpty())
            return null;
        else
            return  userList.get(0);
    }

    public User getUserByphonenum(String phonenum){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhonenumEqualTo(phonenum);
        List<User> userList= userMapper.selectByExample(userExample);
        if (userList.isEmpty())
            return null;
        else
            return  userList.get(0);
    }

    public List<User> listUserByCondition(String loginname, String name, String phonenum, String isvalid,Integer companyid){
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();

        if (companyid!=null){
            criteria.andCompanyidEqualTo(companyid);
        }
        if (!StringUtils.isNullOrNone(loginname)) {
            loginname="%"+loginname+"%";
            criteria.andLoginnameLike(loginname);
        }
        if (!StringUtils.isNullOrNone(name)) {
            name="%"+name+"%";
            criteria.andNameLike(name);
        }
        if (!StringUtils.isNullOrNone(phonenum)) {
            phonenum="%"+phonenum+"%";
            criteria.andPhonenumLike(phonenum);
        }
        if (!StringUtils.isNullOrNone(isvalid)) {
            criteria.andIsvalidEqualTo(Integer.parseInt(isvalid));
        }

       return userMapper.selectByExample(userExample);
    }

    @CacheEvict(value = "user",key = "#user.id")
    public  int updateUserById(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @CacheEvict(value = "user",key = "#userid")
    public int removeUserById(Integer userid){
       return userMapper.deleteByPrimaryKey(userid);
    }

    public int addUser(User user){
       return userMapper.insertSelective(user);
    }
}
