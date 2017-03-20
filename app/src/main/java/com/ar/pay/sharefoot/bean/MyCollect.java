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

    private FoodEntity food;

    public String getUsername() {
        return username;
    }

    private String username;

    public static List<MyCollect> arrayMyCollectFromData(String str) {

        Type listType = new TypeToken<ArrayList<MyCollect>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }
    public FoodEntity getFood() {
        return food;
    }
    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class FoodEntity {
        private String __type;ftf
        private String objectId;
        private String className;

        public static List<FoodEntity> arrayFoodEntityFromData(String str) {

            Type listType = new TypeToken<ArrayList<FoodEntity>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
