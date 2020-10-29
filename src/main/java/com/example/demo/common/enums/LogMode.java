package com.example.demo.common.enums;

/**
 * 用户连接方式
 */
public enum LogMode {
    SHADOWSOCKS("ShadowSocks"),
    OPENVPN("OpenVpn")
    ;
    private String mode;

    LogMode(String mode) {
        this.mode = mode;
    }
}
