package com.youyuan.thread.synsafe;

/**
 * @author zhangyu
 * @version 1.0
 * @description 使用synchronized同步方法使线程安全
 * @date 2018/11/13 20:09
 */
public class SynSafe1 {
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

    private int count=10;

    private boolean flag=true;

    @Override
    public void run() {
        while (count>0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            qiang();
        }
    }

    public synchronized void qiang(){
        if (count<=0){
            flag=false;
            return;
        }
        System.out.println(Thread.currentThread().getName()+"抢到第"+count--+"票");
    }
}
