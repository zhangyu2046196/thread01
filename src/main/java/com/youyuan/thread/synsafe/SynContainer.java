package com.youyuan.thread.synsafe;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author zhangyu
 * @version 1.0
 * @description 容器并发操作,CopyOnWriteArrayList集合里面实现了synchronized同步方法，是线程安全的，在工作内存与主内存交互的时候保证了线程安全
 * @date 2018/11/14 11:46
 */
public class SynContainer {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList<String>();
        for (int i=1;i<=1000;i++){
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(10000);
        System.out.println("list size "+list.size());
    }

}
