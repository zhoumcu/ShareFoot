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

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class BaseListDataFragment<M> extends Fragment implements OnResult<M>,SwipeRefreshLayout.OnRefreshListener{
    public abstract void onEvent();
    public abstract void onInitData();
    public SqlHelper sqlHelper = new SqlHelper(this);
    private ListConfig mListConfig;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitData();
        onConfig();
        onInitView();
        onEvent();
    }
    private void onConfig() {
        mListConfig = getConfig();
        EasyRecyclerView recycler = getRecycler();
        if(recycler!=null){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            //设置布局管理器
            recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            //设置Item增加、移除动画
            recycler.setItemAnimator(new DefaultItemAnimator());
            //添加分割线
            SpaceDecoration itemDecoration = new SpaceDecoration((int) Utils.convertDpToPixel(4,getContext()));
            itemDecoration.setPaddingEdgeSide(true);
            itemDecoration.setPaddingStart(true);
            itemDecoration.setPaddingHeaderFooter(false);
            recycler.addItemDecoration(itemDecoration);
            if(mListConfig.mRefreshAble){
                recycler.setRefreshListener(this);
            }
        }
    }

    @Override
    public void onSucess(M o) {
        setData(o);
    }
    @Override
    public void onErro() {

    }
    public ListConfig getConfig(){
        return ListConfig.Default.clone();
    }

    public void toast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
    protected abstract void setData(M o);
    protected abstract void onInitView();
    protected abstract EasyRecyclerView getRecycler();

}
