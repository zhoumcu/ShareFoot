package com.ar.pay.sharefoot.base;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;

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
        onInitData();
        onEvent();
        onInitView();
        PgyCrashManager.register(this);
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
        PgyCrashManager.unregister();
    }
}
