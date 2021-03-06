package com.ar.pay.sharefoot.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ar.pay.sharefoot.APP;
import com.ar.pay.sharefoot.R;
import com.ar.pay.sharefoot.bean.Food;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：Administrator on 2017/3/13 15:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class ViewHolderFood extends BaseViewHolder<Food> {
    @BindView(R.id.img_food)
    ImageView imgFood;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_describer)
    TextView tvDescriber;

    public ViewHolderFood(ViewGroup parent) {
        super(parent, R.layout.item_food);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Food data) {
        super.setData(data);
        tvTitle.setText(data.getTitle());
        tvDescriber.setText(data.getDescriber());
        Picasso.with(APP.getInstances().getApplicationContext())
                .load(data.getImageUrl())
//                .resize(130, 80)
//                .centerCrop()
                .into(imgFood);
    }
}
