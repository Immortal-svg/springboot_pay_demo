package com.example.demo.common.enums;

/**
 * Created by qinshixin on 2019/7/18
 * 登录注册返回信息
 */
public enum MsgEnum {

    //黑名单
    SUCCESS(0),
    ERROR(-1),
    FEEDBACK_OK(0,"反馈成功"),
    FEEDBACK_ERROR(-1,"反馈失败"),
    ERROR_black_MESSAGE("添加黑名单失败"),
    ERROR_UNBIND_MESSAGE("解封失败"),
    ERROR_TOKEN_MESSAGE("封堵失败,未获取到Token"),

    REGISTER_OK("请求成功"),
    REGISTER_SUCCESS("success"),
    REGISTER_FAIL("注册失败"),
    REGISTER_FAIL_PHONE_EXIST("该手机号已被注册"),
    REGISTER_FAIL_NICKNAME_EXIST("该昵称已被注册"),
    EGISTER_LOGIN_ERROR("登录失败"),
    EGISTER_LOGIN_YZM("请输入正确的验证码"),
    REGISTER_LOGIN_SUCCESS("登录成功");


    private String msg;
    private int code;

    MsgEnum(String msg) {
        this.msg = msg;
    }

    MsgEnum(int code) {
        this.code = code;
    }

    MsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
