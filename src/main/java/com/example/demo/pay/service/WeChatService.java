package com.example.demo.pay.service;



import com.example.demo.common.constants.Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WeChatService {

    Message WeChatPay(HttpServletRequest request);

    Message weixin_notify(HttpServletRequest request, HttpServletResponse response);

    Message weChatOrderQuery(HttpServletRequest request,String out_trade_no);

    Message weCharRefund(HttpServletRequest request,String orderNo,String totalFee);
}
