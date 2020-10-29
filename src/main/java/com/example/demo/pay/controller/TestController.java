package com.example.demo.pay.controller;


import com.example.demo.pay.po.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/hell")
    public Object t(String token) {
        System.out.println("token:"+token);
        return "Hello Wrod";
    }

    public static void main(String[] args) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        System.out.println(System.currentTimeMillis());
        Date date = stampToDate(1602323204L * 1000);
        System.out.println(date);
        System.out.println(simpleDateFormat.format(date));
        list();
    }

    /**
     * 判断两个日期相差多少分钟
     *
     * @param benginDate
     * @param endDate
     * @return
     */
    public static long twoTimeDifference(Date benginDate,Date endDate){
        long begin = benginDate.getTime();
        long end = endDate.getTime();
        long minutes =(end - begin) / (1000 * 60);

        return  minutes;
    }

    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(Long s){
        long lt = new Long(s);
        Date date = new Date(lt);
        return date;
    }

    public static  void list() throws  Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月份是MM
        List<User> users=new ArrayList<>();
        users.add(new User("1",simpleDateFormat.parse("2020-10-12 10:31:00")));
        users.add(new User("3",simpleDateFormat.parse("2020-10-12 10:35:00")));
        users.add(new User("2",simpleDateFormat.parse("2020-10-12 10:29:00")));

        //升序
        users.sort(Comparator.comparing(User::getId));

        //降序
        //users.sort((m1, m2) -> m2.getId().compareTo(m1.getId()));
        System.out.println("最后："+users.get(users.size()-1).getId());
        Map<String,String> taskresultMap=new HashMap<>();
        taskresultMap.put("1","1");
        taskresultMap.put("3","3");
        users.stream().forEach(user -> {
            System.out.println(user.getId()+":"+simpleDateFormat.format(user.getCreateTime()));
        });
        System.out.println("-------------------------------");
       /* users.stream().filter(user-> taskresultMap.get(user.getId())!=null ).forEach(user->{
            System.out.println("map:"+taskresultMap.get(user.getId())+"{}"+user.getId()+":"+simpleDateFormat.format(user.getCreateTime()));
        });*/
       /* try {
            System.out.println(twoTimeDifference(simpleDateFormat.parse("2020-10-11 12:05:00"), new Date()));
        }catch (Exception e){

        }*/
    }
}
