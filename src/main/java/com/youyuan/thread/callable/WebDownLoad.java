package com.youyuan.thread.callable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhangyu
 * @version 1.0
 * @description 图片下载类  此处使用工具类FileUtils
 * @date 2018/11/9 10:47
 */
public class WebDownLoad {
    /**
     * 图片下载方法
     * @param url 图片地址
     * @param name 保存图片后的名字
     */
    public void downLoad(String url,String name) throws MalformedURLException {
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(name)){
            try {
                FileUtils.copyURLToFile(new URL(url),new File(name));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("图片下载异常");
            }
        }
    }
}
