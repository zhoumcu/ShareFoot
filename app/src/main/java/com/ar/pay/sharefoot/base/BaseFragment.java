package com.ar.pay.sharefoot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.pgyersdk.crash.PgyCrashManager;

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
        PgyCrashManager.register(getContext());
    }

    protected abstract void onInitView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        PgyCrashManager.unregister();
    }
}
