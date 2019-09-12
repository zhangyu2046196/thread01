package com.youyuan.thread.vol;

/**
 * @author zhangyu
 * @version 1.0
 * @description volatile修饰变量是可见的保证了数据的同步，例如线程A修改变量X的值，此时线程B读取变量X的值，volatile保证了X值的同步
 * 线程A修改完X的值后会立即同步到主内存供线程B读取
 * @date 2018/11/15 11:52
 */
public class TestVolatile {
    //private static int num;//没有定义volatile时子线程会一直循环,因为子线程没有时间去主内存读取num的值
    private volatile static int num;//变量定义为volatile，保证了变量修改的同步，修改后立即更新回主内存

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num==0){//子线程写个死循环，只有num值改变后才能结束

            }
        }).start();

        Thread.sleep(1000);
        num=1;
    }
}
