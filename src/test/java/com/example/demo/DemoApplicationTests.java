package com.example.demo;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        Gson gson=new Gson();
        String arrp[]=new String[]{"1","2","3","4"};
        String []s=gson.fromJson(gson.toJson(arrp),String[].class);
        for (String s1:s) {
            System.out.println(s1);
        }
    }



}
