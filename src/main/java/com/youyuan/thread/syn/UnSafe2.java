package com.youyuan.thread.syn;

/**
 * @author zhangyu
 * @version 1.0
 * @description 线程不安全场景之银行取钱
 * @date 2018/11/13 20:14
 */
public class UnSafe2 {

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
        qu();
    }

    public void qu(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (acount){
            if (acount.getCount()-money>0){
                quchu+=money;
                acount.setCount(acount.getCount()-money);
                System.out.println(Thread.currentThread().getName()+"取出钱"+quchu+" 账户剩余钱"+acount.getCount());
            }
        }
    }
}
