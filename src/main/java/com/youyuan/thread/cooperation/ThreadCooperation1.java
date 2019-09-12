package com.youyuan.thread.cooperation;

import java.io.Serializable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程间的通信之管程法
 * 管程法是指有一个生产者、一个消费者、缓存区，生产者生产的消息放入缓存区，消费者从缓存区获取消息，如果缓存区满了，生产者等待消费者
 * 消费后在往缓存区放消息，如果缓存区没有消息了，消费者等待生产者放入消息
 *
 * Object类有以下几个方法来进行线程通信
 *  wait() 当前线程等待
 *  wait(long m) 参数时间，当前线程等待多长时间
 *  notify() 唤醒同一监视对象的等待线程(如果是多个随机唤醒,优先级高的线程优先被调用的概率高)
 *  notifyAll() 唤醒同一监视对象的所有等待线程
 *
 * 以上几个方法必须在同步方法或同步代码块中调用
 *
 * @date 2018/11/14 15:34
 */
public class ThreadCooperation1 {

    public static void main(String[] args) {
        Container container=new Container();
        Thread p1=new Thread(new Producer(container),"厂商1");
        Thread p2=new Thread(new Producer(container),"厂商2");
        p1.start();
        p2.start();
        Thread c1=new Thread(new Consumer(container),"有缘");
        c1.start();
    }

}

/**
 * 消息bean(放入缓存区的对象)
 */
class Bread implements Serializable{
    /**
     * 名称
     */
    private String name;

    public Bread(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * 容器，缓存区
 */
class Container{
    //初始化容器
    private Bread[] breads=new Bread[100];
    //容器下标
    private int position=-1;

    /**
     * 生产者放入消息
     * @param bread 消息
     */
    public void push(Bread bread) throws InterruptedException {
        if (bread==null){
            return;
        }
        synchronized (this){
            //容器中的数据已经满
            if (position>=breads.length-1){
                System.out.println("容器中数据已满");
                this.wait();//线程阻塞，等待消费者消费后通知才能进入就绪状态到调度器等待cpu调用
            }
            breads[++position]=bread;
            System.out.println(bread.getName());
            notifyAll();//唤醒消费者消费消息
        }
    }

    /**
     * 消费者从缓存区消费消息
     */
    public synchronized Bread pop() throws InterruptedException {
        if (position<0){
            System.out.println("容器中数据为空");
            this.wait();//线程阻塞，等待生产者生产数据后放入缓存区唤醒在进入就绪状态等待cpu调用
        }
        Bread bread=breads[position];
        System.out.println(bread.getName());
        position--;
        notifyAll();//唤醒生产者生产消息
        return bread;
    }
}

/**
 * 生产者
 */
class Producer implements Runnable{
    //容器
    private Container container;

    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i=1;i<=100;i++){
            try {
                container.push(new Bread(Thread.currentThread().getName()+"生产第"+i+"个面包"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable{
    //容器
    private Container container;

    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        try {
            for (int i=1;i<=10;i++){
                Bread bread=container.pop();
                if (bread!=null){
                    System.out.println(Thread.currentThread().getName()+"消费第"+i+"个面包");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
