package com.youyuan.thread.join;

/**
 * @author zhangyu
 * @version 1.0
 * @description join方法是Thread的成员方法，合并线程使其它线程阻塞，当前线程执行完之后其它线程才会进入就绪状态到调度器中等待cpu调用
 * @date 2018/11/13 16:28
 */
public class JoinDemo1 implements Runnable {

    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            System.out.println("子线程开始执行......"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinDemo1 joinDemo1=new JoinDemo1();
        Thread thread=new Thread(joinDemo1);
        thread.start();

        for (int j=1;j<=100;j++){
            if (j==32){
                thread.join();//因为是用的子线程对象调用的join方法，所以main主线程阻塞
            }
            System.out.println("main......"+j);
        }
    }
}
