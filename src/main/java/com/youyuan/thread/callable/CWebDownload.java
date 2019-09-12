package com.youyuan.thread.callable;


import com.youyuan.thread.downimage.TDownLoad;

import java.util.concurrent.*;

/**
 * @author zhangyu
 * @version 1.0
 * @description 通过实现callable接口重写call方法来多线程编程
 * @date 2018/11/9 17:49
 */
public class CWebDownload implements Callable<Boolean> {
    //图片地址
    private String url;
    //图片名称
    private String name;

    public CWebDownload(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() throws Exception {
        WebDownLoad webDownLoad=new WebDownLoad();
        webDownLoad.downLoad(url,name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CWebDownload t1=new CWebDownload("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4115885470,1945985792&fm=173&app=25&f=JPEG?w=640&h=405&s=3A9218CC0822A3571E2D0EAC0300A006","a.jpg");
        CWebDownload t2=new CWebDownload("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=430874550,2608279295&fm=173&app=49&f=JPEG?w=640&h=335&s=7C06E21540E9112C421BA5CD0300E0AF","b.jpg");
        CWebDownload t3=new CWebDownload("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2914280637,3116977030&fm=173&app=49&f=JPEG?w=499&h=400&s=98196BD89E232C9660081008030050D2","c.jpg");
        //创建服务和指定线程个数
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> future1=executorService.submit(t1);
        Future<Boolean> future2=executorService.submit(t2);
        Future<Boolean> future3=executorService.submit(t3);
        //获取结果
        Boolean b1= future1.get();
        Boolean b2= future2.get();
        Boolean b3= future3.get();
        System.out.println("b1:"+b1+" b2:"+b2+" b3:"+b3);
        //关闭执行
        executorService.shutdownNow();
    }
}
