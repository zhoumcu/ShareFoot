package com.ar.pay.sharefoot.interfaces.model;


import android.text.TextUtils;
import android.widget.EditText;

import com.ar.pay.sharefoot.MainActivity;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.utils.SharedPreferences;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

/**
* Created by Administrator on 2017/03/24
*/

public class LoginModelImpl implements LoginModel{

    @Override
    public String verfiyUserName(EditText text) {
        if(text.getText().toString().isEmpty()){
            return null;
        }

        return text.getText().toString();
    }

    @Override
    public String verfiyPassWord(EditText text) {
        if(text.getText().toString().isEmpty()){
            return null;
        }
        return text.getText().toString();
    }

    @Override
    public void login(final String username, final String password, final BasicCallback callback) {
        final User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    SharedPreferences.getInstance().saveObject("user", bmobUser);
                    JMessageClient.login(username, password, callback);
                } else {
                    //toast(e.getMessage());
                }
            }
        });
    }
}