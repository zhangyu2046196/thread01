package com.youyuan.thread.synsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @version 1.0
 * @description 通过影院购票选座的方式来加深对同步代码块的理解
 * @date 2018/11/14 10:32
 */
public class HappyCinema {

    public static void main(String[] args) {
        List<Integer> seats=new ArrayList<Integer>();
        seats.add(1);
        seats.add(2);
        seats.add(3);
        seats.add(6);
        seats.add(7);
        seats.add(8);
        seats.add(9);
        Cinema cinema=new Cinema("万达电影院",seats);
        List<Integer> count1=new ArrayList<Integer>();
        count1.add(1);
        count1.add(2);
        count1.add(7);
        List<Integer> count2=new ArrayList<Integer>();
        count2.add(5);
        count2.add(8);
        count2.add(10);

        new Thread(new Cousumer(cinema,count1),"成龙").start();
        new Thread(new Cousumer(cinema,count2),"李连杰").start();
    }

}

/**
 * 消费者(顾客)
 */
class Cousumer implements Runnable{
    //影院
    private Cinema cinema;
    //购买座位集合
    private List<Integer> count;

    public Cousumer(Cinema cinema, List<Integer> count) {
        this.cinema = cinema;
        this.count = count;
    }

    @Override
    public void run() {
        boolean isBuy=buy(cinema,count);
        if (isBuy){
            System.out.println(Thread.currentThread().getName()+"购票成功,影院"+cinema.getName()+"剩余座位"+cinema.getSeats());
        }else {
            System.out.println(Thread.currentThread().getName()+"购票失败,影院"+cinema.getName()+"剩余座位"+cinema.getSeats());
        }
    }

    /**
     * 买票
     * @param cinema 影院名称
     * @param count 购买座位信息
     * @return 返回购买结果
     */
    public boolean buy(Cinema cinema,List<Integer> count){
        System.out.println("欢迎管理"+cinema.getName()+" 剩余座位为"+cinema.getSeats()+Thread.currentThread().getName()+"购买座位"+count);
        if (cinema.getSeats().size()<count.size()){
            System.out.println("影院"+cinema.getName()+"票数不足..");
            return false;
        }
        synchronized (cinema){
            List<Integer> copy=new ArrayList<Integer>();//把影院座位拷贝一份
            copy.addAll(cinema.getSeats());
            copy.removeAll(count);//减去购买的票数
            if (cinema.getSeats().size()-count.size()==copy.size()){//影院票数减去要购买的票数等于copy出来的剩余票数代表购买成功
                cinema.setSeats(copy);
                return true;
            }
            return false;
        }
    }
}

/**
 * 影院
 */
class Cinema{
    //电影名
    private String name;
    //座位
    private List<Integer> seats;

    public Cinema(String name, List<Integer> seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}
