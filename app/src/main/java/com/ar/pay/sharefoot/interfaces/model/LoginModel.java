package com.ar.pay.sharefoot.interfaces.model;

import android.widget.EditText;

import com.ar.pay.sharefoot.bean.User;

import cn.jpush.im.api.BasicCallback;

/**
* Created by Administrator on 2017/03/24
*/

public interface LoginModel{
    public String verfiyUserName(EditText text);
    public String verfiyPassWord(EditText text);
    public void login(String username, String password, BasicCallback callback);
}