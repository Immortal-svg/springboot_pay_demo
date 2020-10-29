package com.example.demo.common.constants;

public class ConstantsParam {

	/**
	 * 用户用户名注册
	 */
	public static final String REGISTER_USERNAME = "1";
	
	/**
	 * 用户手机号注册
	 */
	public static final String REGISTER_PHONE = "2";
		
	/**
	 * 用户邮箱注册
	 */
	public static final String REGISTER_EMALI = "3";
		
	/**
	 * 用户每天最大发送短信次数
	 */
	public static final Integer MAX_SEND_COUNT = 100;
		
	/**
	 * 验证码有效时间
	 */
	public static final Long MAX_CODE_INVALID = 5l;
		
	/**
	 * 用户token有效时间
	 */
	public static final int MAX_TOKEN_INVALID = 2;
			
	/**
	 * 验证码：用户登录
	 */
	public static final Integer TYPE_LOGIN = 0;
	
	/**
	 * 验证码：用户注册
	 */
	public static final Integer TYPE_REGISTER = 1;
	
	/**
	 * 验证码：用户找回密码
	 */
	public static final Integer TYPE_FIND_PASSWORD = 2;
	

	/**
	 * 标记 true：表示用户已签到
	 */
	public static final Integer SIGN_TRUE_FLAG = 1;

	/**
	 * 标记 false：表示用户未签到
	 */
	public static final Integer SIGN_FALSE_FLAG = 0;

		
	/**
	 * 用户通过用户名登录验证
	 */
	public static final Integer LOGIN_FORM_USERNAME_VALIDATE = 1;
		
	/**
	 * 用户通过手机号登录验证
	 */
	public static final Integer LOGIN_FORM_PHONE_VALIDATE = 2;
	
	/**
	 * 用户通过邮箱登录验
	 */
	public static final Integer LOGIN_FORM_EMAIL_VALIDATE = 3;
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
