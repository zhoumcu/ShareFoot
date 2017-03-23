package com.ar.pay.sharefoot.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.base.BaseActivity;
import com.ar.pay.sharefoot.bean.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
 * author：Administrator on 2016/12/12 14:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.ed_account)
    EditText edAccount;
    @BindView(R.id.imgCancel)
    ImageView imgCancel;
    @BindView(R.id.layoutPhone)
    RelativeLayout layoutPhone;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_repwd)
    EditText edRepwd;
    @BindView(R.id.rl_1)
    RelativeLayout rl1;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.btn_sendcode)
    TextView btnSendcode;
    @BindView(R.id.ed_code)
    EditText edCode;
    @BindView(R.id.rl_2)
    RelativeLayout rl2;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private View rootView;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.aty_register);
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

    @OnClick(R.id.btn_register)
    public void onClick() {
        close();
        final String edPhone1 = edPhone.getText().toString();
        if (edAccount.length() <3 || edAccount.length() > 12) {
            edAccount.setError("帐号长度不对，请输入3-12个字符");
            return;
        }
        if(!edPwd.getText().toString().equals(edRepwd.getText().toString())) {
            edRepwd.setError("两次密码输入不对应");
            return;
        }
        if (edPwd.length() < 5 || edPwd.length() > 12) {
            edPwd.setError("密码应为5-12位");
            return;
        }
        User user = new User();
        user.setPassword(edPwd.getText().toString());
        user.setMobilePhoneNumber(edPhone1);
        user.setUsername(edPhone1);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    JMessageClient.register(edPhone1, edPwd.getText().toString(), new BasicCallback() {
                        @Override
                        public void gotResult(int i, String s) {
                            toast("注册成功:" +s.toString());
                        }
                    });
                }else{
                    toast(e.getMessage());
                }
            }
        });
    }
}
