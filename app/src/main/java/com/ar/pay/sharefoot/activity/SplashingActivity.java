package com.ar.pay.sharefoot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar.pay.sharefoot.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/15 09:13
 * company: xxxx
 * email：1032324589@qq.com
 */

public class SplashingActivity extends AppCompatActivity {
    @BindView(R.id.splash_image)
    ImageView splashImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_splash);
        ButterKnife.bind(this);
        onInitView();
    }

    public void onInitView() {
        //图标的放大及渐变效果
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
                startActivityWithData(Login.class);
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_in_left);
                finish();
            }
        });
        splashImage.setAnimation(animation);

    }

    public void startActivityWithData(Class<?> cl) {
        Intent intent = new Intent(this, cl);
        startActivity(intent);
    }

}
