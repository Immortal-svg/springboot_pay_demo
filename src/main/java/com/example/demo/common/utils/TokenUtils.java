/**
 *
 */
package com.example.demo.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * head(8)|uniqueKey(128)[key(32)appId(32)]|jam(1)
 * TOKEN生成算法，key 倒推算法
 *
 * @author A
 */
public final class TokenUtils {

    private TokenUtils() {}

    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    private static final String T_VERSION = "T";

    public static String generateToken(String key) {
 if(key==null) {
	 return null;
 }
        Random rd = new Random();
        Long rdLong = rd.nextLong();
        int safe = 0;
        while (rdLong < 1000 && safe < 100) {
            rdLong = rd.nextLong();
            safe++;
        }
        //获取当前时间戳转字符串 截取掉前4位
        String nowStr = Long.toString(System.currentTimeMillis()).substring(4);
        //随机Long类型转字符串保留后四位
        String rdLongStr = Long.toString(rdLong);
        rdLongStr = rdLongStr.substring(rdLongStr.length() - 4);

        String mix = mixContent(T_VERSION + rdLongStr + nowStr, key.toString());
        String token = null;
        try {
            token = T_VERSION + Base64.encodeBase64String(mix.getBytes("UTF-8"));
            token = token.replace("\n", "").replace("\r", "").replace("/", "-").replace("+", "_").replace("=", ".");
        } catch (Exception e) {
            logger.error("generateToken", e);
        }

        return token;
    }

    public static long decryptToken(String token) {
        long key = 0;
        if (StringUtils.isEmpty(token)) {
            return key;
        }
        try {
            token = token.substring(1);
            token = token.replace("-", "/").replace("_", "+").replace(".", "=");
            byte[] eb = Base64.decodeBase64(token);
            String dStr = new String(eb, "UTF-8");
            key = getKeyFromMix(dStr);
        } catch (Exception e) {
            logger.error("decryptToken", e);
        }
        return key;
    }

    private static String mixContent(String maskStr, String keyStr) {
        if (maskStr.length() <= keyStr.length()) {
            return null;
        }
        char[] mix = new char[maskStr.length() + keyStr.length() + 1];
        int keyStrIdx = 0;
        int maskStrIdx = 0;
        boolean isKeyFull = false;
        for (int i = 0; i < mix.length; i++) {
            if (i % 2 != 0 && keyStrIdx < keyStr.length()) {
                // 奇数位放key
                mix[i] = keyStr.charAt(keyStrIdx);
                keyStrIdx++;
            } else {
                if (keyStrIdx == keyStr.length() && !isKeyFull) {
                    mix[i] = '_';
                    isKeyFull = true;
                    continue;
                }
                mix[i] = maskStr.charAt(maskStrIdx);
                maskStrIdx++;
            }
        }

        return new String(mix);
    }

    private static long getKeyFromMix(String mix) {
        long key = 0;
        if (StringUtils.isEmpty(mix)) {
            return key;
        }
        String[] uMixArr = mix.split("_");
        if (uMixArr == null || uMixArr.length != 2) {
            return key;
        }
        String uStr = "";
        for (int i = 0; i < uMixArr[0].length(); i++) {
            if (i % 2 != 0) {
                uStr += uMixArr[0].charAt(i);
            }
        }
        key = NumberUtils.toLong(uStr);
        return key;
    }


//    public static void main(String[] s) throws IOException, InvalidKeyException,
//            NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
//            BadPaddingException {
//
//        String t = generateToken(144l);
//        System.out.println(t);
//        long uid = decryptToken(t);
//        System.out.println(uid);
//
//    }

}
