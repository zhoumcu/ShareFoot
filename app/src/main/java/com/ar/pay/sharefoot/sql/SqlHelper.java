package com.ar.pay.sharefoot.sql;

import android.util.Log;

import com.ar.pay.sharefoot.bean.Category;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.bean.Person;
import com.ar.pay.sharefoot.service.OnResult;

import org.json.JSONArray;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * author：Administrator on 2017/3/13 11:16
 * company: xxxx
 * email：1032324589@qq.com
 */

public class SqlHelper {
    private static OnResult result;

    public SqlHelper(OnResult onResult) {
        result = onResult;
    }

    public static void createData(String imageUrl){
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.setAge(25);
        p2.setImageUrl(imageUrl);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","添加数据成功，返回objectId为："+objectId);
                }else{
                    Log.i("bmob","创建数据失败：" + e.getMessage());
                }
            }
        });
    }
    /**
     * 查询数据
     */
    public static void queryData(){
        BmobQuery query =new BmobQuery("Person");
        query.addWhereEqualTo("age", 25);
        query.setLimit(20);
        query.order("createdAt");
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功："+ary.toString());
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public static void uploadFile(){
        String picPath = "sdcard/test.jpg";
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.e("test","上传文件成功:" + bmobFile.getFileUrl());
                    createFoot(bmobFile.getFileUrl());
                }else{
                    Log.e("test","上传文件失败：" + e.getMessage());
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }
    public static void createFoot(String imageUrl){
        Food food = new Food();
        food.setImageUrl(imageUrl);
        food.setAuthor("zhoumcu");
        food.setDescriber("美女与食神");
        food.setTitle("美女与食神");
        food.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","添加数据成功，返回objectId为："+objectId);
                }else{
                    Log.i("bmob","创建数据失败：" + e.getMessage());
                }
            }
        });
    }
    /**
     * 查询数据
     */
    public static void queryFood(final OnResult onResult){
        BmobQuery query =new BmobQuery("Food");
        query.setLimit(20);
        query.order("createdAt");
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功："+ary.toString());
                    onResult.onSucess(Food.arrayFoodsFromData(ary.toString()));
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    /**
     * 查询数据
     */
    public static void queryCategory(){
        BmobQuery query =new BmobQuery("Category");
        query.setLimit(20);
        query.order("createdAt");
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功："+ary.toString());
                    result.onSucess(Category.arrayCategoryFromData(ary.toString()));
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public static void createCategory(){
        Category category = new Category();
        category.setCategory("美食鉴赏");
        category.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","添加数据成功，返回objectId为："+objectId);
                }else{
                    Log.i("bmob","创建数据失败：" + e.getMessage());
                }
            }
        });
    }
}
