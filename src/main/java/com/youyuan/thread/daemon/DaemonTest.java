package com.youyuan.thread.daemon;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程分类，分为用户线程和守护线程，线程默认都是用户线程，jvm虚拟机确保用户线程执行完毕
 * 守护线程是为用户线程服务的，jvm虚拟机不用等待守护线程执行完毕
 * 将用户线程设置成守护线程方式,Thread t=new Thread();  t.setDaemon(true);
 * @date 2018/11/13 19:15
 */
public class DaemonTest {

    public static void main(String[] args) {
        You you=new You();
        God god=new God();
        Thread t1=new Thread(god);
        t1.setDaemon(true);//将用户线程设置成守护线程
        t1.start();
        Thread t2=new Thread(you);
        t2.start();
    }
}

/**
 * 模拟用户线程和守护线程
 */
class You implements Runnable{

    @Override
    public void run() {
        for (int i=1;i<=365*100;i++){
            System.out.println("好好生活......");
        }
    }
}

/**
 * 模拟用户线程和守护线程
 */
class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("god is ......");
        }
    }
}
