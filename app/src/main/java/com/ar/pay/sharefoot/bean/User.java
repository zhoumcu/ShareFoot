package com.ar.pay.sharefoot.bean;

import cn.bmob.v3.BmobUser;

/**
 * author：Administrator on 2017/3/17 15:30
 * company: xxxx
 * email：1032324589@qq.com
 */

public class User extends BmobUser{
    private String imageUrl;

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    private boolean edit;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
