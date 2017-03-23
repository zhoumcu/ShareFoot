package com.ar.pay.sharefoot.sql;

import android.util.Log;

import com.ar.pay.sharefoot.bean.Category;
import com.ar.pay.sharefoot.bean.Food;
import com.ar.pay.sharefoot.bean.MyCollect;
import com.ar.pay.sharefoot.bean.Person;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.service.OnResult;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
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

    public static void registerUser(User p2){
        Log.i("bmob","注册成功:");
        p2.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    Log.i("bmob","注册成功:" +s.toString());
                }else{

                }
            }
        });
    }
    public static void login(User p2, final OnResult result){
        p2.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if(e==null){
                    Log.i("bmob","登录成功:");
                    result.onSucess(bmobUser);
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{

                }
            }
        });
    }
    public static void updateUser(User newUser, String imageUrl){
        newUser.setImageUrl(imageUrl);
        BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
        newUser.update(bmobUser.getObjectId(),new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","更新用户信息成功");
                }else{
                    Log.i("bmob","更新用户信息失败:" + e.getMessage());
                }
            }
        });
    }
    public static void uploadImageFile(final User user, String picPath){
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.e("test","上传文件成功:" + bmobFile.getFileUrl());
                    updateUser(user,bmobFile.getFileUrl());
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
    public static void uploadFile(final String author,final String title, final String describer, final String content, final int categoryId, String picPath){
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.e("test","上传文件成功:" + bmobFile.getFileUrl());
                    //createFoot(bmobFile.getFileUrl());
                    createFoot(author,title,describer,content,categoryId,bmobFile.getFileUrl());
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
    public static void createFoot(final String author,String title,String describer,String content,int categoryId,String imageUrl){
        Food food = new Food();
        food.setImageUrl(imageUrl);
        food.setAuthor(author);
        food.setDescriber(describer);
        food.setTitle(title);
        food.setCategoryId(categoryId);
        food.setContent(content);
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
    public static void queryFood(int categoryId,final OnResult onResult){
        BmobQuery query =new BmobQuery("Food");
        query.addWhereEqualTo("categoryId",categoryId);
        query.setLimit(50);
        query.order("-createdAt");
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
    public static void queryFood(String username,final OnResult onResult){
        BmobQuery query =new BmobQuery("Food");
        query.addWhereEqualTo("username",username);
        query.setLimit(50);
        query.order("-createdAt");
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
    public void queryFood(String key){
        String bql ="SELECT * FROM Food WHERE title LIKE=? or author LIKE=?";//查询所有的游戏得分记录
        BmobQuery query =new BmobQuery("Food");
//        query.addWhereEqualTo("title",key);
        query.setLimit(20);
        query.order("-createdAt");
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
//        query.findObjectsByTable(new QueryListener<JSONArray>() {
//            @Override
//            public void done(JSONArray ary, BmobException e) {
//                if(e==null){
//                    Log.i("bmob","查询成功："+ary.toString());
//                    result.onSucess(Food.arrayFoodsFromData(ary.toString()));
//                }else{
//                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
//                }
//            }
//        });
        query.doSQLQuery(bql, new SQLQueryListener<Food>() {
            @Override
            public void done(BmobQueryResult<Food> ary, BmobException e) {
                Log.i("bmob","查询成功："+ary.getCount());
            }
        },key,key);
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
    public static void createMyCollect(Food food, String username, final OnResult onResult){
        MyCollect collect = new MyCollect();
        collect.setObjectId(food.getObjectId());
        collect.setUsername(username);
        collect.setCollect(true);
        collect.setFood(food);
        collect.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Log.i("bmob","添加数据成功，返回objectId为："+objectId);
                    onResult.onSucess(true);
                }else{
                    Log.i("bmob","创建数据失败：" + e.getMessage());
                }
            }
        });
    }
    public static void queryMyCollect(final String username, final OnResult onResult){
        BmobQuery query =new BmobQuery("MyCollect");
        query.addWhereEqualTo("username",username);
        query.setLimit(20);
        query.order("-createdAt");
        query.include("food");// 希望在查询帖子信息的同时也把发布人的信息查询出来
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功："+ary.toString());
                    List<MyCollect> myCollects = MyCollect.arrayMyCollectFromData(ary.toString());
                    List<Food> list = new ArrayList<Food>();
                    for (MyCollect m: myCollects){
                        list.add(m.getFood());
                    }
                    onResult.onSucess(list);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public static void queryIsMyCollect(final String username, final Food food,final OnResult onResult){
        BmobQuery query =new BmobQuery("MyCollect");
        query.addWhereEqualTo("username",username);
        BmobQuery query1 =new BmobQuery("MyCollect");
        query1.addWhereEqualTo("food",food);
        BmobQuery query2 =new BmobQuery("MyCollect");
        query2.addWhereEqualTo("isCollect",true);
        //最后组装完整的and条件
        List<BmobQuery<MyCollect>> andQuerys = new ArrayList<BmobQuery<MyCollect>>();
        andQuerys.add(query);
        andQuerys.add(query1);
        andQuerys.add(query2);
        BmobQuery query3 = new BmobQuery("MyCollect");
        query3.and(andQuerys);
        query3.setLimit(1);
        query3.order("-createdAt");
        //v3.5.0版本提供`findObjectsByTable`方法查询自定义表名的数据
        query3.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray ary, BmobException e) {
                if(e==null){
                    Log.i("bmob","查询成功："+ary.toString());
                    onResult.onSucess(MyCollect.arrayMyCollectFromData(ary.toString()));
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public static void deleteMyCollect(String id, final OnResult onResult){
        MyCollect myCollect = new MyCollect();
        myCollect.setObjectId(id);
        myCollect.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","成功");
                    onResult.onSucess(false);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
