package com.youyuan.thread;

/**
 * @author zhangyu
 * @version 1.0
 * @description 初识多线程
 * 实现多线程方法
 * 1、继承Thread类，重写run方法
 * 2、实现Runnable接口，重写run方法
 * 3、实现Callable接口，重写call方法
 *
 * 启动线程是调用start方法,start方法内部会执行run方法，调用start方法不会立即被执行，而是等待cpu的调度
 * @date 2018/11/9 9:51
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("工作......");
        }
    }

    public static void main(String[] args) {

        TestThread testThread=new TestThread();
        //启动线程
        testThread.start();

        for (int j=0;j<20;j++){
            System.out.println("休息......");
        }
    }
}
