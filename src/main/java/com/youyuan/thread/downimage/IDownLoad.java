package com.youyuan.thread.downimage;

import sun.awt.windows.WEmbeddedFrame;

import java.net.MalformedURLException;

/**
 * @author zhangyu
 * @version 1.0
 * @description 实现Runnable接口进行多线程编程
 * @date 2018/11/9 11:34
 */
public class IDownLoad implements Runnable {
    //图片地址
    private String url;
    //下载后保存的图片名
    private String name;

    public IDownLoad(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public void run() {
        WebDownLoad webDownLoad=new WebDownLoad();
        try {
            webDownLoad.downLoad(url,name);
            System.out.println("下载图片名:"+name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new IDownLoad("https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=4115885470,1945985792&fm=173&app=25&f=JPEG?w=640&h=405&s=3A9218CC0822A3571E2D0EAC0300A006","d.jpg")).start();
        new Thread(new IDownLoad("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=430874550,2608279295&fm=173&app=49&f=JPEG?w=640&h=335&s=7C06E21540E9112C421BA5CD0300E0AF","e.jpg")).start();
        new Thread(new IDownLoad("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2914280637,3116977030&fm=173&app=49&f=JPEG?w=499&h=400&s=98196BD89E232C9660081008030050D2","f.jpg")).start();
    }
}
