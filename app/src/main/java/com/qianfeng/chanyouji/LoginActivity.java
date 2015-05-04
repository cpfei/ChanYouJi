package com.qianfeng.chanyouji;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.chanyouji.fragment.LoginFragment;
import com.qianfeng.chanyouji.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import m.framework.utils.UIHandler;


public class LoginActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioGroup login_Rg;
    private ViewPager login_ViewPage;
    private List<Fragment> fragments;
    private RadioButton[] rbs=new RadioButton[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        fragments=new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        rbs[0]= ((RadioButton) findViewById(R.id.login));
        rbs[1]= ((RadioButton) findViewById(R.id.zhuce));
        login_Rg = ((RadioGroup) findViewById(R.id.login_rg));
        login_Rg.setOnCheckedChangeListener(this);
        login_ViewPage = ((ViewPager) findViewById(R.id.login_viewpage));
        login_ViewPage.setOnPageChangeListener(this);
        login_ViewPage.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i <rbs.length; i++) {
            if (rbs[i].getId()==checkedId){
                login_ViewPage.setCurrentItem(i);
            }

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        rbs[position].setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



    class MyFragmentAdapter extends FragmentPagerAdapter{

        public MyFragmentAdapter(FragmentManager fm) {
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


//    private void authorize(Platform plat) {
//        if (plat == null) {
//            popupOthers();
//            return;
//        }
////判断指定平台是否已经完成授权
//        if(plat.isValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
//                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                login(plat.getName(), userId, null);
//                return;
//            }
//        }
//        plat.setPlatformActionListener(this);
//        // true不使用SSO授权，false使用SSO授权
//        plat.SSOSetting(true);
//        //获取用户资料
//        plat.showUser(null);
//    }

//    private void popupOthers() {
//
//
//
//    }


}
