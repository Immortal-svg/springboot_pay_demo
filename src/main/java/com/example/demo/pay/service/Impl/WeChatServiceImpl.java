package com.example.demo.pay.service.Impl;


import com.example.demo.common.constants.Message;
import com.example.demo.common.enums.ResponseEnum;
import com.example.demo.common.utils.*;
import com.example.demo.pay.service.WeChatService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

@Service
public class WeChatServiceImpl implements WeChatService {

    private final static Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);

    //API_KEY
    private static String API_KEY = "****************";
    //公众账号ID
    private static  String APP_ID="****************";
    //商户号
    private static String MCH_ID="****************";
    //回调地址
    private static String NOTIFY_URL="http://wwww.*******.com/api/wx/wxPayCallBlack";
    //支付回调页面
    //private static String REDIRECT_URL="http://wwww.*******.com/pay_is_ok.html?orderNo=";
    //支付成功页面
    //private static String PAY_SUCCESS_URL="http://wwww.*******.com/pay_success.html";
    //支付失败页面
    //private static String PAY_ERROR_URL="http://wwww.*******.com/pay_error.html";
    //服务端IP
    private static String SPBILL_CREATE_IP="192.168.0.2";
    // 微信支付统一接口(POST)
    private final static String WX_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 微信订单查询接口(POST)
    public final static String WX_CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 微信h5支付
     *
     * @return
     */
    @Override
    public Message WeChatPay(HttpServletRequest request) {
        String nonce_str = generateNonceStr();
        int total_fee = 1;
        double money=total_fee;
        String body = "购买-" + "一月" + "产品,支付" + (money / 100) + "元";
        String out_trade_no = OrderIdUtil.getOrderNo();
        String spbill_create_ip = SPBILL_CREATE_IP;
        //String time_start= DateUtil.getAtPersent();//交易起始时间
        //String time_expire= DateUtil.getBeformTime(5);//交易结束时间
        String trade_type = "MWEB";//H5支付的交易类型为 MWEB
        String scene_info = "{'h5_info': {'type':'Wap','wap_url': 'https://pay.qq.com','wap_name': '产品购买'}}";
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", APP_ID);// 公众账号ID
        packageParams.put("mch_id", MCH_ID);// 商户号
        packageParams.put("nonce_str", nonce_str);// 随机字符串
        packageParams.put("body", body);// 商品描述
        packageParams.put("out_trade_no", out_trade_no);// 商户订单号
        packageParams.put("total_fee", total_fee);// 总金额(单位分)
        packageParams.put("spbill_create_ip", spbill_create_ip);//发起人IP地址
        packageParams.put("notify_url", NOTIFY_URL);// 回调地址
        packageParams.put("trade_type", trade_type);// 交易类型
        packageParams.put("scene_info", scene_info);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);// 签名
        packageParams.put("sign", sign);
        String mweb_url = "";
        Map<String,Object> data=new HashMap<>();
        try {
            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpRequest.postData(WX_PAY_URL, requestXML);
            Map map = XMLUtil.doXMLParse(resXml);
            logger.info("map:{}"+map);
            String returnCode = (String) map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = (String) map.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    logger.info("订单号：{}发起H5支付成功", out_trade_no);
                    mweb_url = (String) map.get("mweb_url");
                } else {
                    String errCodeDes = (String) map.get("err_code_des");
                    logger.info("订单号：{}发起H5支付(系统)失败:{}", out_trade_no, errCodeDes);
                }
            } else {
                String returnMsg = (String) map.get("return_msg");
                logger.info("(订单号：{}发起H5支付(通信)失败:{}", out_trade_no, map);
            }
        } catch (Exception e) {
            logger.error("订单号：{}发起H5支付失败(系统异常))", out_trade_no, e);
        }
        try {
            //String url = URLEncoder.encode(REDIRECT_URL+out_trade_no, "utf-8");
            //redirect_url参数，来指定回调页面
            //mweb_url=mweb_url+"&redirect_url="+url;
        }catch (Exception e){
              logger.error("URl进行Encode异常:{}", e);
        }
        data.put("url",mweb_url);
        data.put("orderNo",out_trade_no);
        return new Message(ResponseEnum.SUCCESS, data);
    }

    /**
     * 微信h5支付回调方法
     *
     * @param request
     * @param response
     */
    @Override
    public void weixin_notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 读取参数
            InputStream inputStream = request.getInputStream();
            StringBuffer sb = new StringBuffer();
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((s = in.readLine()) != null) {
                sb.append(s);
            }
            in.close();
            inputStream.close();
            // 解析xml成map
            Map<String, String> m = new HashMap<String, String>();
            m = XMLUtil.doXMLParse(sb.toString());
            // 过滤空 设置 TreeMap
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            Iterator it = m.keySet().iterator();
            while (it.hasNext()) {
                String parameter =String.valueOf( it.next());
                String parameterValue = m.get(parameter);
                String v = "";
                if (null != parameterValue) {
                    v = parameterValue.trim();
                }
                packageParams.put(parameter, v);
            }
            logger.info("回调参数 map：{}"+packageParams);
            // 账号信息
            String key = API_KEY; // key
            // 判断签名是否正确
            if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
                logger.info("微信支付成功回调");
                // ------------------------------
                // 处理业务开始
                // ------------------------------
                String resXml = "";
                if ("SUCCESS".equals(String.valueOf(packageParams.get("result_code")))) {
                    // 这里是支付成功
                    String orderNo = String.valueOf(packageParams.get("out_trade_no"));
                    logger.info("付款成功,微信订单号:{}", orderNo);
                    // 这里 根据实际业务场景 做相应的操作
                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = "<xml>"
                            + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                } else {
                    logger.info("支付失败,错误信息：{}", packageParams.get("err_code"));
                    resXml = "<xml>"
                            + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[报文为空]]></return_msg>"
                            + "</xml> ";
                }
                // ------------------------------
                // 处理业务完毕
                // ------------------------------
                BufferedOutputStream out = new BufferedOutputStream(
                        response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            } else {
                logger.info("通知签名验证失败");
            }
        } catch (Exception e) {
            logger.error("微信H5支付回调异常{}:", e.toString(), e);
        }
    }


    /**
     * 微信订单查询
     *
     * @param request
     * @param out_trade_no
     * @return
     */
    @Override
    public Message weChatOrderQuery(HttpServletRequest request, String out_trade_no) {
        String nonce_str = generateNonceStr();
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", APP_ID);// 公众账号ID
        packageParams.put("mch_id", MCH_ID);// 商户号
        packageParams.put("nonce_str", nonce_str);// 随机字符串
        packageParams.put("out_trade_no",out_trade_no);//订单No
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, API_KEY);// 签名
        packageParams.put("sign", sign);
        Map<String,Object> data=new HashMap<>();
        //data.put("pay_success_url",PAY_SUCCESS_URL);
        //data.put("pay_error_url",PAY_ERROR_URL);
        try {
            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpRequest.postData(WX_CHECK_ORDER_URL, requestXML);
            Map map = XMLUtil.doXMLParse(resXml);
            logger.info("map：{}"+map);
            String returnCode = (String) map.get("return_code");
            if ("SUCCESS".equals(returnCode)) {
                String resultCode = (String) map.get("result_code");
                if ("SUCCESS".equals(resultCode)) {
                    String tradeState = (String) map.get("trade_state");
                    logger.info("tradeState:{}" + tradeState);
                    logger.info("订单号：{}", out_trade_no);
                    data.put("orders",map);
                    boolean paySuccess=paySuccess(tradeState);
                    if (paySuccess == true)
                        return new Message(ResponseEnum.SUCCESS, data);
                } else {
                    String errCodeDes = (String) map.get("err_code_des");
                    logger.info("订单号：{}订单查询失败(系统)失败：{}", out_trade_no, errCodeDes);
                }
            } else {
                String returnMsg = (String) map.get("return_msg");
                logger.info("(订单号：{}订单查询失败：{}", out_trade_no, returnMsg);
            }
        } catch (Exception e) {
            logger.error("订单号：{}订单查询失败(系统异常))", out_trade_no, e);
        }
        return new Message(ResponseEnum.FALL,data);
    }


    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        String noncestr = RandomStringUtils.randomAlphanumeric(16);
        return noncestr;
    }

    /**
     * 生成系统当前时间戳
     *
     * @return
     */
    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    public static boolean paySuccess(String tradeState){
        if("SUCCESS".equalsIgnoreCase(tradeState)){
         return  true;
        }
        return  false;
    }
}
