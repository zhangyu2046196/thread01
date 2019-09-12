package com.youyuan.thread.runnable;

/**
 * @author zhangyu
 * @version 1.0
 * @description 多线程模拟龟兔赛跑
 * @date 2018/11/9 15:14
 */
public class Game implements Runnable {
    /**
     * 胜利者
     */
    private String winner;

    public void run() {
        for (int i=1;i<=100;i++){
            System.out.println(Thread.currentThread().getName()+"======>"+i);
            boolean isOver=runGame(i);
            if (isOver){
                break;
            }
        }
    }

    private boolean runGame(int i){
        if (winner!=null){
            return true;
        }else{
            if (i==100) {
                winner = Thread.currentThread().getName();
                System.out.println("胜利者:" + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Game game=new Game();
        new Thread(game,"乌龟").start();
        new Thread(game,"兔子").start();
    }
}
