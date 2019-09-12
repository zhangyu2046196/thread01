package com.youyuan.thread.state;

/**
 * @author zhangyu
 * @version 1.0
 * @description 内部观察线程的五大状态信息
 * @date 2018/11/13 17:14
 */
public class AllThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
           for (int i=1;i<=10;i++){
               if (i==2){
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println("lambda......"+i);
           }
        });

        Thread.State state= thread.getState();
        System.out.println(state);//NEW 新生状态

        thread.start();
        state=thread.getState();//RUNNABLE 就绪或运行状态
        System.out.println(state);

        for (int j=1;j<=10;j++){
            state=thread.getState();
            System.out.println(state);
            if (j==2){
                Thread.sleep(2000);
            }
            System.out.println("main......"+j);
        }

    }
}
