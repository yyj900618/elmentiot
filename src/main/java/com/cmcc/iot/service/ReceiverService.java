package com.cmcc.iot.service;

import com.cmcc.iot.common.CacheKeyConst;
import com.cmcc.iot.model.OneNetMessage;
import com.cmcc.iot.utills.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReceiverService {
    @Resource(name = "redisUtil")
    RedisUtil redisUtil;


    @Resource(name = "jedis")
    Jedis jedis;


    public void saveCache(Object msg)  {
        Long at = null;
        Integer type= null;
        String ds_id= null;
        String value= null;
        Integer dev_id= null;
        Integer status= null;
        Integer login_type= null;
        Integer cmd_type= null;
        String cmd_id= null;
        String imei= null;
        Long send_time= null;
        Integer send_status= null;
        Long confirm_time= null;
        Integer confirm_status= null;
        String confirm_body= null;
        try {
            JSONObject msg_jsonobject=(JSONObject)msg;
            String msg_string=msg_jsonobject.toString();
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,Object> map = mapper.readValue(msg_string, HashMap.class);
            if (map.get("at")!=null)
                at=((Number)map.get("at")).longValue();
            if (map.get("type")!=null)
                type=((Number)map.get("type")).intValue();
            if (map.get("ds_id")!=null)
                ds_id=map.get("ds_id").toString();
            if (map.get("value")!=null)
                value=map.get("value").toString();
            if (map.get("dev_id")!=null)
                dev_id=((Number)map.get("dev_id")).intValue();
            if (map.get("status")!=null)
                status=((Number)map.get("status")).intValue();
            if (map.get("login_type")!=null)
                login_type=((Number)map.get("login_type")).intValue();
            if (map.get("cmd_type")!=null)
                cmd_type=((Number)map.get("cmd_type")).intValue();
            if (map.get("cmd_id")!=null)
                cmd_id=map.get("cmd_id").toString();
            if (map.get("imei")!=null)
                imei=map.get("imei").toString();
            if (map.get("send_time")!=null)
                send_time=((Number)map.get("send_time")).longValue();
            if (map.get("send_status")!=null)
                send_status=((Number)map.get("send_status")).intValue();
            if (map.get("confirm_time")!=null)
                confirm_time=((Number)map.get("confirm_time")).longValue();
            if (map.get("confirm_status")!=null)
                confirm_status=((Number)map.get("confirm_status")).intValue();
            if (map.get("confirm_body")!=null)
                confirm_body=map.get("confirm_body").toString();
            OneNetMessage oneNetMessage = new OneNetMessage();
            oneNetMessage.setAt(at);
            oneNetMessage.setType(type);
            oneNetMessage.setDs_id(ds_id);
            oneNetMessage.setValue(value);
            oneNetMessage.setDev_id(dev_id);
            oneNetMessage.setStatus(status);
            oneNetMessage.setLogin_type(login_type);
            oneNetMessage.setCmd_type(cmd_type);
            oneNetMessage.setCmd_id(cmd_id);
            oneNetMessage.setImei(imei);
            oneNetMessage.setSend_time(send_time);
            oneNetMessage.setSend_status(send_status);
            oneNetMessage.setConfirm_time(confirm_time);
            oneNetMessage.setConfirm_status(confirm_status);
            oneNetMessage.setConfirm_body(confirm_body);
            redisUtil.set(CacheKeyConst.ONENETMSG+dev_id+"."+at,oneNetMessage,3600);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public List<Object> getCache(String pattern,Integer count){
        List<Object> objectList=new ArrayList<>();
        ScanParams scanParams = new ScanParams();
        scanParams.match(pattern);
        scanParams.count(count);
        ScanResult<String> scan=jedis.scan("0", scanParams);
        List<String> keys=scan.getResult();
        for (String key:keys){
            Object obj=redisUtil.get(key);
            objectList.add(obj);
        }
        return objectList;
    }


}
