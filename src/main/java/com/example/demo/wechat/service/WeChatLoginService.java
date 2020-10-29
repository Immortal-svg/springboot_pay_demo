package com.example.demo.wechat.service;



import com.example.demo.common.constants.Message;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信
 */
public interface WeChatLoginService {
    /**
     * 微信扫码登录请求地址的生成
     * @return
     */
    String getQRCodeUrl();

    /**
     * 微信扫码回调
     * @param code
     * @param state
     * @return
     */
    Message wxCallBack(HttpServletRequest request, String code, String state);
}
