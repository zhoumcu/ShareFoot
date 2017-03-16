package com.ar.pay.sharefoot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ar.pay.sharefoot.MainActivity;
import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;

/**
 * author：Administrator on 2017/3/15 09:13
 * company: xxxx
 * email：1032324589@qq.com
 */

public class SplashingActivity extends BaseActivity{
    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_splash);
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {
//图标的放大及渐变效果
        ImageView splash=(ImageView) findViewById(R.id.splash_image);
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 1.0f);
        alphaAnim.setDuration(2500);
//		view.startAnimation(alphaAnim);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_scale_translate);
        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                arg0.setFillAfter(true);
                startActivityWithData(MainActivity.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_in_left);
                finish();
            }
        });
        splash.setAnimation(animation);
    }

    @Override
    public void onInitData() {

    }
}
