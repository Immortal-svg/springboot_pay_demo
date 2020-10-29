package com.example.demo.common.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jova
 * @create
 */
@Configuration
@Data
public class WxOpenConfig {
    /**
     * appid
     */
    @Value("${wx.open.config.appid}")
    private String appid;

    /**
     * secret
     */
    @Value("${wx.open.config.secret}")
    private String secret;

    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(appid);
        configStorage.setSecret(secret);
        service.setWxMpConfigStorage(configStorage);
        return service;
    }

}
