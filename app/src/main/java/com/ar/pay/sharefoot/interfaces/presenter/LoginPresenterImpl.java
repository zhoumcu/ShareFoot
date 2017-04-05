package com.ar.pay.sharefoot.interfaces.presenter;


import android.widget.EditText;

import com.ar.pay.sharefoot.interfaces.model.LoginModel;
import com.ar.pay.sharefoot.interfaces.model.LoginModelImpl;
import com.ar.pay.sharefoot.interfaces.view.LoginView;
import com.ar.pay.sharefoot.presenter.interfaces.LoginPresenter;

import cn.jpush.im.api.BasicCallback;

/**
* Created by Administrator on 2017/03/24
*/

public class LoginPresenterImpl implements LoginPresenter {

    @Override
    public void login(EditText eduser, EditText edpassword, final LoginView loginView) {
        //实例化产品模型
        final LoginModel loginModel = new LoginModelImpl();
        loginView.showProgress();
        String username = loginModel.verfiyUserName(eduser);
        String password = loginModel.verfiyPassWord(edpassword);
        if(username==null&&password==null) return;
        //从产品模型中获取产品分组数据
        loginModel.login(username, password, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                loginView.loginResult(i,s);
                loginView.hideProgress();
            }
        });
    }
}