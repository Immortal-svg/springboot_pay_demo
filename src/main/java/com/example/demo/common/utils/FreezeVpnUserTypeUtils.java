package com.example.demo.common.utils;

/**
 * @author Administrator
 * 判断Control_list FREEZEVPNUSER的类型
 */
public class FreezeVpnUserTypeUtils {




    public static String FreezeVpnUserType(int i) {
        switch (i) {
            case 1:
                return "user_intenralid";
            case 2:
                return "mobilephone";
            case 3:
                return "keychain";
            case 4:
                return "mac";
            case 5:
                return "imet";
            case 6:
                return "imst";
            case 7:
                return "user_account";
        }
        return null;
    }
}
