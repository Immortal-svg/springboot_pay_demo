package com.example.demo.common.enums;

/**
 * Created A
 */
public enum CodeEnum {

    OK("0", "success"),

    // 验证码
    E4015("4015", "验证失败，请重新发送验证码."),
    E4016("4016", "验证码超时，请重新发送验证码."),
    E4017("4017", "验证码错误");

    // 成员变量
    private String code;
    private String name;

    // 构造方法
    CodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
