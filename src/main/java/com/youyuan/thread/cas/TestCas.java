package com.youyuan.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangyu
 * @version 1.0
 * @description 锁的分类:悲观锁和乐观锁
 * 悲观锁:是指独占锁例如synchronized，一个线程获取锁之后其它线程阻塞
 * 乐观锁:认为不会发生冲突，如果发生冲突就失败，然后重复执行直到成功
 *
 * CSA:Compare and Swap 比较并交换，类似数据版本号控制并发问题
 *
 *
 * 以下场景模拟库存
 * @date 2018/11/16 16:24
 */
public class TestCas {
    private static AtomicInteger count=new AtomicInteger(5);//默认有5件库存 AtomicInteger内部实现了CAS，底层是调用的c++程序，由cpu直接操作硬件

    public static void main(String[] args) throws InterruptedException {
        for (int i=1;i<=6;i++){
            Thread.sleep(1000);
            new Thread(()->{
                if (count.get()<1){
                    System.out.println("商品已抢购完");
                }else {
                    int yue=count.decrementAndGet();
                    System.out.println(Thread.currentThread().getName()+"获取商品成功,还剩"+yue+"件商品");
                }
            }).start();
        }
    }
}
