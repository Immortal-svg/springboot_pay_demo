package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by A
 */
public class IdUtil {

    private static final Logger logger = LoggerFactory.getLogger(IdUtil.class);

    // 不考虑分布式环境的方案
    public static synchronized String nextOrderId() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            logger.error(e.toString(), e);
        }

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
    }

    public static String generateUserId(String phoneNum){
        char[] c = phoneNum.toCharArray();

        String resultStr = "";

        for (int i = c.length - 1; i >= 0; i -= 2) {
            resultStr += (c[i]);
        }

        for (int i = c.length - 2; i >= 0; i -= 2) {
            resultStr += (c[i]);
        }

        return resultStr + RandomUtil.getRandowString(7);
    }
}
