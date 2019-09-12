package com.youyuan.thread.single;

/**
 * @author zhangyu
 * @version 1.0
 * @description 双重检查方式创建单例
 * 创建单例条件
 * 1、构造方法私有
 * 2、私有静态属性指定对象变量(设置成静态在虚拟机中只有一份)
 * 3、公共静态方法获取属性
 * @date 2018/11/15 14:06
 */
public class DoubleCheckLock {
    //私有静态属性,使用volatile标注变量是数据同步，其它线程可见，变量值改变或创建后立即写入主内存
    private volatile static DoubleCheckLock instance;
    //私有构造方法
    private DoubleCheckLock(){

    }

    /**
     * 公共静态方法返回对象
     * @return 返回对象
     */
    public static DoubleCheckLock getInstance(){
        //双重检查，提高性能，如果对象已经创建成功无需再去等待获取锁
        if (instance!=null){
            return instance;
        }
        synchronized (DoubleCheckLock.class){
            if (instance==null){
                instance=new DoubleCheckLock();
                //对象new的时候过程如下
                //1、创建内存空间  2、初始化对象  3、把对象地址给引用   此时有可能初始化对象比较耗时，导致指令重排，对象还没有初始化完成
                //就把地址返回回去了，这时前端拿的地址是空，所以在变量上加入volatile，使数据同步
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(getInstance());
        }).start();

        System.out.println(getInstance());
    }
}
