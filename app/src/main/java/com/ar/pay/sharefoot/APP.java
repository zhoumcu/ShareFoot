package com.ar.pay.sharefoot;

import android.app.Application;

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
    }
}
