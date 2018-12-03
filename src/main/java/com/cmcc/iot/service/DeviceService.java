package com.cmcc.iot.service;

import com.cmcc.iot.common.MyLoginException;
import com.cmcc.iot.mapper.DeviceCompanyIDMapper;
import com.cmcc.iot.model.DeviceCompanyID;
import com.cmcc.iot.model.DeviceCompanyIDExample;
import com.cmcc.iot.model.User;
import com.cmcc.iot.utills.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    DeviceCompanyIDMapper deviceCompanyIDMapper;


    public List<DeviceCompanyID> getDeviceIDListByCompany(String title,Integer device_id,Integer companyid) throws MyLoginException {
        DeviceCompanyIDExample example = new DeviceCompanyIDExample();
        DeviceCompanyIDExample.Criteria criteria=example.createCriteria();
        User loginuser=(User) SecurityUtils.getSubject().getPrincipal();
        if (loginuser==null)
            throw  new MyLoginException("登录后重试");
        if (device_id!=null)
            criteria.andDeviceidEqualTo(device_id);
        if(!StringUtils.isNullOrNone(title))
            criteria.andTitelLike("%"+title+"%");
        if (loginuser.getRoletype()!=1)
            criteria.andCompanyidEqualTo(loginuser.getCompanyid());
        else if(companyid!=null)
            criteria.andCompanyidEqualTo(companyid);
        return deviceCompanyIDMapper.selectByExample(example);
    }

    public int removeDeviceByID(Integer deviceid){

        return  deviceCompanyIDMapper.deleteByPrimaryKey(deviceid);
    }

    public int addDevice(DeviceCompanyID device)  {
//        throw new MyLoginException("请登录");
        return  deviceCompanyIDMapper.insertSelective(device);
    }

    public DeviceCompanyID getDevicebyID(Integer deviceid){
       return deviceCompanyIDMapper.selectByPrimaryKey(deviceid);
    }

}
