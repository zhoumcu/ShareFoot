package com.ar.pay.sharefoot.service;

/**
 * author：Administrator on 2017/3/13 11:40
 * company: xxxx
 * email：1032324589@qq.com
 */

public interface OnResult<M> {
    public void onSucess(M o);
    public void onErro();
}
