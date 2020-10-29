package com.example.demo.common.utils;

/**
 * Created by Allen on 2017/4/5.
 */
public final class PageUtil {
    private PageUtil() {
    }

    public static boolean isAsc(String order) {
        if (order.contains("asc")) {
            return true;
        }
        return false;
    }
}
