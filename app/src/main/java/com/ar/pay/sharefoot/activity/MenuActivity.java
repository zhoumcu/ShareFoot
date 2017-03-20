package com.ar.pay.sharefoot.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.pay.sharefoot.APP;
import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseSwipeActivity;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.SharedPreferences;
import com.ar.pay.sharefoot.utils.imageload.GlideImageLoader;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.squareup.picasso.Picasso;
import com.wingjay.blurimageviewlib.BlurImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * author：Administrator on 2017/3/15 09:13
 * company: xxxx
 * email：1032324589@qq.com
 */

public class MenuActivity extends BaseSwipeActivity {
    private static final int REQUEST_CAMERA_CODE = 1001;
    private static final int REQUEST_PREVIEW_CODE = 1002;
    @BindView(R.id.blurring_view)
    BlurImageView blurringView;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_articlecount)
    TextView tvArticlecount;
    @BindView(R.id.tv_sharecount)
    TextView tvSharecount;
    @BindView(R.id.btn_mineashare)
    RelativeLayout btnMineashare;
    @BindView(R.id.btn_minearticle)
    LinearLayout btnMinearticle;
    @BindView(R.id.btn_about)
    TextView btnAbout;
    @BindView(R.id.btn_update)
    TextView btnUpdate;
    @BindView(R.id.back)
    TextView back;
    private User user;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_menu);
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {
//        customizeBlurImageView();
//        int blurFactor = BlurImageView.DEFAULT_BLUR_FACTOR;
//        blurringView.setBlurFactor(blurFactor);
//        blurringView.setDefaultDrawable(getResources().getDrawable(R.drawable.test));
//        blurringView.setFullImageByUrl(user.getImageUrl(),user.getImageUrl());
    }

    @Override
    public void onInitData() {
        user = (User) SharedPreferences.getInstance().readObject("user");
        Picasso.with(APP.getInstances().getApplicationContext())
                .load(user.getImageUrl())
                .resize(100, 100)
                .centerCrop()
                .into(imgLogo);
        tvName.setText(user.getUsername());
    }

    private void customizeBlurImageView() {
        blurringView.setProgressBarBgColor(blurImageViewProgressBgColor[getResIndex()]);
        blurringView.setProgressBarColor(blurImageViewProgressClor[getResIndex()]);
    }

    @Override
    protected void onDestroy() {
        blurringView.cancelImageRequestForSafty();
        super.onDestroy();
    }

    String[] mediumSmUrl = {
            "http://upload-images.jianshu.io/upload_images/1825662-4c4e9bc7148749b7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
            "http://upload-images.jianshu.io/upload_images/1977600-c562e582d45a4dee.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
            "http://upload-images.jianshu.io/upload_images/1761761-704c9d7ed34d112b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62",
            "http://upload-images.jianshu.io/upload_images/2557965-b224163bc8a4a695.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/62"
    };

    String[] mediumNmUrl = {
            "http://upload-images.jianshu.io/upload_images/1825662-4c4e9bc7148749b7.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
            "http://upload-images.jianshu.io/upload_images/1977600-c562e582d45a4dee.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
            "http://upload-images.jianshu.io/upload_images/1761761-704c9d7ed34d112b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620",
            "http://upload-images.jianshu.io/upload_images/2557965-b224163bc8a4a695.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/620"
    };
    int currentIndex = 0;

    int getResIndex() {
        if (currentIndex > 3) {
            currentIndex = currentIndex - 4;
        }
        return currentIndex;
    }

    int[] blurImageViewProgressBgColor = {
            Color.BLACK,
            Color.BLACK,
            Color.parseColor("#E29C45"),
            Color.parseColor("#E29C45"),
    };

    int[] blurImageViewProgressClor = {
            Color.WHITE,
            Color.parseColor("#789262"),
            Color.parseColor("#7BCFA6"),
            Color.parseColor("#519A73"),
    };

    @OnClick({R.id.back,R.id.img_logo, R.id.btn_mineashare, R.id.btn_minearticle, R.id.btn_about, R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_logo:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(true); // 是否显示拍照， 默认false
                // intent.setImageConfig(config);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                break;
            case R.id.btn_mineashare:
                startActivityWithData(MyCollectActivity.class);
                break;
            case R.id.btn_minearticle:
                break;
            case R.id.btn_about:
                break;
            case R.id.btn_update:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
//                    if(captureManager.getCurrentPhotoPath() != null) {
//                        captureManager.galleryAddPic();
//                        // 照片地址
//                        String imagePaht = captureManager.getCurrentPhotoPath();
//                        // ...
//                    }
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }
    private void refreshAdpater(ArrayList<String> paths){
        // 处理返回照片地址
        User userInfo = BmobUser.getCurrentUser(User.class);
        String path = paths.get(0);
        SqlHelper.uploadImageFile(userInfo,path);
        new GlideImageLoader().loadGridItemView(imgLogo,paths.get(0),R.id.img_add,50,50);
    }
}
