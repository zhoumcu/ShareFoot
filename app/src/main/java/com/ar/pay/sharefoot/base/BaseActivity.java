package com.ar.pay.sharefoot.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.foamtrace.photopicker.ImageConfig;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * author：Administrator on 2017/3/13 09:09
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity{
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
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

        ImageConfig config = new ImageConfig();
        config.minHeight = 400;
        config.minWidth = 400;
        config.mimeType = new String[]{"image/jpeg", "image/png"}; // 图片类型 image/gif ...
        config.minSize = 1 * 1024 * 1024; // 1Mb 图片大小

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void close(){
        /**隐藏软键盘**/
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
