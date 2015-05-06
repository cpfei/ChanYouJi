package com.qianfeng.chanyouji;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.qianfeng.chanyouji.fragment.SearchForeignFragment;
import com.qianfeng.chanyouji.fragment.SearchFourSeasonsFragment;
import com.qianfeng.chanyouji.fragment.SearchHomeFragment;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private List<Fragment> fragments;
    private ViewPager viewPager;
    private RadioGroup rg;
    private SearchViewPagerAdapter adapter;
    private ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();

    }


    /**
     * 初始化fragment集合
     */
    private void init() {

        fragments = new ArrayList<Fragment>();//初始化fragment
        fragments.add(new SearchForeignFragment());
        fragments.add(new SearchHomeFragment());
        fragments.add(new SearchFourSeasonsFragment());

        viewPager = ((ViewPager) findViewById(R.id.searchViewPager));
        rg = ((RadioGroup) findViewById(R.id.search_RadioGroup));
        ((RadioButton) rg.getChildAt(0)).setChecked(true);//默认讲第一个设置为true
        adapter = new SearchViewPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(this);

        rg.setOnCheckedChangeListener(this);//radioButton的点击监听




    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) rg.getChildAt(position)).setChecked(true);//滑动时rb的背景图随之改变
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for (int i = 0; i < rg.getChildCount(); i++) {
            if (rg.getChildAt(i).getId() == checkedId) {
                viewPager.setCurrentItem(i);
            }
        }
    }


    /**
     * fragment的适配器
     */
    class SearchViewPagerAdapter extends FragmentPagerAdapter {


        public SearchViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    //左上角返回
    public void click(View V){
        finish();
    }




}
