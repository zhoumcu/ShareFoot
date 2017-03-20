package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.fragment.MyCollectFragment;
import com.ar.pay.sharefoot.utils.SharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/16 17:29
 * company: xxxx
 * email：1032324589@qq.com
 */
public class MyCollectActivity extends BaseActivity {

    @BindView(R.id.content_container)
    FrameLayout contentContainer;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_mycollect);
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {
        User user = (User) SharedPreferences.getInstance().readObject("user");
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MyCollectFragment foot =MyCollectFragment.getFragment(user.getUsername());
        fragmentTransaction.add(R.id.content_container, foot);
        fragmentTransaction.commit();
    }

    @Override
    public void onInitData() {

    }
}
