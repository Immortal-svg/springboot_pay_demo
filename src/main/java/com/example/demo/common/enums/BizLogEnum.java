package com.example.demo.common.enums;

/**
 * Created by A
 */
public enum BizLogEnum {
    //zip密码
    ZipPassword("1234!@#$"),
    ACCOUNT("ACCOUNT", "注册日志", 1),
    LOGIN("LOGIN", "连接日志", 2),
    ACCESSLOG("ACCESSLOG", "访问日志", 3),
    MAPPINGLOG("MAPPINGLOG", "映射关系日志", 4);


    private String bizLogType;
    private String bizLogName;
    private String bizLogZipPassword;
    private int sqlType;

    BizLogEnum(String bizLogType, String bizLogName, int sqlType) {
        this.bizLogType = bizLogType;
        this.bizLogName = bizLogName;
        this.sqlType = sqlType;
    }
    BizLogEnum(String bizLogZipPassword) {
      this.bizLogZipPassword=bizLogZipPassword;
    }
    public String getBizLogType() {
        return bizLogType;
    }

    public String getBizLogName() {
        return bizLogName;
    }

    public void setBizLogType(String bizLogType) {
        this.bizLogType = bizLogType;
    }

    public void setBizLogName(String bizLogName) {
        this.bizLogName = bizLogName;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public String getBizLogZipPassword() {
        return bizLogZipPassword;
    }

    public void setBizLogZipPassword(String bizLogZipPassword) {
        this.bizLogZipPassword = bizLogZipPassword;
    }
}
