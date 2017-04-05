package com.ar.pay.sharefoot.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ar.pay.sharefoot.APP;
import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseSwipeActivity;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.sql.SqlHelper;
import com.ar.pay.sharefoot.utils.Fglass;
import com.ar.pay.sharefoot.utils.SharedPreferences;
import com.ar.pay.sharefoot.utils.imageload.GlideImageLoader;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.pgyersdk.feedback.PgyFeedback;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * author：Administrator on 2017/3/15 09:13
 * company: xxxx
 * email：1032324589@qq.com
 */

public class MenuActivity extends BaseSwipeActivity {
    private static final int REQUEST_CAMERA_CODE = 1001;
    private static final int REQUEST_PREVIEW_CODE = 1002;

    @BindView(R.id.img_logo)
    CircleImageView imgLogo;
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
    @BindView(R.id.btn_feedbak)
    TextView btnFeedbak;
    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.bg_glass)
    LinearLayout bgGlass;
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
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Fglass.blur(imgBg, bgGlass, 2, 10);
//            }
//        }, 10);
    }

    @Override
    public void onInitData() {
        btnUpdate.setText("版本更新   V" + APP.getInstances().getVersionName());
        user = (User) SharedPreferences.getInstance().readObject("user");
        Picasso.with(APP.getInstances().getApplicationContext())
                .load(user.getImageUrl())
                .resize(96, 96)
                .centerCrop()
                .into(imgLogo);
        tvName.setText(user.getUsername());
    }

    private void update() {
        PgyUpdateManager.register(this, "",
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {
                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(MenuActivity.this)
                                .setTitle("更新")
                                .setMessage(appBean.getReleaseNote())
                                .setNegativeButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startDownloadTask(MenuActivity.this, appBean.getDownloadURL());
                                            }
                                        }).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        Toast.makeText(MenuActivity.this, "没有新版本！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_feedbak, R.id.btn_store, R.id.back, R.id.img_logo, R.id.btn_mineashare, R.id.btn_minearticle, R.id.btn_about, R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_logo:
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(true); // 是否显示拍照， 默认false
                // intent.setImageConfig(config);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                break;
            case R.id.btn_store:
                startActivityWithData(MyCollectActivity.class);
                break;
            case R.id.btn_minearticle:
                break;
            case R.id.btn_about:
                startActivityWithData(AboutActivity.class);
                break;
            case R.id.btn_update:
                update();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.btn_feedbak:
                // 以对话框的形式弹出
                PgyFeedback.getInstance().showDialog(MenuActivity.this);
                // 以Activity的形式打开，这种情况下必须在AndroidManifest.xml配置FeedbackActivity
                // 打开沉浸式,默认为false
                // FeedbackActivity.setBarImmersive(true);
                PgyFeedback.getInstance().showActivity(MenuActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
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

    private void refreshAdpater(ArrayList<String> paths) {
        // 处理返回照片地址
        User userInfo = BmobUser.getCurrentUser(User.class);
        String path = paths.get(0);
        SqlHelper.uploadImageFile(userInfo, path);
        new GlideImageLoader().loadGridItemView(imgLogo, paths.get(0), R.id.img_add, 50, 50);
    }

}
