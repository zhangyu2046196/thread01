package com.youyuan.thread.threadlocal;

/**
 * @author zhangyu
 * @version 1.0
 * @description ThreadLocal�̱߳���ֵ����
 * @date 2018/11/15 22:39
 */
public class TestThreadLocal2 {
    //ͨ��������ķ�ʽ�����̱߳���ֵ
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
