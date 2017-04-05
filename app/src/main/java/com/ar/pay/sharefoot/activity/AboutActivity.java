package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * author：Administrator on 2017/3/16 17:29
 * company: xxxx
 * email：1032324589@qq.com
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.webView)
    ProgressWebView webView;
    private String strUlr="http://mp.weixin.qq.com/s/RQU1m6h5YGDdSzHDqIs81g";
    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_about);
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(strUlr);
    }

    @Override
    public void onInitData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                // 处理返回逻辑
                finish();
                return true;
            case R.id.action_share:
                showShare();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //调用inflate()方法创建菜单
        getMenuInflater().inflate(R.menu.share_menu,menu);

        //如果返回false，创建的菜单无法显示
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle(food.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl(articlesUrl);
        // text是分享文本，所有平台都需要这个字段
//        oks.setText(food.getDescriber());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl(food.getImageUrl());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(strUlr);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
        // 设置自定义的外部回调
//        oks.setCallback(new OneKeyShareCallback());
        // 启动分享GUI
        oks.show(this);
    }
}
