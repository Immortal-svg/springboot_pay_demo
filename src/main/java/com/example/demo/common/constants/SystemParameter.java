package com.example.demo.common.constants;

public class SystemParameter {

    private SystemParameter(){}

    public static final String REDISTOKENPREFIX = "vpn:token:";  // id-token  vpn:token:12 tyuyuh%&2334
    public static final String REDISSERVICELISTPREFIX = "vpn:service_list:";
    //public static final String USERDETAILREFIX = "vpn:user";
    public static final long REDIS_VALIDATY = 60*60*24*30;

    public static final int LOG_FILE_MAX_SIZE = 5;
    public static final String LOG_FILE_MAX_SIZE_UNIT = "K";
    public static final int LOG_FILE_WRITE_TIMEOUT = 1;

    public static String bizlogLocalPath;
    public static String smsIsdebug;
}
