package com.cmcc.iot;

import com.cmcc.iot.utills.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        Jedis jedis = new Jedis("47.92.115.215", 6379);
//        jedis.auth("123456");
//        // 调用jedis对象的方法，方法名称和redis的命令一致。
//        ScanParams scanParams = new ScanParams();
//        scanParams.match("iot.msg.503687319.*");
//        scanParams.count(1000);
//        jedis.select(0);
//        ScanResult<String> scan = jedis.scan("0", scanParams);
//        System.out.println("scan：返回用于下次遍历的游标"+scan.getStringCursor());
//        System.out.println("scan：返回结果"+scan.getResult());
//        // 关闭jedis。
//        jedis.close();




    }
}
