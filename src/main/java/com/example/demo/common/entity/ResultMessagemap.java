package com.example.demo.common.entity;

import com.example.demo.common.enums.CodeEnum;

import java.util.Map;

/**
 * @author Administrator
 */
public class ResultMessagemap {
    protected String code;
    protected String msg;
    protected Map<String,Object> data;

    public ResultMessagemap(){
        this.code="0";
    }

    public ResultMessagemap(Map<String,Object> data) {
        this.code = CodeEnum.OK.getCode();
        this.msg = CodeEnum.OK.getName();
        this.data = data;
    }


    public ResultMessagemap(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getName();
    }

    public ResultMessagemap(CodeEnum codeEnum, Map<String,Object> data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getName();
        this.data=data;
    }
    public ResultMessagemap(String code , String msg) {
        this.code = code;
        this.msg = msg;
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



    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
