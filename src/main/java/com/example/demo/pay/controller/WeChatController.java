package com.example.demo.pay.controller;



import com.example.demo.common.constants.Message;
import com.example.demo.common.enums.ResponseEnum;
import com.example.demo.common.utils.IpUtil;
import com.example.demo.pay.service.WeChatService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "微信H5支付接口")
@RestController
@RequestMapping("/api/wx")
public class WeChatController {

    private final static Logger logger= LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    private WeChatService weChatService;

    /**
     * 微信h5支付请求接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wxPay")
    public Message wxPay(HttpServletRequest request){
        Message message = weChatService.WeChatPay(request);
        return message;
    }

    /**
     * 微信h5支付回调接口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/wxPayCallBlack")
    public void wxPayCallBlack(HttpServletRequest request, HttpServletResponse response){
       try {
           weChatService.weixin_notify(request, response);
       }catch (Exception e){
           logger.info("微信h5回调异常：{}"+e.toString(),e);
       }
    }

    /**
     * 微信h5支付订单查询接口
     *
     * @param request
     * @param response
     * @param outTradeNo
     * @return
     */
    @RequestMapping(value = "/wxPayOrderQuery")
    public Message wxPayOrderQuery(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(defaultValue = "") String outTradeNo){
        if (StringUtils.isBlank(outTradeNo)) {
            return new Message(ResponseEnum.ERROR_ISEXITNULL);
        }
        logger.info("订单号查看：{}"+outTradeNo);
        Message message = weChatService.weChatOrderQuery(request, outTradeNo);
        logger.info("查询状态msg：{}"+message.getMsg()+"code：{}"+message.getCode());
        return  message;
    }

}
