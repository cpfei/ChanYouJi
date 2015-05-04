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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.chanyouji.adapter.ArticleAdapter;
import com.qianfeng.chanyouji.beans.Articles;
import com.qianfeng.chanyouji.netutils.DownLoadData;
import com.qianfeng.chanyouji.netutils.PaseJson;
import com.qianfeng.chanyouji.urls.FinalUrl;

import java.util.List;


public class ArticlesActivity extends ActionBarActivity implements PullToRefreshBase.OnRefreshListener<ListView>{

    private PullToRefreshListView articlesListView;
    private String id;
    private String name_zh_cn;
    private TextView text_Name;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //刷新完成
            articlesListView.onRefreshComplete();
            if (msg.what==1) {
                String data = (String) msg.obj;
                //解析
                List<Articles> articleses = PaseJson.jsonToArticles(data);
                Log.d("articleses",articleses.toString());

                //articlesListView 设置适配器
                articlesListView.setAdapter(new ArticleAdapter(ArticlesActivity.this,articleses));

            }
        }
    };
    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        init();
        DownLoadData.downData(this, FinalUrl.ARTICLES+id+"&page=1",handler,1);

    }

    private void init() {
        linear = ((LinearLayout) findViewById(R.id.articlesback));
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        articlesListView = ((PullToRefreshListView) findViewById(R.id.articleslistview));
        articlesListView.setOnRefreshListener(this);
        text_Name = ((TextView) findViewById(R.id.articles));
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name_zh_cn = intent.getStringExtra("name_Zh_Cn");
        text_Name.setText(name_zh_cn);



    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        DownLoadData.downData(this, FinalUrl.ARTICLES+id+"&page=1",handler,1);
    }
}
