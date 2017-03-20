package com.ar.pay.sharefoot.receiver;

import cn.bmob.v3.BmobInstallation;

/**
 * Created by ymn on 2017/3/17.
 */
public class MyBmobInstallation extends BmobInstallation {

    /**
     * 用户id-这样可以将设备与用户之间进行绑定
     */
    private String uid;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MyBmobInstallation() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}