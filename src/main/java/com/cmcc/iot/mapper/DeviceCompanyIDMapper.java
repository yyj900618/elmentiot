package com.cmcc.iot.mapper;

import com.cmcc.iot.model.DeviceCompanyID;
import com.cmcc.iot.model.DeviceCompanyIDExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DeviceCompanyIDMapper {
    int countByExample(DeviceCompanyIDExample example);

    int deleteByExample(DeviceCompanyIDExample example);

    int deleteByPrimaryKey(Integer deviceid);

    int insert(DeviceCompanyID record);

    int insertSelective(DeviceCompanyID record);

    List<DeviceCompanyID> selectByExample(DeviceCompanyIDExample example);

    DeviceCompanyID selectByPrimaryKey(Integer deviceid);

    int updateByExampleSelective(@Param("record") DeviceCompanyID record, @Param("example") DeviceCompanyIDExample example);

    int updateByExample(@Param("record") DeviceCompanyID record, @Param("example") DeviceCompanyIDExample example);

    int updateByPrimaryKeySelective(DeviceCompanyID record);

    int updateByPrimaryKey(DeviceCompanyID record);
}