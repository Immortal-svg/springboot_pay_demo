package com.example.demo.common.utils;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ResMessage<T> implements Serializable {

    private List<T> list;
    private String msg;
    private boolean rel;
    private T obj;

    public ResMessage(String msg, boolean rel, T obj, Integer count) {
        super();
        this.msg = msg;
        this.rel = rel;
        this.obj = obj;
        this.count = count;
    }

    public ResMessage(List<T> list, String msg, boolean rel, Integer count) {
        super();
        this.list = list;
        this.msg = msg;
        this.rel = rel;
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private Integer count;

    @Override
    public String toString() {
        return "ResMessage [list=" + list + ", msg=" + msg + ", rel=" + rel + ", obj=" + obj + ", count=" + count + "]";
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }


}
