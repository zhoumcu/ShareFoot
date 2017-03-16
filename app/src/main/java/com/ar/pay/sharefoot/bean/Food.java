package com.ar.pay.sharefoot.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * author：Administrator on 2017/3/13 11:33
 * company: xxxx
 * email：1032324589@qq.com
 */

public class Food extends BmobObject{
    private String title;
    private String describer;
    private String imageUrl;
    private String author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    public static List<Food> arrayFoodsFromData(String str) {

        Type listType = new TypeToken<ArrayList<Food>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
