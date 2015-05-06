package com.qianfeng.chanyouji;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.chanyouji.adapter.CantrayPackageBookAdapter;
import com.qianfeng.chanyouji.beans.Destination;
import com.qianfeng.chanyouji.beans.Entry_Destination;
import com.qianfeng.chanyouji.netutils.DownLoadData;
import com.qianfeng.chanyouji.netutils.PaseJson;
import com.qianfeng.chanyouji.urls.FinalUrl;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class CanTrayPackageBookActivity extends ActionBarActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener<ListView> {

    private TextView text_Name;
    private PullToRefreshListView listView;
    private String id;
    private List<Entry_Destination> list;
    private CantrayPackageBookAdapter adapter;
    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            listView.onRefreshComplete();
            list.clear();
            String s = (String) msg.obj;
            //解析
            List<Entry_Destination> entry_destinations = PaseJson.jsonToList2(s);
            list.addAll(entry_destinations);
            //设置适配器
            adapter.notifyDataSetChanged();

        }
    };
    private ImageView image_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_tray_package_book);
        init();

        DownLoadData.downData(this, FinalUrl.ENTER_DESTINATION + id + ".json", handler, 1);


    }

    private void init() {
        // getSupportActionBar().hide();
        listView = (PullToRefreshListView) findViewById(R.id.pulltolistview);
        listView.setOnRefreshListener(this);
        Intent intent = getIntent();
        String name_zh_cn = intent.getStringExtra("name_Zh_cn");
        id = intent.getStringExtra("id");
        image_back = ((ImageView) findViewById(R.id.image_log));
        image_back.setOnClickListener(this);

        //分享按钮
        ImageView image_share = (ImageView) findViewById(R.id.share_btn);
        image_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
                text_Name = ((TextView) findViewById(R.id.text_name));
        text_Name.setText(name_zh_cn + "口袋书");
        list = new ArrayList<Entry_Destination>();
        adapter = new CantrayPackageBookAdapter(CanTrayPackageBookActivity.this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

        DownLoadData.downData(this, FinalUrl.ENTER_DESTINATION + id + ".json", handler, 1);
    }




    private void showShare() {
        m.framework.utils.UIHandler.prepare();
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }



}
