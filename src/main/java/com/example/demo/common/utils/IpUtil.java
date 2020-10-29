package com.example.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jw on 2016/8/5.
 */
public class IpUtil {
    /**
     * <p>
     * Web 服务器反向代理中用于存放客户端原始 IP 地址的 Http header 名字，
     * 若新增其他的需要增加或者修改其中的值。
     * </p>
     */
    private static final String[] PROXY_REMOTE_IP_ADDRESS = { "X-Forwarded-For", "X-Real-IP" };
    /**
     * <p>
     * 获取请求的客户端的 IP 地址。若应用服务器前端配有反向代理的 Web 服务器，
     * 需要在 Web 服务器中将客户端原始请求的 IP 地址加入到 HTTP header 中。
     * 详见 {@link #PROXY_REMOTE_IP_ADDRESS}
     */
    public static String getRealIp( HttpServletRequest request ) {
        for ( int i = 0 ; i < PROXY_REMOTE_IP_ADDRESS.length ; i++ ) {
            String ip = request.getHeader( PROXY_REMOTE_IP_ADDRESS[i] );
            if ( ip != null && ip.trim().length() > 0 ) {
                int commaOffset = ip.trim().indexOf( ',' );
                if ( commaOffset < 0 ) {
                    return ip.trim();
                }
                return ip.trim().substring( 0 , commaOffset );
            }
        }
        return request.getRemoteHost();
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
                if(StringUtils.isNotBlank(ipAddressArray[i])){
                    ipAddress = ipAddressArray[i];
                    break;
                }
            }
        }
        if(ipAddress!=null){
            ipAddress = ipAddress.trim();
        }
        return ipAddress;
    }

    public static long ipToLong(String ipStr)
    {
        try {
            InetAddress ip = InetAddress.getByName(ipStr);
            if(ip == null){
                return 0;
            }
            byte[] bytes = ip.getAddress();
            long[] longIp = new long[4];
            for(int i=0;i<4;i++){
                longIp[i] = (long)bytes[i];
                if(longIp[i] < 0){
                    longIp[i] = longIp[i] + 256;
                }
            }
            return (longIp[3] << 24) | (longIp[2] << 16) | (longIp[1] << 8) | longIp[0];
        } catch (UnknownHostException e) {

        }
        return 0;
    }

    public static boolean containIp(long begin, long end, long ip)
    {
        return (compareIp(begin, ip) <= 0 && compareIp(end, ip) >= 0);
    }

    public static int compareIp(long ip1, long ip2)
    {
        byte[] ipAddr1 = longToIpBytes(ip1);
        byte[] ipAddr2 = longToIpBytes(ip2);
        return compareIp(ipAddr1, ipAddr2);
    }

    private static int compareIp(byte[] ip, byte[] beginIP)
    {
        for (int i = 0; i < 4; i++)
        {
            int r = compareByte(ip[i], beginIP[i]);
            if (r != 0)
                return r;
        }
        return 0;
    }

    private static int compareByte(byte bsrc, byte bdst)
    {
        if ((bsrc & 0xFF) > (bdst & 0xFF))
            return 1;
        else if ((bsrc ^ bdst) == 0)
            return 0;
        else
            return -1;
    }

    private static byte[] longToIpBytes(long ip)
    {
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(ip % 256);
        bytes[1] = (byte)((ip >> 8) % 256);
        bytes[2] = (byte)((ip >> 16) % 256);
        bytes[3] = (byte)((ip >> 24) % 256);

        return bytes;
    }
}
