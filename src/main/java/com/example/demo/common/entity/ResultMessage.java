package com.example.demo.common.entity;


import com.example.demo.common.enums.CodeEnum;

import java.util.Map;

/**
 * Created by A
 */
public class ResultMessage<T> {
    protected String code;
    protected String msg;
    protected T data;
    public ResultMessage(){
        this.code="0";
    }

    public ResultMessage(T data) {
        this.code = CodeEnum.OK.getCode();
        this.msg = CodeEnum.OK.getName();
        this.data = data;
    }

    public ResultMessage(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getName();
    }
    public ResultMessage(CodeEnum codeEnum, T t) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getName();
        this.data = t;
    }

    public String getCode() {
        return code;
    }

    public void setCode(CodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getName();
    }

    public void setCode(CodeEnum code, String... args) {
        this.code = code.getCode();
        this.msg = String.format(code.getName(), args);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
