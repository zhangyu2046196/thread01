package com.youyuan.thread.join;

import java.util.TreeMap;

/**
 * @author zhangyu
 * @version 1.0
 * @description join是Thread的成员方法，合并线程，使其它线程阻塞，等当前线程执行完后其它线程才会进入就绪状态到调度器中等待cpu重新调用
 * 此处模拟父亲抽烟，没有烟让儿子去买烟过程
 * @date 2018/11/13 16:35
 */
public class JoinDemo2 {

    public static void main(String[] args) {
        Father father=new Father();
        father.start();
    }

}

/**
 * 模拟父亲类
 */
class Father extends Thread{
    @Override
    public void run() {
        System.out.println("父亲想抽烟，发现没有烟了");
        System.out.println("父亲给儿子钱，让儿子去买烟");
        Son son=new Son();
        son.start();
        try {
            son.join();//因为用的是son线程对象调用的join方法，所以此时父亲线程阻塞，等son线程执行完，父亲线程才会进入就绪状态到调度器等待cpu调度
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("父亲接过烟，把剩下钱给了儿子");
    }
}

/**
 * 模拟儿子
 */
class Son extends Thread{
    @Override
    public void run() {
        System.out.println("儿子接过钱去买烟");
        System.out.println("在路上看到游戏厅玩了10秒");
        for (int i=1;i<=10;i++){
            System.out.println("开始玩"+i+"秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("儿子赶紧去买烟");
        System.out.println("儿子拿着烟回来给了父亲");
    }
}
