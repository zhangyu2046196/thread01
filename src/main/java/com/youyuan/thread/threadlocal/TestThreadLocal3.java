package com.youyuan.thread.threadlocal;

/**
 * @author zhangyu
 * @version 1.0
 * @description ThreadLocal�̱߳���ֵ����
 * @date 2018/11/15 22:41
 */
public class TestThreadLocal3 {

    public void buy(ThreadLocal<Integer> threadLocal){
        System.out.println(Thread.currentThread().getName()+"�̱߳���ֵ:"+threadLocal.get());
    }
}
