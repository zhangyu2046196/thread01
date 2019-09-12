package com.youyuan.thread.cooperation;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程通信 信号灯法
 * 通过标志位来使线程等待或唤醒
 * 以下以看电视表演为例演示，表演者表演时观众等待，观众观看时表演者等待
 * @date 2018/11/14 17:12
 */
public class ThreadCooperation2 {

    public static void main(String[] args) {
        Tv tv=new Tv("奇葩说");
        new Thread(new Player(tv)).start();
        new Thread(new Watcher(tv)).start();
    }

}

/**
 * 电视
 */
class Tv{
    //电视节目 名 称
    private String name;
    //标志位
    //true  表演者表演观众等待
    //false 表演者等待观众观看
    private boolean flag=true;

    public Tv(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 表演者表演
     * @param content 表演内容
     */
    public synchronized void play(String content){
        if (!flag){
            try {
                this.wait();//线程阻塞，等待观众着观看后唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演者表演"+content);
        notifyAll();
        this.flag=false;
        this.name=content;
    }

    /**
     * 观看者观看节目
     */
    public synchronized void watch(){
        if (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看者观看节目"+this.name);
        notifyAll();
        this.flag=true;
    }
}

/**
 * 表演者线程
 */
class Player implements Runnable{
    //节目
    private Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i=1;i<=20;i++){
            if (i%2==0){
                tv.play("奇葩说");
            }else {
                tv.play("广告");
            }
        }
    }
}

/**
 * 消费者线程
 */
class Watcher implements Runnable{
    //节目
    private Tv tv;

    public Watcher(Tv tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i=1;i<=20;i++){
            tv.watch();
        }
    }
}
