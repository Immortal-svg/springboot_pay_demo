package com.example.demo.wechat.controller;


import com.example.demo.common.constants.Message;
import com.example.demo.common.enums.ResponseEnum;
import com.example.demo.wechat.service.WeChatService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**

 */
@RestController
@RequestMapping("/api/wxLong")
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatService weChatService;

    /**
     * 获取微信登陆二维码地址
     * @return
     */
    @RequestMapping(value = "/getQRCodeUrl",method = RequestMethod.POST)
    public Message getQRCodeUrl() {
        logger.info("获取二维码地址");
        try {
            String codeUrl = weChatService.getQRCodeUrl();
            logger.info("codeUrl:"+codeUrl);
            return new Message(ResponseEnum.SUCCESS,codeUrl);
        }catch (Exception e){
            logger.error(e.toString()+e);
            return new Message(ResponseEnum.FALL);
        }
    }

    /**
     * 回调地址
     * @param code
     * @param state
     * @return
     */
    @RequestMapping(value = "/wxCallBack",method = RequestMethod.POST)
    public Message wxCallBack(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "code",defaultValue = "") String code, @RequestParam(name = "state",defaultValue = "")String state) {
        if(StringUtils.isBlank(code)){
            return new Message(ResponseEnum.ERROR_ISEXITNULL);
        }
        if(StringUtils.isBlank(state)){
            return new Message(ResponseEnum.ERROR_ISEXITNULL);
        }
        logger.info("微信回调------------");
        logger.info(code+"------"+state);
        try {
            Message  message=weChatService.wxCallBack(request,code, state);
            return message;
        }catch (Exception e){
            logger.error(e.toString()+"\n"+e);
            return new Message(ResponseEnum.FALL);
        }
    }
/*
    *//**
     * 回调绑定信息接口
     * @param request
     * @param b_mobile
     * @param b_smsCode
     * @param b_pass
     * @param b_nick
     * @param b_weixinOpenid
     * @return
     *//*
    @RequestMapping(value = "/bangdingWxUser",method = RequestMethod.POST)
    public Message bangdingWxUser(HttpServletRequest request,
                                  String b_mobile, String b_smsCode, String b_pass, String b_nick, String b_weixinOpenid){
        if(StringUtils.isBlank(b_mobile)){
            return new Message(ResponseEnum.USER_MOBLIE_NULL);
        }
        if(StringUtils.isBlank(b_smsCode)){
            return new Message(ResponseEnum.WECHAT_SMS_NULL);
        }
        if(StringUtils.isBlank(b_pass)){
            return new Message(ResponseEnum.WECHATE_PASS_NULL);
        }
        if(StringUtils.isBlank(b_nick)){
            return new Message(ResponseEnum.USER_NICK_NULL);
        }
        if(StringUtils.isBlank(b_weixinOpenid)){
            return new Message(ResponseEnum.WECHATE_OPENDID_NULL);
        }
        // 判断短信验证码
        ResultMessage resultMessage = VerificationCodeUtil.verifySmsCaptcha(b_mobile, b_smsCode);
        if (!resultMessage.getCode().equals("0")) {
            return new Message(Integer.parseInt(resultMessage.getCode()), resultMessage.getMsg());
        }
        try {
            User userMoblie = userService.selectUserByMoblie(b_mobile);
            if (userMoblie != null) {
                logger.info("手机号已存在:"+userMoblie.getMobile());
                if (!SHAUtil.encryption(b_pass).equals(userMoblie.getPasswd())) {
                    return new Message(ResponseEnum.USER_PASS_ERROR);
                }
                userMoblie.setWeixinNick(b_nick);
                userMoblie.setWeixinOpenid(b_weixinOpenid);
                String token = userService.createAndUpdateToken(request, userMoblie);
                return new Message(ResponseEnum.SUCCESS, token);
            } else {
                User user = userService.weChatbinding(request, b_mobile, b_pass, b_nick, b_weixinOpenid);
                publicService.addUser(user);
                logger.info("不存在:"+user.getMobile());
                String token = userService.createAndUpdateToken(request, user);
                return new Message(ResponseEnum.SUCCESS, token);
            }
        } catch (Exception e){
            logger.error(e.toString()+e);
            return new Message(ResponseEnum.FALL);
        }
    }*/
}
