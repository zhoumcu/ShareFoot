package com.ar.pay.sharefoot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ar.pay.sharefoot.base.BaseDataActivity;
import com.ar.pay.sharefoot.bean.Category;
import com.ar.pay.sharefoot.fragment.CookBookFragment;
import com.ar.pay.sharefoot.fragment.FootFragment;
import com.ar.pay.sharefoot.service.HandlerResponse;
import com.ar.pay.sharefoot.utils.SharedPreferences;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseDataActivity<List<Category>> {
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private List<Category> categoryList = new ArrayList<>();//= {"美食鉴赏","菜谱","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技","科技"};
    private List<Fragment> flist = new ArrayList<>();
    private myPagerAdapter adapter;

    @Override
    public void onUICreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.category);
        ButterKnife.bind(this);
    }

    @Override
    public void onInitView() {
        viewPager.setOffscreenPageLimit(5);
        adapter = new myPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabs.setViewPager(viewPager);
    }

    @Override
    public void onInitData() {

        if(SharedPreferences.getInstance().readObject("cateroty")==null){
            sqlHelper.queryCategory();
        }else {
            categoryList = (List<Category>) SharedPreferences.getInstance().readObject("cateroty");
            pareseData(categoryList);
        }
//        sqlHelper.createCategory();
    }

    @Override
    protected void setData(List<Category> o) {
        categoryList = o;
        SharedPreferences.getInstance().saveObject("cateroty",o);
        pareseData(o);
    }
    private void pareseData(List<Category> o){
        for (Category category : o) {
            switch (category.getUiType()) {
                case 0:
                    FootFragment foot = new FootFragment();
                    flist.add(foot);
                    break;
                case 1:
                    CookBookFragment cookBookFragment = new CookBookFragment();
                    flist.add(cookBookFragment);
                    break;
            }
        }
        adapter.notifyDataSetChanged();
        tabs.notifyDataSetChanged();
    }
    @Override
    public void onEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private class myPagerAdapter extends FragmentPagerAdapter {

        public myPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return flist.get(position);
        }

        @Override
        public int getCount() {
            return flist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return categoryList.get(position).getCategory();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
