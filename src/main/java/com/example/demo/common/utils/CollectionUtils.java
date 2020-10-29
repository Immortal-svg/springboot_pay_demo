package com.example.demo.common.utils;

import com.google.common.cache.CacheBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * jiazhao
 * <p/>
 * 提供集合对象的快速创建, 包括 map, list, set, cacheMap
 */
public class CollectionUtils {

  /**
   * 不能创建实例, 即使使用反射, 也会抛异常
   */
  private CollectionUtils() {
    throw new IllegalAccessError("could not instance CollectionUtils!");
  }

  /**
   * <p> 方法描述：克隆一个map内的所有数据到一个新的map, 这样可以不改变原有map的东西. </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a>
   * <br/> 创建时间：2011-11-24 上午01:16:33 <br/>
   */
  public static <K, V> Map<K, V> cloneHashMap(Map<K, V> map) {
    return new HashMap<K, V>(map);
  }


  /**
   * <p> 新建一个同步的集合. </p>
   *
   * @author jiacheo
   * @created at 2012-3-7 下午05:05:44
   */
  public static <T> Set<T> newSyncSet() {
    return Collections.synchronizedSet(new HashSet<T>());
  }

  /**
   * <p> 新建一个并发hashmap </p>
   *
   * @author jiacheo
   * @created at 2012-3-7 下午04:54:40
   */
  public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap() {
    return new ConcurrentHashMap<K, V>();
  }

  /**
   * <p> 新建一个并发hashmap, 使用初始容量 initCapacity </p>
   *
   * @author jiacheo
   * @created at 2012-3-7 下午04:55:19
   */
  public static <K, V> Map<K, V> newConcurrentHashMap(int initCapacity) {
    return new ConcurrentHashMap<K, V>(initCapacity);
  }

  /**
   * <p> 方法描述：新建一个hashMap </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:17:03 <br/>
   */
  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<K, V>();
  }

  /**
   * <p> 方法描述：新建一个hashMap </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:17:21 <br/>
   */
  public static <K, V> Map<K, V> newHashMap(int capacity) {
    return new HashMap<K, V>(capacity);
  }

  /**
   * <p> 方法描述：新建一个TreeMap </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:17:31 <br/>
   */
  public static <K, V> Map<K, V> newTreeMap() {
    return new TreeMap<K, V>();
  }

  /**
   * <p> 方法描述：新建一个数组表 </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/> 创建时间：2011-11-24
   * 上午01:17:44 <br/>
   */
  public static <T> List<T> newArrayList() {
    return new ArrayList<T>();
  }

  /**
   * <p> 方法描述：新建一个数组表 </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/> 创建时间：2011-11-24
   * 上午01:17:55 <br/>
   */
  public static <T> List<T> newArrayList(int capacity) {
    return new ArrayList<T>(capacity);
  }

  /**
   * 新建一个ArrayList，存入参数。不同于Arrays.asList,这个可以修改。 参数为空时返回空List，不是null。
   */
  public static <T> List<T> newArrayList(T... objs) {
    if (objs == null) {
      return new ArrayList<>();
    }
    List<T> list = new ArrayList<>(objs.length);
    for (T obj : objs) {
      list.add(obj);
    }
    return list;
  }

  /**
   * <p> 方法描述：新建一个链表 </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/> 创建时间：2011-11-24
   * 上午01:18:12 <br/>
   */
  public static <T> List<T> newLinkedList() {
    return new LinkedList<T>();
  }

  /**
   * <p> 方法描述：创建一个缓存用的并发map </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:14:23 <br/>
   *
   * @param expire value失效
   * @param timeUnit 时间单位
   */
  @SuppressWarnings({"unchecked"})
  public static <K, V> ConcurrentMap<K, V> newCacheMap(long expire, TimeUnit timeUnit) {
    return (ConcurrentMap<K, V>) CacheBuilder.newBuilder().expireAfterWrite(expire, timeUnit)
      .concurrencyLevel(16)
      .build().asMap(); //.expiration(expire, timeUnit).makeMap();
  }

  /**
   * 创建一个缓存用的并发map, 失效时间是根据上次访问来计算的, 每次访问(R/W)都会更新该时间
   */
  @SuppressWarnings("unchecked")
  public static <K, V> ConcurrentMap<K, V> newEaaCacheMap(long expire, TimeUnit timeUnit) {
    return (ConcurrentMap<K, V>) CacheBuilder.newBuilder().expireAfterAccess(expire, timeUnit)
      .concurrencyLevel(16)
      .build().asMap(); //.expiration(expire, timeUnit).makeMap();
  }

  /**
   * <p> 方法描述：创建一个缓存用的并发map </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:14:23 <br/>
   *
   * @param expire value失效
   * @param timeUnit 时间单位
   */
  @SuppressWarnings("unchecked")
  public static <K, V> ConcurrentMap<K, V> newCacheMap(long expire, TimeUnit timeUnit,
    int maxmunSize) {
    return (ConcurrentMap<K, V>) CacheBuilder.newBuilder().expireAfterWrite(expire, timeUnit)
      .concurrencyLevel(16)
      .maximumSize(maxmunSize).build()
      .asMap(); //new MapMaker().expiration(expire, timeUnit).maximumSize(maxmunSize).makeMap();
  }

  /**
   * <p> 方法描述：新建一个HashSet </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:22:38 <br/>
   */
  public static <E> Set<E> newHashSet() {
    return new HashSet<E>();
  }

  /**
   * <p> 方法描述：新建一个HashSet </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:22:52 <br/>
   */
  public static <E> Set<E> newHashSet(int capacity) {
    return new HashSet<E>(capacity);
  }

  /**
   * <p> 方法描述：新建一个TreeSet </p> 创建人：<a href="mailto:jiacheo@taovip.com">jiacheo</a> <br/>
   * 创建时间：2011-11-24 上午01:22:56 <br/>
   */
  public static <E> Set<E> newTreeSet() {
    return new TreeSet<E>();
  }

  public static String join(Collection<?> c, String connector) {

    if (c == null) {
      return "";
    }

    int size = c.size();
    if (size == 0) {
      return "";
    }

    int index = 0;
    StringBuffer sb = new StringBuffer();
    for (Object obj : c) {
      sb.append(obj);
      if (index < size - 1) {
        sb.append(connector);
      }
      index++;
    }
    return sb.toString();
  }

  public static boolean isNullOrEmpty(Collection<?> c) {
    if (c == null) {
      return true;
    }
    return c.isEmpty();
  }
}
