package com.ar.pay.sharefoot;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.pgyersdk.crash.PgyCrashManager;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import cn.jpush.im.android.api.JMessageClient;
import cn.sharesdk.framework.ShareSDK;

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
        initBmob();
        PgyCrashManager.register(this);
//        MobSDK.init(this,"1c47e8310c6ca");
        ShareSDK.initSDK(this);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this,true);
//        BBSSDK.registerSDK();
//         初始化BmobSDK
//        Bmob.initialize(this, "d79087477318b1ba60f416d99c96800f");
//         使用推送服务时的初始化操作
//        BmobInstallation.getCurrentInstallation().save();
//         启动推送服务
//        BmobPush.startWork(this);
    }
    private void initBmob() {
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
                //设置appkey
                .setApplicationId("d79087477318b1ba60f416d99c96800f")
                //请求超时时间（单位为秒）：默认15s
                .setConnectTimeout(30)
                //文件分片上传时每片的大小（单位字节），默认512*1024
                .setUploadBlockSize(1024*1024)
                //文件的过期时间(单位为秒)：默认1800s
                .setFileExpiration(2500)
                .build();
        Bmob.initialize(config);
    }
    public  String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
            String version = packInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
