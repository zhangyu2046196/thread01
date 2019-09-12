package com.youyuan.thread.lambda;

/**
 * @author zhangyu
 * @version 1.0
 * @description JDK1.8后的lambda表达式，用于减少匿名内部类定义过多，lambda是函数式编程，以下是lambda表达式用法的推导过程
 * @date 2018/11/12 20:26
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        ILike like=new Like();//外部类实现接口方式
        like.labdda();
        like=new Like1();//静态内部类实现接口
        like.labdda();
        //局部内部类实现接口
        class Like2 implements ILike{

            @Override
            public void labdda() {
                System.out.println("局部内部类实现接口");
            }
        }
        like=new Like2();//局部内部类实现接口
        like.labdda();

        like=new ILike() {//匿名内部类实现接口
            @Override
            public void labdda() {
                System.out.println("匿名内部类实现接口");
            }
        };
        like.labdda();

        like=()-> {//lambda表达式
            System.out.println("lambda表达式实现接口");
        };
        like.labdda();

    }

    /**
     * 静态内部类
     */
    static class Like1 implements ILike{

        @Override
        public void labdda() {
            System.out.println("静态内部类实现接口");
        }
    }
}

/**
 * 外部接口
 */
interface ILike{
    void labdda();
}

/**
 * 外部类实现接口
 */
class Like implements ILike{

    @Override
    public void labdda() {
        System.out.println("外部类实现接口");
    }
}
