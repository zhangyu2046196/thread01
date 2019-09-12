package com.youyuan.thread.threadlocal;

/**
 * @author zhangyu
 * @version 1.0
 * @description ThreadLocal线程变量值传递
 * @date 2018/11/15 22:39
 */
public class TestThreadLocal2 {
    //通过匿名类的方式设置线程变量值
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 200;
        }
    };

    public static void main(String[] args) {
        new TestThreadLocal3().buy(threadLocal);
    }

}
