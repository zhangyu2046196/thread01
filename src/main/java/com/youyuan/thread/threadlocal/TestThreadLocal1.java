package com.youyuan.thread.threadlocal;

/**
 * @author zhangyu
 * @version 1.0
 * @description ThreadLocal线程变量，存储的是线程本身的变量，只能本线程可见，修改线程变量的值其它线程变量值不受影响
 * ThreadLocal有以下方法
 * 1、get            获取线程变量值
 * 2、set            设置线程变量值
 * 3、initiaValue   初始化线程变量值
 * @date 2018/11/15 22:30
 */
public class TestThreadLocal1 {
    //通过匿名类初始化线程变量值
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"线程变量值:"+threadLocal.get());
        //重新设置线程变量值
        threadLocal.set(900);
        System.out.println(Thread.currentThread().getName()+"线程变量值"+threadLocal.get());

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"子线程默认线程变量值"+threadLocal.get());
        }).start();

        new Thread(()->{
            //子线程设置线程变量值，不会影响main主线程和上面子线程的值
            threadLocal.set(190);
            System.out.println(Thread.currentThread().getName()+"子线程设置完变量值:"+threadLocal.get());
        }).start();
    }
}
