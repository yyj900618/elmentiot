package com.cmcc.iot.service;

import com.cmcc.iot.mapper.RoleMapper;
import com.cmcc.iot.model.Role;
import com.cmcc.iot.model.RoleExample;
import com.cmcc.iot.utills.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public Role getroleById(Integer roleid){
        Role role= roleMapper.selectByPrimaryKey(roleid);
        if(role.getIsvalid()==1)
            return role;
        else
            return null;
    }

    public List<Role> getRoleByCondition(Integer roletype,String rolename, Integer isvalid ){
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria=example.createCriteria();

        if(roletype!=null)
            criteria.andRoletypeGreaterThanOrEqualTo(roletype);
        if(!StringUtils.isNullOrNone(rolename)){
            rolename="%"+rolename+"%";
            criteria.andRolenameLike(rolename);
        }

        if(isvalid!=null)
            criteria.andIsvalidEqualTo(isvalid);
        return roleMapper.selectByExample(example);
    }

    public int addRole(Role role){
        return roleMapper.insertSelective(role);
    }

    public int updateRole(Role role){
        return roleMapper.updateByPrimaryKeySelective(role);
    }


    public int removeRoleById(Integer roleid){
        return roleMapper.deleteByPrimaryKey(roleid);
    }

}
