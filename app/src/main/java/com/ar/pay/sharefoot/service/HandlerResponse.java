package com.ar.pay.sharefoot.service;

import android.os.Handler;
import android.os.Message;

/**
 * author：Administrator on 2017/3/13 17:01
 * company: xxxx
 * email：1032324589@qq.com
 */

public abstract class HandlerResponse implements OnResult{
    public abstract void onHandlerSucess(Object obj);
    public abstract void onHandlerError();
    private static final int SUCESS = 0;
    private static final int ERROR = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SUCESS:
                    onHandlerSucess(msg.obj);
                    break;
                case ERROR:
                    onHandlerError();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    public void onSucess(Object o) {
        Message msg = new Message();
        msg.what = SUCESS;
        msg.obj = o;
        handler.sendMessage(msg);
    }

    @Override
    public void onErro() {
        Message msg = new Message();
        msg.what = ERROR;
        handler.sendMessage(msg);
    }
}
