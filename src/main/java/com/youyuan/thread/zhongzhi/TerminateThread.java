package com.youyuan.thread.zhongzhi;

/**
 * @author zhangyu
 * @version 1.0
 * @description 通过变量的值来控制线程运行是否终止(JDK中有stop和destory两个方法,但是这两个方法已经过时了,所以用变量来控制线程是否执行)
 * @date 2018/11/13 14:57
 */
public class TerminateThread implements Runnable{
    /**
     * 变量来控制线程是否结束,true线程运行 false线程结束
     */
    private boolean flag=true;
    /**
     * 名称
     */
    private String name;

    public TerminateThread(String name){
        this.name=name;
    }

    @Override
    public void run() {
        int i=1;
        while (flag){
            System.out.println(name+"------>"+i++);
        }
    }

    /**
     * 改变变量的值用来控制线程是否结束
     */
    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        TerminateThread terminateThread=new TerminateThread("京东");//线程新生状态
        new Thread(terminateThread).start();//线程就绪状态
        for (int i=1;i<=100;i++){
            if (i==91){
                terminateThread.stop();
                System.out.println("线程变量修改终止线程");
            }
            System.out.println("外边变量循环-->"+i);
        }
    }
}
