package com.ar.pay.sharefoot.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.ar.pay.sharefoot.service.OnResult;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.ListConfig;
import com.ar.pay.sharefoot.utils.Utils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class BaseDataFragment<M> extends Fragment implements OnResult<M>, SwipeRefreshLayout.OnRefreshListener {

    public abstract void onEvent();
    public abstract void onInitData();
    public SqlHelper sqlHelper = new SqlHelper(this);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitData();

        onInitView();
        onEvent();
        PgyCrashManager.register(getContext());
    }


    @Override
    public void onSucess(M o) {
        setData(o);
    }
    @Override
    public void onErro() {

    }


    public void toast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
    protected abstract void setData(M o);
    protected abstract void onInitView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        PgyCrashManager.unregister();
    }
}

