package com.example.demo.common.utils;



import com.example.demo.common.entity.*;
import com.example.demo.common.enums.CodeEnum;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 验证码工具类
 * Created by A
 */
public class VerificationCodeUtil {

    private final static ConcurrentHashMap<String, Tuple2<String, Long>> mapUnverified = new ConcurrentHashMap<>();
    public static final long SMSVALIDLIMIT = 10 * 60 * 1000; //5分钟超时限制
    /**
     * 获取验证码
     * @return
     */
    public static String getVerificationCode() {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();

        return afterShuffle.substring(5, 9);
    }

    /**
     * 短信发送成功后，放入缓存
     * @param pnum
     * @param verificationCode
     */
    public static void putVerificationCode(String pnum, String verificationCode){
        mapUnverified.put(pnum, new Tuple2<>(verificationCode, Instant.now().toEpochMilli()));
    }

    /**
     * 对验证码进行核对是否正确
     * @param pnum
     * @param verificationCode
     * @return
     */
    public static ResultMessage verifySmsCaptcha(String pnum, String verificationCode){
        ResultMessage resultMessage = new ResultMessage();

        Tuple2<String, Long> tuple2 = mapUnverified.get(pnum);
        if(tuple2==null){
            return new ResultMessage(CodeEnum.E4015);
        }

        if(Instant.now().toEpochMilli() - tuple2.b > SMSVALIDLIMIT){
            return new ResultMessage(CodeEnum.E4016);
        }

        if(!tuple2.a.equals(verificationCode)){
            return new ResultMessage(CodeEnum.E4017);
        }

        mapUnverified.remove(pnum);
        return resultMessage;
    }

}
