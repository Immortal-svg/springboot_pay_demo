package com.example.demo.common.enums;

/**
 * Created A
 */
public enum CodeEnum {

    OK("0", "success"),
    ERROR("-1","ERROR"),
    ISEXIT_Identifier("-1","必要信息不能为空"),
    ISEXIT_TYPE("-1","格式不正确"),
    ISEXIT_NO("-1","不存在"),
    EXIT_CONTROL_LIST("-1","您的账号进行了违规操作，已加入黑名单！"),
    // 验证码
    E4015("4015", "验证失败，请重新发送验证码."),
    E4016("4016", "验证码超时，请重新发送验证码."),
    E4017("4017", "验证码错误"),
    E4018("4018","触发日发送限额"),
    E4019("4019", "用户未注册."),
    E4020("4020", "请输入有效的手机号."),
    E4021("4020", "你获取验证码已达到限额."),
    E9999("9999", "未知异常"),
    E101("101", "验证失败"),
    E102("102", "手机号码格式不正确"),
    E103("103", "会员级别不够"),
    E104("104", "内容未审核"),
    E105("105", "内容过多"),
    E111("111", "密码错误"),
    E106("106", "账户余额不足"),
    E107("107", "Ip受限"),  
	E108("108", "手机号码发送太频繁，请换号或隔天再发"),  
	E109("109", "帐号被锁定"), 
	E110("110", "手机号发送频率持续过高，黑名单屏蔽数日"), 
	E120("120", "系统升级"),
	E121("121", "30秒内不能重复发送短信"),
	E122("122", "1小时内只能发送5条短信"),
	E123("123", "24小时内只能发送10条短信");

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
