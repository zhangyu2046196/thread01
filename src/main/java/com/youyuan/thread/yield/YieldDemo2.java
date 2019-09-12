package com.youyuan.thread.yield;

/**
 * @author zhangyu
 * @version 1.0
 * @description yield线程暂停直接进入就绪状态,到调度器等待cpu调用
 * @date 2018/11/13 15:59
 */
public class YieldDemo2 {

    public static void main(String[] args) {
        new Thread(()->{
            for (int i=1;i<=100;i++){
                System.out.println("lambda表达式"+i);
            }
        }).start();

        for (int j=1;j<=100;j++){
            Thread.yield();
            System.out.println("main"+j);
        }
    }
}
