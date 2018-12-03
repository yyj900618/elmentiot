package com.cmcc.iot.controller;

import com.cmcc.iot.common.CacheKeyConst;
import com.cmcc.iot.common.ResponseModel;
import com.cmcc.iot.model.OneNetMessage;
import com.cmcc.iot.service.ReceiverService;
import com.cmcc.iot.utills.ReceiverUtil;
import com.cmcc.iot.utills.RedisUtil;
import com.cmcc.iot.utills.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


@Controller
@RequestMapping("/receive")
@Api(description = "接收器")
public class ReceiverController {



    @Autowired
    ReceiverService receiverService;

    private static String token ="yyj900618";//用户自定义token和OneNet第三方平台配置里的token一致
    private static String aeskey ="yyj900618";//aeskey和OneNet第三方平台配置里的token一致

    private static Logger logger = LoggerFactory.getLogger(ReceiverController.class);
    /**
     * 功能描述：第三方平台数据接收。<p>
     *           <ul>注:
     *               <li>1.OneNet平台为了保证数据不丢失，有重发机制，如果重复数据对业务有影响，数据接收端需要对重复数据进行排除重复处理。</li>
     *               <li>2.OneNet每一次post数据请求后，等待客户端的响应都设有时限，在规定时限内没有收到响应会认为发送失败。
     *                    接收程序接收到数据时，尽量先缓存起来，再做业务逻辑处理。</li>
     *           </ul>
     * @param body 数据消息
     * @return 任意字符串。OneNet平台接收到http 200的响应，才会认为数据推送成功，否则会重发。
     */
    @RequestMapping(value = "/receiver",method = RequestMethod.POST)
    @ResponseBody
    public String receive(@RequestBody String body) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException {
        System.out.println("进入POST");
        /************************************************
         *  解析数据推送请求，非加密模式。
         *  如果是明文模式使用以下代码
         **************************************************/
        /*************明文模式  start****************/
        ReceiverUtil.BodyObj obj = ReceiverUtil.resolveBody(body, false);
//        logger.info("data receive:  body Object --- " +obj);

//        JSONObject msgtest=(JSONObject)obj.getMsg();
//        String code= StringUtils.codelist(msgtest.get("value"));
//        System.out.println(code);


        if (obj != null){
            boolean dataRight = ReceiverUtil.checkSignature(obj, token);
            if (dataRight){
                Object msg=obj.getMsg();
                if (msg instanceof  JSONObject){
                    receiverService.saveCache(msg);
                }
                if(msg instanceof JSONArray){
                    JSONArray msg_list=(JSONArray)msg;
                    System.out.println(msg_list);
                    for (Object msg_obj:msg_list){
                        receiverService.saveCache(msg_obj);
                    }
                }
            }else {
                logger.info("data receive: signature error");
            }
        }else {
            logger.info("data receive: body empty error");
        }
        /*************明文模式  end****************/


        /********************************************************
         *  解析数据推送请求，加密模式
         *
         *  如果是加密模式使用以下代码
         ********************************************************/
        /*************加密模式  start****************/
//        Util.BodyObj obj1 = Util.resolveBody(body, true);
//        logger.info("data receive:  body Object--- " +obj1);
//        if (obj1 != null){
//            boolean dataRight1 = Util.checkSignature(obj1, token);
//            if (dataRight1){
//                String msg = Util.decryptMsg(obj1, aeskey);
//                logger.info("data receive: content" + msg);
//            }else {
//                logger.info("data receive:  signature error " );
//            }
//        }else {
//            logger.info("data receive: body empty error" );
//        }
        /*************加密模式  end****************/
        return "ok";
    }





    /**
     * 功能说明： URL&Token验证接口。如果验证成功返回msg的值，否则返回其他值。
     * @param msg 验证消息
     * @param nonce 随机串
     * @param signature 签名
     * @return msg值
     */
    @RequestMapping(value = "/receiver", method = RequestMethod.GET)
    @ResponseBody
    public String check(@RequestParam(value = "msg") String msg,
                        @RequestParam(value = "nonce") String nonce,
                        @RequestParam(value = "signature") String signature) throws UnsupportedEncodingException {
        System.out.println("进入get");
        logger.info("url&token check: msg:{} nonce{} signature:{}",msg,nonce,signature);
        if (ReceiverUtil.checkToken(msg,nonce,signature,token)){
            return msg;
        }else {
            return "error";
        }

    }

    @RequestMapping(value = "/getcache", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "device_id", dataType = "String", paramType = "query",required = false),
            @ApiImplicitParam(name = "count", dataType = "int", paramType = "query",required = true)
    })
    @ResponseBody
    public ResponseModel getCache(@RequestParam(value = "device_id",required = false) String device_id,
                                  @RequestParam(value = "count",required = false,defaultValue = "1000") Integer count){
        List<Object> msgobjList;
        if (!StringUtils.isNullOrNone(device_id))
             msgobjList=receiverService.getCache("iot.msg."+device_id+".*",count);
        else
            msgobjList=   receiverService.getCache("iot.msg.*",count);
        List<OneNetMessage> msgList= new ArrayList<>();
        for (Object msgobj:msgobjList){
            msgList.add((OneNetMessage)msgobj);
        }
        //排序
        msgList.sort((o1, o2) -> {
            Long i_long = o2.getAt()-o1.getAt();
            return i_long.intValue();
        });
        return new ResponseModel(0l,"成功",msgList);
    }


}
