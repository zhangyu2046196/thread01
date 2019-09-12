package com.youyuan.thread.syn;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程不安全应用场景之12306购票
 * @date 2018/11/13 20:09
 */
public class UnSafe1 {
    public static void main(String[] args) {
        Piao piao=new Piao();
        new Thread(piao,"黄牛1").start();
        new Thread(piao,"黄牛2").start();
        new Thread(piao,"黄牛3").start();
    }
}

/**
 * 购票
 */
class Piao implements Runnable{

    private Integer count=10;

    @Override
    public void run() {
        while (count>0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"抢到第"+count--+"票");
        }
    }
}
