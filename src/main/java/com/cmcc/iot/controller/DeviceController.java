package com.cmcc.iot.controller;

import com.cmcc.iot.common.MapFromPageInfo;
import com.cmcc.iot.common.MyLoginException;
import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.DeviceCompanyID;
import com.cmcc.iot.service.DeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@Api("设备")
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @RequestMapping(value = "getdevieidlist",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getDeviceIDList(@RequestParam(value = "pagesize")Integer pagesize,
                                         @RequestParam(value = "pagenum")Integer pagenum,
                                         @RequestParam(value = "title",required = false)String title,
                                         @RequestParam(value = "device_id",required = false)Integer device_id,
                                         @RequestParam(value = "companyid",required = false)Integer companyid){
        List<Integer> deviceidList=new ArrayList<>();
        try {
            PageHelper.startPage(pagenum,pagesize);
            List<DeviceCompanyID> deviceCompanyIDList=deviceService.getDeviceIDListByCompany(title,device_id,companyid);
            for (DeviceCompanyID item:deviceCompanyIDList){
                deviceidList.add(item.getDeviceid());
            }
            MapFromPageInfo result=new MapFromPageInfo(deviceCompanyIDList,deviceidList);

            return new ResponseModel(0L,"成功",result);
        }catch (MyLoginException e){
            e.printStackTrace();
            return new ResponseModel(1001L,e.getMessage(),null);
        }
    }

    @RequestMapping(value = "deletedevice",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel deleteDeviceByID(
            @RequestParam(value = "deviceid") Integer deviceid){
       int a= deviceService.removeDeviceByID(deviceid);
       return  new ResponseModel(0L,"成功删除"+a+"条数据",null);
    }


    @RequestMapping(value = "adddevice",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel addDevice(@RequestParam(value = "deviceid")Integer deviceid,
                                   @RequestParam(value = "companyid")Integer companyid,
                                   @RequestParam(value = "title") String title,
                                   @RequestParam(value = "apikey",required = false)String apikey) {


        DeviceCompanyID device= new DeviceCompanyID();
        device.setDeviceid(deviceid);
        device.setApikey(apikey);
        device.setCompanyid(companyid);
        device.setTitel(title);
        int a=deviceService.addDevice(device);
        return new ResponseModel(0L,"成功添加"+a+"台设备ID为："+deviceid,null);
    }

    @RequestMapping(value = "getcompanyidbydeviceid",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getCompanyidByDeviceid(@RequestParam(value = "deviceid") Integer deviceid){
        DeviceCompanyID deviceCompanyID= deviceService.getDevicebyID(deviceid);
        if (deviceCompanyID!=null) {
            Integer companyid=deviceCompanyID.getCompanyid();
            return new ResponseModel(0L,"成功",companyid);
        }else
            return new ResponseModel(500L,"失败",null);

    }


    @RequestMapping(value = "getdevicecount",method = RequestMethod.POST)
    @ResponseBody
    public ResponseModel getDeviceCount(){
        Integer count=deviceService.getDeviceCount();
        return new ResponseModel(0L,"成功",count);
    }


}
