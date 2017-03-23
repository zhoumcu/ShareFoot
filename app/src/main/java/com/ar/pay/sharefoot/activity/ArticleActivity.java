package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.bean.MyCollect;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.receiver.MyBmobInstallation;
import com.ar.pay.sharefoot.service.HandlerResponse;
import com.ar.pay.sharefoot.service.OnResult;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.Fglass;
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
    private User user;
    private Boolean isCollect = false;
    private MyCollect collect;

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
//        webView.getSettings().setUseWideViewPort(true);//让webview读取网页设置的viewport，pc版网页
//        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.loadDataWithBaseURL(null, food.getContent(), "text/html", "UTF-8", null);
    }

    @Override
    public void onInitData() {
        //update();
        user = (User) SharedPreferences.getInstance().readObject("user");
        checkIsCollect();
    }

    private void checkIsCollect() {
        SqlHelper.queryIsMyCollect(user.getUsername(), food, new HandlerResponse<List<MyCollect>>() {
            @Override
            public void onHandlerSucess(List<MyCollect> obj) {
                if(obj.size()==0){
                    isCollect = false;
                }else {
                    isCollect = true;
                    collect = obj.get(0);
                }
                invalidateOptionsMenu();
                getWindow().invalidatePanelMenu(Window.FEATURE_OPTIONS_PANEL);
            }

            @Override
            public void onHandlerError() {

            }
        });
    }

    @OnClick({R.id.btn_yuyue})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_yuyue:
//                BmobPushManager bmobPush = new BmobPushManager();
//                BmobQuery<MyBmobInstallation> query = MyBmobInstallation.getQuery();
//                query.addWhereEqualTo("username", "android");
//                bmobPush.setQuery(query);
//                bmobPush.pushMessage("消息内容");
                BmobPushManager bmobPush = new BmobPushManager();
                bmobPush.pushMessageAll("Hello Bmob.");
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
            case R.id.action_favorite:
                if(item.isCheckable()){
                    SqlHelper.createMyCollect(food, user.getUsername(), new OnResult<Boolean>() {
                        @Override
                        public void onSucess(Boolean obj) {
                            isCollect = obj;
                            invalidateOptionsMenu();
                        }

                        @Override
                        public void onErro() {

                        }
                    });
                }else{
                    SqlHelper.deleteMyCollect(collect.getObjectId(), new OnResult<Boolean>() {
                        @Override
                        public void onSucess(Boolean obj) {
                            isCollect = obj;
                            invalidateOptionsMenu();
                        }

                        @Override
                        public void onErro() {

                        }
                    });
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //调用inflate()方法创建菜单
        getMenuInflater().inflate(R.menu.favar_menu,menu);
        //如果返回false，创建的菜单无法显示
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("执行了onPrepareOptionsMenu");
        if (isCollect) {
            menu.findItem(R.id.action_favorite).setIcon(
                    R.mipmap.favorites_filling);
            menu.findItem(R.id.action_favorite).setCheckable(false);
        } else {
            menu.findItem(R.id.action_favorite).setIcon(
                    R.mipmap.favorite);
            menu.findItem(R.id.action_favorite).setCheckable(true);
        }
        // getSupportMenuInflater().inflate(R.menu.book_detail, menu);
        return super.onPrepareOptionsMenu(menu);
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
        oks.setTitle(food.getTitle());
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl(articlesUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(food.getDescriber());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(food.getImageUrl());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(food.getContent());
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
