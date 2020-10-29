package com.example.demo.common.constants;


public class Constants {

	/**
	 * 订单状态-待付款
	 */
	public static final String ORDER_STATUS_DFK = "0";
	/**
	 * 订单状态-正在付款
	 */
	public static final String ORDER_STATUS_YDFK = "1";
	/**
	 * 订单状态-已删除
	 */
	public static final String ORDER_STATUS_YSC = "2";
	/**
	 * 订单状态-已关闭
	 */
	public static final String ORDER_STATUS_YGB = "3";
	/**
	 * 订单状态-付款成功
	 */
	public static final String ORDER_STATUS_FKCG = "4";
	/**
	 * 订单状态-付款失败
	 */
	public static final String ORDER_STATUS_FKSB = "5";
	/**
	 * 微信支付
	 */
	public static final String WEIXIN_PAY = "2";
	
	/**
	 * 支付宝支付
	 */
	public static final String ALIPAY_PAY = "1";
	
	/**
	 * 支付宝商户id
	 */
	public static final String ALIPAY_APP_ID = "ALIPAY_APP_ID";

	/**
	 * 支付宝私钥 
	 */
	public static final String ALIPAY_PRIVATE_KEY = "ALIPAY_PRIVATE_KEY";

	/**
	 * 支付宝公钥 
	 */
	public static final String ALIPAY_PUBLIC_KEY = "ALIPAY_PUBLIC_KEY";

	/**
	 * 支付宝商户id
	 */
	public static final String ALIPAY_NOTIFY_URL = "ALIPAY_NOTIFY_URL";

	/**
	 * 支付宝完成返回地址 
	 */
	public static final String ALIPAY_RETURN_URL = "ALIPAY_RETURN_URL";
	
	/**
	 * 支付宝签名类型 
	 */
	public static final String ALIPAY_SIGN_TYPE = "ALIPAY_SIGN_TYPE";
	
	/**
	 * 支付宝字符集 
	 */
	public static final String ALIPAY_CHARSET = "ALIPAY_CHARSET";
	
	/**
	 * 支付宝网关url
	 */
	public static final String ALIPAY_GATEWAY_URL = "ALIPAY_GATEWAY_URL";
	
	/**
	 * 支付宝数据格式
	 */
	public static final String ALIPAY_JUMP_URL = "ALIPAY_JUMP_URL";
	
	/**
	 * 支付宝数据格式
	 */
	public static final String ALIPAY_FORMAT = "ALIPAY_FORMAT";

	/**
	 * 推荐后注册加天数
	 */
	public static final String RECOMMEND_REGISTER_DAYS = "RECOMMEND_REGISTER_DAYS";

	/**
	 * 推荐后充值加天数
	 */
	public static final String RECOMMEND_RECHARGE_DAYS = "RECOMMEND_RECHARGE_DAYS";
	/**
	 * 下载链接
	 */
	public static  final String ANDROID_DOWNLOAD_PATH="ANDROID_DOWNLOAD_PATH";
	/**
	 * 注册日志接口
	 */
		public static  final String LOG_REGISTERLOGURL="REGISTER_LOG";
	/**
	 * 退出连接日志
	 */
	public static  final  String LOG_CONNNECTLOGURL="CONNECT_QUIT_LOG";
	/**
	 * 1-购买产品
	 */
	public static  final int  EXPIRETIMETYPE_PRODUCT=1;
	/**
	 *   2-注册
	 */
	public static  final int  EXPIRETIMETYPE_REGISTER=2;
	/**
	 *  3-推荐人注册
	 */
	public static  final int  EXPIRETIMETYPE_RECOMMENDREGISTER=3;
	/**
	 *   4-推荐人购买
	 */
	public static  final int  EXPIRETIMETYPE_RECOMMENDPRODUCT=4;
	/**
	 *   5-CDK充值
	 */
	public static  final int  EXPIRETIMETYPE_CDK=5;
	/**
	 * 注册增加时间
	 */
	public static  final String REGISTER_ADD_DAYS="REGISTER_ADD_DAYS";
	
	public static final String SEND_SMS_SERVICE = "http://127.0.0.1:8080/SendSmsService";
}