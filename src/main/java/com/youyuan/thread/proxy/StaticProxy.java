package com.youyuan.thread.proxy;

import java.util.Map;

/**
 * @author zhangyu
 * @version 1.0
 * @description 静态代理方式 来模拟Runnable和Thread的方式
 * 静态代理方式需要 真是对象和代理对象都实现公共接口，真实对象传到代理对象中来调用接口的实现方法
 * @date 2018/11/12 17:50
 */
public class StaticProxy {

    public static void main(String[] args) {
        new MarryCompany(new RealMarry()).happyMarry();//通过静态代理方式调用方法

        //new Thread(new Runnable()).start();//Thread是Runnable的静态代理
    }

}

/**
 * 接口
 */
interface Marry{
    public void happyMarry();
}

/**
 * 真实对象实现公共接口
 */
class RealMarry implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("结婚真高兴......");
    }
}

/**
 * 代理对象实现公共接口
 */
class MarryCompany implements Marry{

    private RealMarry realMarry;

    public MarryCompany(RealMarry realMarry){
        this.realMarry=realMarry;
    }

    @Override
    public void happyMarry() {
        ready();//加入代理对象的一些逻辑
        realMarry.happyMarry();//真实对象调用方法
        over();//加入代理对象一些逻辑 可以进行日志采集等
    }

    /**
     * 代理对象逻辑方法
     */
    private void ready() {
        System.out.println("准备工作中......");
    }

    /**
     * 代理对象逻辑方法
     */
    private void over() {
        System.out.println("结束工作红......");
    }
}
