package com.ar.pay.sharefoot;

import android.app.Application;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

/**
 * author：Administrator on 2017/3/13 15:22
 * company: xxxx
 * email：1032324589@qq.com
 */
public class APP extends Application{
    private static APP instances;
    public static APP getInstances(){
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        // 初始化BmobSDK
        Bmob.initialize(this, "d79087477318b1ba60f416d99c96800f");
        // 使用推送服务时的初始化操作
        BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);
    }
}
