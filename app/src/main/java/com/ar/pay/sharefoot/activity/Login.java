package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.pay.sharefoot.MainActivity;
import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.utils.SharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * author：Administrator on 2017/3/1 09:07
 * company: xxxx
 * email：1032324589@qq.com
 */

public class Login extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.imgCancel)
    ImageView imgCancel;
    @BindView(R.id.tv_find)
    TextView tvFind;
    @BindView(R.id.layoutPhone)
    RelativeLayout layoutPhone;
    @BindView(R.id.btn_pwd)
    TextView btnPwd;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.btnSure)
    Button btnSure;
    @BindView(R.id.btnClose)
    Button btnClose;
    @BindView(R.id.tv_load)
    TextView tvLoad;
    @BindView(R.id.btnRegister)
    LinearLayout btnRegister;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_login);
        ButterKnife.bind(this);
    }

    @Override
    public void onEvent() {

    }

    @Override
    public void onInitView() {

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.btnSure, R.id.btnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSure:
                close();
                String phone1 = phone.getText().toString();
                String mPassword = tvPwd.getText().toString();
//        if (phone.length() != 11) {
//            getView().phone.setError("手机号格式错误");
//            return;
//        }
                if (phone1.length() <= 0) {
                    phone.setError("手机号格式错误");
                    return;
                }
                if (mPassword.length() < 4 || mPassword.length() > 12) {
                    tvPwd.setError("密码应为5-12位");
                    return;
                }
                User user = new User();
                user.setPassword(mPassword);
                user.setMobilePhoneNumber(phone1);
                user.setUsername(phone1);
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User bmobUser, BmobException e) {
                        if(e==null){
                            toast("登录成功:");
                            SharedPreferences.getInstance().saveObject("user",bmobUser);
                            startActivityWithData(MainActivity.class);
                            finish();
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                        }else{
                            toast(e.getMessage());
                        }
                    }
                });
                break;
            case R.id.btnRegister:
                startActivityWithData(RegisterActivity.class);
                break;
        }
    }
}
