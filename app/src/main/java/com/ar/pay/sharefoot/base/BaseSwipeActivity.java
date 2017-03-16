package com.ar.pay.sharefoot.base;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * author：Administrator on 2017/3/13 09:09
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class BaseSwipeActivity extends SwipeBackActivity{
    public abstract void onUICreate(Bundle savedInstanceState);
    public abstract void onEvent();
    public abstract void onInitView();
    public abstract void onInitData();
    protected int activityCloseEnterAnimation;
    protected int activityCloseExitAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onUICreate(savedInstanceState);
        initAni();
        initBmob();
        onInitData();
        onEvent();
        onInitView();
    }
    private void initAni(){
        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[] {android.R.attr.windowAnimationStyle});
        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
        activityStyle.recycle();
        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[] {android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
        activityStyle.recycle();
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
    public void toast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }
    public void startActivityWithData(Class<?> cl){
        Intent intent = new Intent(this,cl);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
