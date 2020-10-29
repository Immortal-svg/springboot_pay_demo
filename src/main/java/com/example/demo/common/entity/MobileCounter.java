package com.example.demo.common.entity;

import java.util.concurrent.atomic.AtomicInteger;


public class MobileCounter {
    private AtomicInteger count = new AtomicInteger(0);
    public void inc(){
        count.incrementAndGet();
    }

    public void inc(int val){
        count.addAndGet(val);
    }

    public int get(){
        return count.get();
    }
    
    public void set(int val){
        count.lazySet(val);
    }
}
