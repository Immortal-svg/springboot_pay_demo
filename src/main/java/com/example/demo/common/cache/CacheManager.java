package com.example.demo.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Allen on 2017/5/19.
 * 缓存管理
 */
public class CacheManager {
    private static CacheManager cacheManager;
    private Map<Object, Object> caches;

    private CacheManager() {
        caches = new ConcurrentHashMap<Object, Object>();
    }

    /**
     * 获取唯一实例
     *
     * @return instance
     */
    public static CacheManager getInstance() {
        if (cacheManager == null) {
            synchronized (CacheManager.class) {
                if (cacheManager == null) {
                    cacheManager = new CacheManager();
                }
            }
        }
        return cacheManager;
    }

    //用于保存缓存
    public void addCache(Object key, Object value) {
        caches.put(key, value);
    }

    //用于得到缓存
    public Object getCache(Object key) {
        if (caches.containsKey(key)) {
            return caches.get(key);
        }
        return null;
    }

    //用于清除缓存信息
    public void clearCache() {
        caches.clear();
    }

    //用于清除指定的缓存信息
    public void removeCache(Object key) {
        if (caches.containsKey(key)) {
            caches.remove(key);
        }
    }

//    public static void main(String[] args) {
//        CacheManager cacheManager = CacheManager.getInstance();
//        System.out.println(cacheManager.getCache(111));
//    }
}
