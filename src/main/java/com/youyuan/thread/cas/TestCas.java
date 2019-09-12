package com.youyuan.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangyu
 * @version 1.0
 * @description ���ķ���:���������ֹ���
 * ������:��ָ��ռ������synchronized��һ���̻߳�ȡ��֮�������߳�����
 * �ֹ���:��Ϊ���ᷢ����ͻ�����������ͻ��ʧ�ܣ�Ȼ���ظ�ִ��ֱ���ɹ�
 *
 * CSA:Compare and Swap �Ƚϲ��������������ݰ汾�ſ��Ʋ�������
 *
 *
 * ���³���ģ����
 * @date 2018/11/16 16:24
 */
public class TestCas {
    private static AtomicInteger count=new AtomicInteger(5);//Ĭ����5����� AtomicInteger�ڲ�ʵ����CAS���ײ��ǵ��õ�c++������cpuֱ�Ӳ���Ӳ��

    public static void main(String[] args) throws InterruptedException {
        for (int i=1;i<=6;i++){
            Thread.sleep(1000);
            new Thread(()->{
                if (count.get()<1){
                    System.out.println("��Ʒ��������");
                }else {
                    int yue=count.decrementAndGet();
                    System.out.println(Thread.currentThread().getName()+"��ȡ��Ʒ�ɹ�,��ʣ"+yue+"����Ʒ");
                }
            }).start();
        }
    }
}
