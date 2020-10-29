package com.example.demo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdUtil {
	public static String getOrderNo() {
        String strDate = String.valueOf(System.currentTimeMillis());
        //为了防止高并发重复,再获取3个随机数
        String random = getRandom620(3);
         
        //最后得到16位订单编号。
        String orderNo = strDate + random;
        return orderNo;
    }
    /**
     * 获取6-10 的随机位数数字
     * @param length    想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
 
            result += randInt;
        }
        return result;
    }
}
