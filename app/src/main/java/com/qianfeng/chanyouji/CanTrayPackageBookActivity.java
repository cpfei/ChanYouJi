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


public class CanTrayPackageBookActivity extends ActionBarActivity implements View.OnClickListener, PullToRefreshBase.OnRefreshListener<ListView> {

    private TextView text_Name;
    private Button btnBack;
    private PullToRefreshListView listView;
    private String id;
    private List<Entry_Destination> list;
    private CantrayPackageBookAdapter adapter;
    private Handler handler =new Handler(){


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_tray_package_book);
        init();

        DownLoadData.downData(this, FinalUrl.ENTER_DESTINATION+id+".json",handler,1);


    }

    private void init() {
       // getSupportActionBar().hide();
        listView = (PullToRefreshListView) findViewById(R.id.pulltolistview);
        listView.setOnRefreshListener(this);
        Intent intent = getIntent();
        String name_zh_cn = intent.getStringExtra("name_Zh_cn");
        id = intent.getStringExtra("id");
        btnBack = ((Button) findViewById(R.id.btnback));
        btnBack.setOnClickListener(this);
        text_Name = ((TextView) findViewById(R.id.text_name));
        text_Name.setText(name_zh_cn + "口袋书");
        list=new ArrayList<Entry_Destination>();
        adapter = new CantrayPackageBookAdapter(CanTrayPackageBookActivity.this,list);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {

        DownLoadData.downData(this, FinalUrl.ENTER_DESTINATION+id+".json",handler,1);
    }
}
