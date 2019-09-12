package com.youyuan.thread.lock;

/**
 * @author zhangyu
 * @version 1.0
 * @description 死锁：比如线程1调用方法占用锁A，锁A的同步代码块中还需要锁B，线程1需要拿到锁A、B后才能执行完代码
 * 线程2调用方法占用锁B，锁B的同步代码块中还需要锁A，线程2需要拿到锁B、A后才能执行完代码，如果线程并发会造成死锁，都拿不到对方的锁
 *
 * 解决死锁的方法:不要在同步代码块中嵌套同步代码块，并且使用多个不同的对象锁
 *
 * 以下用一个场景来描述死锁和解决死锁的问题
 *  用户A照镜子拥有镜子锁，但又要拿口红，用户B拿着口红拥有口红锁，但又要照镜子，这个时候A拿不到口红锁，B拿不到镜子锁，这时候就造成了死锁
 *
 * @date 2018/11/14 13:42
 */
public class DeadLock {

    public static void main(String[] args) {
        new Thread(new MakeUp(0),"范冰冰").start();
        new Thread(new MakeUp(1),"李冰冰").start();
    }

}

/**
 * 镜子
 */
class Mirror{

}

/**
 * 口红
 */
class Lipstick{

}

/**
 * 化妆
 */
class MakeUp implements Runnable{
    //模拟一个镜子
    private static Mirror mirror=new Mirror();
    //模拟一个口红
    private static Lipstick lipstick=new Lipstick();
    //条件
    private int chose;//0先拿镜子 1先拿口红
    public MakeUp(int chose){
        this.chose=chose;
    }
    @Override
    public void run() {
        make();
    }

    /**
     * 化妆
     */
    public void make(){
        if (chose==0){//先拿镜子
            synchronized (mirror){
                System.out.println(Thread.currentThread().getName()+"照镜子");
                try {
                    //等待1秒后去拿口红
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
/*                synchronized (lipstick){
                    System.out.println(Thread.currentThread().getName()+" 抹口红");
                }*/
                //此时执行完释放了镜子对象锁
            }

            synchronized (lipstick){
                System.out.println(Thread.currentThread().getName()+" 抹口红");
                //此时执行完释放口红对象锁
            }
        }else {//先拿口红
            synchronized (lipstick){
                System.out.println(Thread.currentThread().getName()+" 抹口红");
                try {
                    //等待2秒后去照镜子
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
/*                synchronized (mirror){
                    System.out.println(Thread.currentThread().getName()+"照镜子");
                }*/
                //此时执行完释放口红对象锁
            }
            synchronized (mirror){
                System.out.println(Thread.currentThread().getName()+"照镜子");
                //此时执行完释放镜子对象锁
            }
        }
    }
}


