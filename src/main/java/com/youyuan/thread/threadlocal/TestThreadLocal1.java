package com.youyuan.thread.threadlocal;

/**
 * @author zhangyu
 * @version 1.0
 * @description ThreadLocal�̱߳������洢�����̱߳���ı�����ֻ�ܱ��߳̿ɼ����޸��̱߳�����ֵ�����̱߳���ֵ����Ӱ��
 * ThreadLocal�����·���
 * 1��get            ��ȡ�̱߳���ֵ
 * 2��set            �����̱߳���ֵ
 * 3��initiaValue   ��ʼ���̱߳���ֵ
 * @date 2018/11/15 22:30
 */
public class TestThreadLocal1 {
    //ͨ���������ʼ���̱߳���ֵ
    private static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 100;
        }
    };

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"�̱߳���ֵ:"+threadLocal.get());
        //���������̱߳���ֵ
        threadLocal.set(900);
        System.out.println(Thread.currentThread().getName()+"�̱߳���ֵ"+threadLocal.get());

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"���߳�Ĭ���̱߳���ֵ"+threadLocal.get());
        }).start();

        new Thread(()->{
            //���߳������̱߳���ֵ������Ӱ��main���̺߳��������̵߳�ֵ
            threadLocal.set(190);
            System.out.println(Thread.currentThread().getName()+"���߳����������ֵ:"+threadLocal.get());
        }).start();
    }
}
