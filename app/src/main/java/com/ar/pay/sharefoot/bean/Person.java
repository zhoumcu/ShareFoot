package com.ar.pay.sharefoot.bean;

import cn.bmob.v3.BmobObject;

/**
 * author：Administrator on 2017/3/13 10:34
 * company: xxxx
 * email：1032324589@qq.com
 */

public class Person extends BmobObject{
    private String name;
    private String address;
    private int age;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
