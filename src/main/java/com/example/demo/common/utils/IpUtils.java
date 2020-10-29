package com.example.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Set;

public final class IpUtils {

    private IpUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(IpUtils.class);

//    public static void main(String[] args) {
//        InetAddress netAddress = getInetAddress();
//        System.out.println("host ip:" + getHostIp());
//        System.out.println("host name:" + getHostName(netAddress));
//        Properties properties = System.getProperties();
//        Set<String> set = properties.stringPropertyNames(); //获取java虚拟机和系统的信息。
//        for (String name : set) {
//            System.out.println(name + ":" + properties.getProperty(name));
//        }
//    }


    public static InetAddress getInetAddress() {

        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            System.out.println("unknown host!");
        }
        return null;

    }

    public static String getHostIp() {
        InetAddress netAddress1 = getInetAddress();
        if (null == netAddress1) {
            return null;
        }
        String ip = netAddress1.getHostAddress(); //get the ip address
        return ip;
    }

    public static String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ipAddress) || ipAddress.toLowerCase().contains("unknown")) {
            ipAddress = request.getRemoteAddr();

            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        // 对于通过多级反向代理的情况，第一个非 unknown的IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.indexOf(",") > -1) {
            String[] ipAddressArray = ipAddress.trim().split(",");
            for (int i = 0; i < ipAddressArray.length; i++) {
                if (StringUtils.isNotBlank(ipAddressArray[i])) {
                    ipAddress = ipAddressArray[i];
                    break;
                }
            }
        }
        if (ipAddress != null) {
            ipAddress = ipAddress.trim();
        }
        return ipAddress;
    }

    public static String getHostName(InetAddress netAddress) {
        if (null == netAddress) {
            return null;
        }
        String name = netAddress.getHostName(); //get the host address
        return name;
    }
    public static long ipToLong(String strIp) throws Exception {
        String[]ip = strIp.split("\\.");
        if(ip.length!=4){
            throw new Exception("ip format error");
        }

        return (Long.parseLong(ip[0]) << 24) + (Long.parseLong(ip[1]) << 16) + (Long.parseLong(ip[2]) << 8) + Long.parseLong(ip[3]);
    }

    /**
     * 获取用户实际ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                    //根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
            return ipAddress;
        }catch (Exception e) {
            logger.error(e.toString(),e);
            return  "127.0.0.1";
        }
    }
}
