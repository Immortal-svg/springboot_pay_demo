package com.example.demo.common.enums;

/**
 * Created by lff on 2016/9/12.
 */
public enum ResponseEnum {
    FALL(-1,"请求失败"),

    SUCCESS(0, "SUCCESSS"),

    ERROR_ISEXITNULL(-1,"必要信息不能为空");

    private Integer code;
    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
