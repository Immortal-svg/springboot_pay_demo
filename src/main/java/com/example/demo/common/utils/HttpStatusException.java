package com.example.demo.common.utils;

import java.io.IOException;

/**
 *
 * Http 异常
 *
 * date: 2017.12.12
 *
 * @author: lizhuolun
 */
public class HttpStatusException extends IOException {

    int code;

    public HttpStatusException() {
    }

    public HttpStatusException(int code, String message) {
        super(message);
        this.code =code;
    }

    public int getCode(){
        return code;
    }
}
