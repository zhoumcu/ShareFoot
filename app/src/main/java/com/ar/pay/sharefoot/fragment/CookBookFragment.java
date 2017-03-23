package com.ar.pay.sharefoot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.activity.ArticleActivity;
import com.ar.pay.sharefoot.adapter.FootAdapter;
import com.ar.pay.sharefoot.base.BaseFragment;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.service.OnResult;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.Utils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * author：Administrator on 2017/3/13 09:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class CookBookFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String MY_TAG = "CookBookFragment";
    @BindView(R.id.recycler)
    EasyRecyclerView recycler;
    private FootAdapter adapter;
    private int categoryId = 0;
    private List<Food> listFood = new ArrayList<>();

    public static CookBookFragment getFragment(int categoryId) {
        CookBookFragment cookBookFragment = new CookBookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId",categoryId);
        cookBookFragment.setArguments(bundle);
        return cookBookFragment;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        recycler.setLayoutManager(layoutManager);
        //设置adapter
        adapter = new  FootAdapter(getContext());
        recycler.setAdapter(adapter);
        //设置Item增加、移除动画
        recycler.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        SpaceDecoration itemDecoration = new SpaceDecoration((int) Utils.convertDpToPixel(8,getContext()));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recycler.addItemDecoration(itemDecoration);

    }
    @Override
    public void onInitData() {
        categoryId = getArguments().getInt("categoryId");
        Log.e("查询成功",categoryId+"");
        SqlHelper.queryFood(categoryId,new OnResult() {
            @Override
            public void onSucess(Object o) {
                Log.i("bmob","查询成功：");
                listFood = (List<Food>) o;
                adapter.addAll(listFood);
            }

            @Override
            public void onErro() {

            }
        });
//        recycler.setRefreshListener(this);
    }

    @Override
    public void onRefresh() {

    }
}
