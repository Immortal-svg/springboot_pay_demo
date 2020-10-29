package com.example.demo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类
 */
public class AESUtil {
	private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

	public static String KKEY = "axb2c3e4f5$6e7%8";
	public static String KIV = "a1b2c3d4e5f6g7h8";
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";// 默认的加密算法
	public static String code = "UTF-8";
	/**
	 * AES 加密操作
	 *
	 * @param srcData  待加密内容
	 * @param key 加密密码
	 * @param iv       使用CBC模式，需要一个向量iv，可增加加密算法的强度
	 * @return 加密数据
	 */
	public static String encrypt(String srcData,byte[] key,byte[] iv) {
		SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher;
		String  encodeBase64String = null;
		try {
			cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));
			byte[] encData = cipher.doFinal(srcData.getBytes(code));
			encodeBase64String = Base64.getEncoder().encodeToString(encData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodeBase64String;
	}

	/**
	 * AES 解密操作
	 *
	 * @param encDataStr  密文
	 * @param key 密码
	 * @param iv       使用CBC模式，需要一个向量iv，可增加加密算法的强度
	 * @return 明文
	 */
	public static String decrypt(String encDataStr,byte[] key,byte[] iv) {
		byte[] encData = Base64.getDecoder().decode(encDataStr.getBytes());
		SecretKeySpec keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher;
		byte[] decbbdt = null;
		try {
			cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
			decbbdt = cipher.doFinal(encData);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new String(decbbdt);
	}
	
//	public static void main(String[] args) throws Exception {
//		String str = "fot4Ginq4iey7kLUUmA+dA==";
//		byte[] s = Base64.getDecoder().decode(str);
//		String s2 = "1234567890";
//		String iv = "1234567890123456";
//		System.out.println("加密前： "+s2);
//		String encrypt = AESUtil.encrypt(s2, KKEY.getBytes(), KIV.getBytes());
//		System.out.println("加密后： "+new String(encrypt));
//		String decrypt = AESUtil.decrypt(encrypt, KKEY.getBytes(), KIV.getBytes());
//		System.out.println("解密后： "+decrypt);
//	}
}