package com.cmcc.iot.utills;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MySecurityUtil {

    public  static String encode(String password,String salt){
        Object result = new SimpleHash("MD5",password,salt,1024);
        return  result.toString();
    }

}
