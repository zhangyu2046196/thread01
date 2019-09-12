package com.youyuan.thread.yield;

/**
 * @author zhangyu
 * @version 1.0
 * @description yield方法是礼让线程，使线程暂停从运行状态直接转入就绪状态，进入调度器等待cpu调用，yield线程不是阻塞线程
 * @date 2018/11/13 15:52
 */
public class YieldDemo1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start------>");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+" end------>");
    }

    public static void main(String[] args) {
        YieldDemo1 yieldDemo1=new YieldDemo1();
        new Thread(yieldDemo1,"A").start();
        new Thread(yieldDemo1,"B").start();
    }
}
