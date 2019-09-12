package com.youyuan.thread.synsafe;

/**
 * @author zhangyu
 * @version 1.0
 * @description synchronized同步代码块实现线程安全
 * @date 2018/11/13 20:14
 */
public class SynSafe2 {

    public static void main(String[] args) {
        Acount acount=new Acount("彩礼",100);
        QuQian you=new QuQian(acount,90);
        QuQian wife=new QuQian(acount,80);
        new Thread(you).start();
        new Thread(wife).start();
    }
}

/**
 * 账户
 */
class Acount{
    //账户名称
    private String name;
    //账户金额
    private int count;

    public Acount(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

/**
 * 模拟取钱线程
 */
class QuQian implements Runnable{
    //取出的钱数
    private int quchu;
    //账户
    private Acount acount;
    //要取的钱数
    private int money;

    public QuQian(Acount acount, int money) {
        this.acount = acount;
        this.money = money;
    }

    @Override
    public void run() {
        //此判断是提高性能，先判断金额是否小于等于0，如果是也就不用再等待其它线程释放锁
        if (acount.getCount()<=0){
            return;
        }
        qu();
    }

    public void qu(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //因为这里操作的是acount所以加锁的对象必须是acount
        synchronized (acount){
            if (acount.getCount()-money>0){
                quchu+=money;
                acount.setCount(acount.getCount()-money);
                System.out.println(Thread.currentThread().getName()+"取出钱"+quchu+" 账户剩余钱"+acount.getCount());
            }
        }
    }
}
