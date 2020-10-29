package com.example.demo.pay.po;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    public User(String id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }

    private String id;

    private Date createTime;


}
