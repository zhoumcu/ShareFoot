package com.ar.pay.sharefoot.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by ymn on 2017/3/18.
 */
public class MyCollect extends BmobObject{

    /**
     * __type : Pointer
     * objectId : 14364f08c2
     * className : Food
     */

    private Food food;

    public String getUsername() {
        return username;
    }

    private String username;

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    private Boolean isCollect;

    public static List<MyCollect> arrayMyCollectFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyCollect>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
