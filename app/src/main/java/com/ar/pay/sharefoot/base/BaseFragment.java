package com.ar.pay.sharefoot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class BaseFragment extends Fragment{
    public abstract void onEvent();
    public abstract void onInitData();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitData();
        onInitView();
        onEvent();
    }

    protected abstract void onInitView();
}
