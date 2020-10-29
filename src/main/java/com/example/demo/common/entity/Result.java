package com.example.demo.common.entity;




public class Result<T> {

    private boolean success;

    private String message;

    private T data;

    private Exception exception;


    public Result() {
        super();
    }

    public Result(boolean success) {
        super();
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public static <T> Result<T> create() {
        return create(false);
    }

    public static <T> Result<T> create(boolean success) {
        return new Result<T>(success);
    }
}
