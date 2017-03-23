package com.ar.pay.sharefoot.utils;

import android.view.View;

/**
 * author：Administrator on 2017/3/14 16:42
 * company: xxxx
 * email：1032324589@qq.com
 */

public class ListConfig implements Cloneable{
    public static ListConfig Default = new ListConfig();
    public static void setDefaultListConfig(ListConfig config){
        Default = config;
    }

    public boolean mRefreshAble = false;
    public boolean mLoadmoreAble = false;
    public boolean mNoMoreAble = true;
    public boolean mErrorAble = true;
    public View mLoadMoreView;
    public View mNoMoreView;
    public View mErrorView;

    public ListConfig setRefreshAble(boolean mRefreshAble) {
        this.mRefreshAble = mRefreshAble;
        return this;
    }

    public ListConfig setLoadmoreAble(boolean mLoadmoreAble) {
        this.mLoadmoreAble = mLoadmoreAble;
        return this;
    }

    public ListConfig setLoadMoreView(View mLoadMoreView) {
        this.mLoadMoreView = mLoadMoreView;
        return this;
    }

    public ListConfig setNoMoreView(View mNoMoreView) {
        this.mNoMoreView = mNoMoreView;
        return this;
    }

    public ListConfig setNoMoreAble(boolean mNoMoreAble) {
        this.mNoMoreAble = mNoMoreAble;
        return this;
    }

    @Override
    public ListConfig clone(){
        try {
            return (ListConfig) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ListConfig();
    }
}
