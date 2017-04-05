package com.ar.pay.sharefoot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ar.pay.sharefoot.activity.EditTextActivity;
import com.ar.pay.sharefoot.activity.MenuActivity;
import com.ar.pay.sharefoot.activity.SearchActivity;
import com.ar.pay.sharefoot.base.BaseDataActivity;
import com.ar.pay.sharefoot.bean.Category;
import com.ar.pay.sharefoot.bean.User;
import com.ar.pay.sharefoot.fragment.CookBookFragment;
import com.ar.pay.sharefoot.fragment.FootFragment;
import com.ar.pay.sharefoot.utils.SharedPreferences;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;
import com.review.signature.Review;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseDataActivity<List<Category>> {
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;

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
        User user = (User) SharedPreferences.getInstance().readObject("user");
        if(user.isEdit()){
            fab.setVisibility(View.VISIBLE);
        }
//        Review.MD5Review(this,"com.ar.pay.sharefoot","761a8e3374d49b746beddf9674b9d7e9");
//        if (SharedPreferences.getInstance().readObject("cateroty") == null) {
            sqlHelper.queryCategory();
//        } else {
//            categoryList = (List<Category>) SharedPreferences.getInstance().readObject("cateroty");
//            pareseData(categoryList);
//        }
    }

    @Override
    protected void setData(List<Category> o) {
        categoryList = o;
        SharedPreferences.getInstance().saveObject("cateroty", o);
        pareseData(o);
    }

    private void pareseData(List<Category> o) {
        for (Category category : o) {
            switch (category.getUiType()) {
                case 0:
                    FootFragment foot =FootFragment.getFragment(category.getCategoryId());
                    flist.add(foot);
                    break;
                case 1:
                    CookBookFragment cookBookFragment = CookBookFragment.getFragment(category.getCategoryId());
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

    @OnClick(R.id.fab)
    public void onClick() {
        startActivityWithData(EditTextActivity.class);
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
        switch (item.getItemId()) {
            case R.id.action_search:
                //toast("action_search");
                startActivityWithData(SearchActivity.class);
                break;
            case android.R.id.home:
                //toast("home");
                startActivityWithData(MenuActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
