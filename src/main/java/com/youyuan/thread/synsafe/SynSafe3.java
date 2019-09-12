package com.youyuan.thread.synsafe;

/**
 * @author zhangyu
 * @version 1.0
 * @description synchronized同步代码块性能优化
 * @date 2018/11/14 9:54
 */
public class SynSafe3 {
    public static void main(String[] args) {
        Web12306 w=new Web12306();
        Thread t1=new Thread(w,"购买A");
        Thread t2=new Thread(w,"购买B");
        Thread t3=new Thread(w,"购买C");
        t1.start();
        t2.start();
        t3.start();
    }
}

/**
 * 线程
 */
class Web12306 implements Runnable{
    //票数
    private int count=10;
    //票是否卖完
    private boolean flag=false;

    @Override
    public void run() {
        while (!flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //双重检查，第一次检查是为了提高执行性能，当票没有的情况下线程可以不必监视锁是否被释放,第二次检查是为了判断最后一张票，防止同一张票被多个人购买
    public void buy() throws InterruptedException {
        Thread.sleep(500);
        //判断票是否卖完，如果卖完不用监视同步代码块中的锁了，提高执行性能
        if (count<=0){
            flag=true;
            return;
        }
        //同步代码块，锁的引用地址不能改变，锁的成员变量可以改变，因为此时count是改变的，所以count不能用作锁
        synchronized (this){
            //判断最后一张票
            if (count<=0){
                flag=true;
                return;
            }
            System.out.println(Thread.currentThread().getName()+"购买第"+count--+"张票......");
        }
    }
}
