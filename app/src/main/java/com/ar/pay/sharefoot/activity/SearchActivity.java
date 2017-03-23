package com.ar.pay.sharefoot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.adapter.FootAdapter;
import com.ar.pay.sharefoot.base.BaseListDataActivity;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.widget.IconCenterEditText;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/15 09:13
 * company: xxxx
 * email：1032324589@qq.com
 */

public class SearchActivity extends BaseListDataActivity<List<Food>> {

    @BindView(R.id.recycler)
    EasyRecyclerView recycler;
    @BindView(R.id.search)
    IconCenterEditText search;
    private FootAdapter adapter;
    private List<Food> listFood = new ArrayList<>();

    @Override
    protected void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_search);
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent() {
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchActivity.this, ArticleActivity.class);
                intent.putExtra("food", listFood.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onInitView() {
        search.setOnSearchClickListener(new IconCenterEditText.OnSearchClickListener() {
            @Override
            public void onSearchClick(View view) {
                sqlHelper.queryFood(search.getText().toString());
            }
        });
        //设置adapter
        adapter = new FootAdapter(this);
        recycler.setAdapter(adapter);
    }

    @Override
    protected EasyRecyclerView getRecycler() {
        return recycler;
    }


    @Override
    public void onInitData() {

    }

    @Override
    protected void setData(List<Food> o) {
        listFood = o;
        adapter.clear();
        adapter.addAll(listFood);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

    }
}
