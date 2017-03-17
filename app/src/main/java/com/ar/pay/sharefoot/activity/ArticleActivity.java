package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.widget.TextView;

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
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_data)
    TextView tvData;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
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
        tvTitle.setText(food.getTitle());
        tvData.setText(food.getCreatedAt());
        tvAuthor.setText(food.getAuthor());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, food.getContent(), "text/html", "UTF-8", null);
    }

    @Override
    public void onInitData() {

    }

}
