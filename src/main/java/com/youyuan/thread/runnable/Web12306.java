package com.youyuan.thread.runnable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 模拟多线程抢票
 * @date 2018/11/9 13:50
 */
public class Web12306 implements Runnable {
    /**
     * 初始100张票
     */
    private static int count=100;

    public void run() {
        while(true){
            if (count<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"抢到第"+count--);
        }
    }

    public static void main(String[] args) {
        Web12306 web12306=new Web12306();
        new Thread(web12306,"有缘").start();
        new Thread(web12306,"京东").start();
        new Thread(web12306,"淘宝").start();
    }
}
