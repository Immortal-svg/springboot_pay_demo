package com.example.demo.common.enums;

/**
 * Created by lff on 2016/9/12.
 */
public enum ResponseEnum {
    OK(0,"请求成功"),
    SUCCESS(1, "success"),
    FAIL_PRODUCT(-1,"无产品信息"),
    NO_LOGGIN(-2,"请先登录!"),
    NO_REDIS(-2,"暂无缓存!请稍后"),
    FAIL_NOTUSER(-1,"没有用户信息"),
    FAIL_PHONE(-1,"手机号未注册"),
    FAIL_INSERT_ORDER(-1,"生成订单失败"),
    FAIL_ORDERSTATUS(-1,"取消失败"),
    FAIL_ORDERSTATUSA(-1,"验证码错误"),
    FAIL_ORDERS(-1,"查询失败"),
    ORDER_OVERTIME(-1,"订单超时"),
    ORDER_NOT_OVERTIME(0,"订单在时间范围内"),
    HAVA_UN_PAY(-1,"有未完成订单"),
    ERROR_FORMATERROR(-1,"格式不正确"),
    ERROR_ISEXITNULL(-1,"必要信息不能为空"),
    ORDERPAY_NOPAYTYPE(-1,"支付类型不存在"),
    ORDERPAY_PAYFAIL(-1,"支付发生异常："),
    ORDERPAY_NOUSERORDER(-1,"没有找到用户订单信息"),
    NOT_HAVE_UN_PAY(0,"无未完成订单"),
    NOT_HAVE_DICT_INFO(0, "数字字典无数据"),
    CDK_STARTS_USE(-1,"CDK已被使用"),
    CDK_STARTS_NOTCDK(-1,"CDK无效"),
    CDK_CREATECDK_ERROR(-1,"CDK请求异常(出现重复)"),
    CDK_CREATECDK_TOTAL(-1,"产生CDk不得超过100个"),
    PAY_IS_CLOSE(-1,"系统测试中，敬请期待"),
    DEVICE_ISNOT(-1,"设备信息不能为空!"),
    CDK_STARTS_OK(0,"充值成功");

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
