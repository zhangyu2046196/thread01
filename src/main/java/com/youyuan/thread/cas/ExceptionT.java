package com.youyuan.thread.cas;

import java.util.concurrent.Executors;

/**
 * @author zhangyu
 * @version 1.0
 * @description
 * @date 2018/11/19 13:56
 */
public class ExceptionT {

    public static void main(String[] args) {
/*        try{
            int j=0;
            for (int i=1;i<=10;i++){
                System.out.println("Ñ­»·"+i);
                if (i==5){
                    int p=2/0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }*/

            int j=0;
            for (int i=1;i<=10;i++){
                try {
                    System.out.println("Ñ­»·"+i);
                    if (i==5){
                        int p=2/0;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }
}
