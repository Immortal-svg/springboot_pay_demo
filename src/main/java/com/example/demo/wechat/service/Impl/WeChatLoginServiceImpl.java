package com.example.demo.wechat.service.Impl;


import com.example.demo.common.constants.Message;
import com.example.demo.common.enums.ResponseEnum;
import com.example.demo.common.utils.DateUtil;
import com.example.demo.common.utils.MD5Utils;
import com.example.demo.wechat.service.WeChatLoginService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Jova
 * @create 2020/10/29
 */
@Service
@Slf4j
public class WeChatLoginServiceImpl implements WeChatLoginService {


    @Autowired
    private WxMpService wxMpService;

    @Value("${wx.open.config.redirectUrl}")
    private String wxRedirectUrl;

    @Value("${wx.open.config.csrfKey}")
    private String CSRF_KEY;

    public static final String SCOPE = "snsapi_login";


    @Override
    public String getQRCodeUrl() {
        // 生成 state 参数，用于防止 csrf
        String date = DateUtil.format(new Date(), "yyyyMMdd");
        String state = MD5Utils.generate(CSRF_KEY + date);
        return wxMpService.buildQrConnectUrl(wxRedirectUrl, SCOPE, state);
    }

    @Override
    public Message wxCallBack(HttpServletRequest request, String code, String state) {
        String openId = null;
        if (code != null && state != null) {
            // 验证 state,防止跨站请求伪造攻击
            String date = DateUtil.format(new Date(), "yyyyMMdd");
            Boolean isNotCsrf;
            try {
                isNotCsrf = MD5Utils.verify(CSRF_KEY + date, state);
            } catch (Exception e) {
                log.error(e.toString());
                return new Message(ResponseEnum.FALL);
            }
            if (!isNotCsrf) {
                return new Message(ResponseEnum.FALL);
            }
            // 获取 openid
            try {
                WxMpOAuth2AccessToken accessToken = wxMpService.oauth2getAccessToken(code);
                if (accessToken == null) {
                    return new Message(ResponseEnum.FALL);
                }
                openId = accessToken.getOpenId();
                log.info("openId:" + openId);
                /*  token = accessToken.getAccessToken();*/
                WxMpUser wxUser = wxMpService.oauth2getUserInfo(accessToken, null);
                //下面根据实际业务逻辑处理
                log.info(wxUser.toString());
                return new Message(ResponseEnum.SUCCESS, wxUser);
            } catch (WxErrorException e) {
                log.error(e.getMessage(), e);
                return new Message(ResponseEnum.FALL);
            }
        }
        return new Message(ResponseEnum.FALL);
    }
}
