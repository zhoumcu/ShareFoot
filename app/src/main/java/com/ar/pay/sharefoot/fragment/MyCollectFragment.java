package com.ar.pay.sharefoot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.activity.ArticleActivity;
import com.ar.pay.sharefoot.adapter.FootAdapter;
import com.ar.pay.sharefoot.base.BaseListDataFragment;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.bean.MyCollect;
import com.ar.pay.sharefoot.utils.ListConfig;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class MyCollectFragment extends BaseListDataFragment<List<Food>> {
    public static final String MY_TAG = "FootFragment";
    @BindView(R.id.recycler)
    EasyRecyclerView recycler;
    private FootAdapter adapter;
    private List<Food> listFood = new ArrayList<>();
    private String username;

    public static MyCollectFragment getFragment(String username) {
        MyCollectFragment footFragment = new MyCollectFragment();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        footFragment.setArguments(bundle);
        return footFragment;
    }

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
        username = getArguments().getString("username");
        sqlHelper.queryMyCollect(username,this);
    }

    @Override
    protected void setData(List<MyCollect> o) {
        for (MyCollect myCollect: o){
            listFood.add(myCollect.getFood())
        }
        listFood = o;
        adapter.clear();
        adapter.addAll(o);
    }


    @Override
    public void onRefresh() {
        sqlHelper.queryMyCollect(username,this);
    }
}
