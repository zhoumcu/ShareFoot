package com.ar.pay.sharefoot.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.ar.pay.sharefoot.bean.MyCollect;
import com.ar.pay.sharefoot.viewholder.ViewHolderFood;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * author：Administrator on 2017/3/13 15:29
 * company: xxxx
 * email：1032324589@qq.com
 */

public class MyFoodAdapter extends RecyclerArrayAdapter<MyCollect.FoodEntity> {

    public MyFoodAdapter(Context context) {
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderFood(parent);
    }
}
