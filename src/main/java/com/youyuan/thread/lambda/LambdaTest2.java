package com.youyuan.thread.lambda;

/**
 * @author zhangyu
 * @version 1.0
 * @description JDK1.8的lambda表达式解决匿名内部类定义过多问题，内部就是函数调用，以下是用lambda表达式测试无参、有参、有返回值的接口
 * lambda表达式用法要求接口只能有一个抽象方法
 * @date 2018/11/12 21:00
 */
public class LambdaTest2 {

    public static void main(String[] args) {
       ILove1 love1=()->{
           System.out.println("lambda无参接口");
       };
       love1.happyLove();

       ILove2 love2=(a)->{
           System.out.println("lambda有参接口"+a);
       };
       love2.happyLove(100);

       ILove3 love3=(a,b)->{
           System.out.println("lambda两个参数接口"+a+" "+b);
       };
       love3.happyLove(100,200);

       ILove5 iLove5=(a,b)->{
         return a+b;
       };
       System.out.println("lambda两个参数有返回值接口"+iLove5.happyLove(300,900));
    }

    /**
     * 无参接口
     */
    interface ILove1{
        void happyLove();
    }

    /**
     * 一个参数接口
     */
    interface ILove2{
        void happyLove(int a);
    }

    /**
     * 两个参数接口
     */
    interface ILove3{
        void happyLove(int a,int b);
    }

    /**
     * 两个参数和返回值的接口
     */
    interface ILove5{
        int happyLove(int a,int b);
    }
}
