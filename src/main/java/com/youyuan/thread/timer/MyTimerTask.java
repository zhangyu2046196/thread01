package com.youyuan.thread.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangyu
 * @version 1.0
 * @description 定时调度
 * Timer类是定时调度任务，可以设置任务执行的时间
 * TimerTask是实现了Runnable接口的抽象类，调度任务执行的内容需要继承TimerTask抽象类，在run方法中写
 * @date 2018/11/14 20:52
 */
public class MyTimerTask {
    public static void main(String[] args) {
        //调度器
        Timer timer=new Timer();
        //指定要执行的调度任务和执行时间
        //timer.schedule(new TestTask(),1000);//一秒后执行
        //timer.schedule(new TestTask(),1000,2000);//一秒后执行，每隔两秒还执行
        timer.schedule(new TestTask(),new Date());//指定执行的时间
    }
}

/**
 * 调度任务执行内容放在run方法中
 */
class TestTask extends TimerTask{

    @Override
    public void run() {
        System.out.println("我们休息一会.....");
    }
}
