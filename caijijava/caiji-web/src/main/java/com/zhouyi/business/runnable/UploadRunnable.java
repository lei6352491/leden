package com.zhouyi.business.runnable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: first
 * @Date: 上午6:19 2019/11/6
 * @Description: 数据上报线程
**/
public class UploadRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("data uplaod task:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
