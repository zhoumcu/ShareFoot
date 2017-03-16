package com.ar.pay.sharefoot.activity;

import android.os.Bundle;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.widget.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/16 17:29
 * company: xxxx
 * email：1032324589@qq.com
 */
public class ArticleActivity extends BaseActivity {
    @BindView(R.id.webView)
    ProgressWebView webView;
    private Food food;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_articledetail);
        ButterKnife.bind(this);
        food = (Food) getIntent().getExtras().getSerializable("food");
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(food.getContent(),"text/html","utf8");
    }

    @Override
    public void onInitData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
