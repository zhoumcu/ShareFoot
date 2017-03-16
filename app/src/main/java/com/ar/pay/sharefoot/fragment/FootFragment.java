package com.ar.pay.sharefoot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.activity.ArticleActivity;
import com.ar.pay.sharefoot.adapter.FootAdapter;
import com.ar.pay.sharefoot.base.BaseListDataFragment;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.utils.ListConfig;
import com.ar.pay.sharefoot.utils.Utils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class FootFragment extends BaseListDataFragment<List<Food>> {
    public static final String MY_TAG = "FootFragment";
    @BindView(R.id.recycler)
    EasyRecyclerView recycler;
    private FootAdapter adapter;
    private List<Food> listFood = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_foot, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onEvent() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(),ArticleActivity.class);
                intent.putExtra("food",listFood.get(position));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onInitView() {
        //设置adapter
        adapter = new  FootAdapter(getContext());
        recycler.setAdapter(adapter);
    }

    @Override
    protected EasyRecyclerView getRecycler() {
        return recycler;
    }

    @Override
    public ListConfig getConfig() {
        return super.getConfig()
                .setLoadmoreAble(false)
                .setRefreshAble(true)
                .setNoMoreAble(true);
    }
    @Override
    public void onInitData() {
        sqlHelper.queryFood(this);
    }

    @Override
    protected void setData(List<Food> o) {
        listFood = o;
        adapter.addAll(o);
    }


    @Override
    public void onRefresh() {
        sqlHelper.queryFood(this);
    }
}
