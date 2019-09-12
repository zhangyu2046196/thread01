package com.youyuan.thread.priority;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程的优先级,线程优先级是数字1-10,设置线程优先级在调用start方法之前设置,优先级越高代表在调度器中待执行的线程被先调用的概率高，优先级越低优先调用的概率低
 * 优先级低的线程也会被调用只是优先调用的概率低,设置优先级的时候可以直接传数字,但是必须在1-10的范围
 * 线程默认的优先级都是5
 * Thread.MAX_PRIORITY 优先级最大值 10
 * Thread.MIN_PRIORITY 优先级最小值 1
 * Thread.NORM_PRIORITY 默认优先级 5
 * @date 2018/11/13 17:44
 */
public class PriorityTest {

    public static void main(String[] args) {
        System.out.println("主线程优先级:"+Thread.currentThread().getPriority());
        PriorityT p=new PriorityT();
        Thread t1=new Thread(p,"线程1");
        Thread t2=new Thread(p,"线程2");
        Thread t3=new Thread(p,"线程3");
        Thread t4=new Thread(p,"线程4");
        Thread t5=new Thread(p,"线程5");
        Thread t6=new Thread(p,"线程6");

        //设置线程优先级priority
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t4.setPriority(Thread.NORM_PRIORITY);
        t5.setPriority(Thread.MIN_PRIORITY);
        t6.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }

    static class PriorityT extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"优先级"+Thread.currentThread().getPriority());
        }
    }
}
