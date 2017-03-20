package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.receiver.MyBmobInstallation;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.SharedPreferences;
import com.ar.pay.sharefoot.widget.ProgressWebView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

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
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadDataWithBaseURL(null, food.getContent(), "text/html", "UTF-8", null);
    }

    @Override
    public void onInitData() {
        update();
    }

    @OnClick({R.id.btn_yuyue, R.id.btn_store})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yuyue:
                BmobPushManager bmobPush = new BmobPushManager();
                BmobQuery<MyBmobInstallation> query = MyBmobInstallation.getQuery();
                query.addWhereEqualTo("username", "android");
                bmobPush.setQuery(query);
                bmobPush.pushMessage("消息内容");
                break;
            case R.id.btn_store:
                User user = (User) SharedPreferences.getInstance().readObject("user");
                SqlHelper.createMyCollect(food,user.getUsername());
                break;
        }
    }
    private void update(){
        BmobQuery<MyBmobInstallation> query = new BmobQuery<MyBmobInstallation>();
        query.addWhereEqualTo("installationId", BmobInstallation.getInstallationId(this));
        query.findObjects(new FindListener<MyBmobInstallation>() {

            @Override
            public void done(List<MyBmobInstallation> list, BmobException e) {
                if (list.size() > 0) {
                    MyBmobInstallation mbi = list.get(0);
                    mbi.setUid("用户id");
                    mbi.setUsername("test01");
                    mbi.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Log.i("bmob", "设备信息更新成功");
                            } else {
                                Log.i("bmob", "设备信息更新失败:" + e.getMessage());
                            }
                        }

                    });
                } else {

                }
            }
        });
    }
}
