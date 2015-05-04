package com.qianfeng.chanyouji;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class Enter_LvxingDi extends ActionBarActivity  implements PullToRefreshBase.OnRefreshListener2<ListView>{

    private PullToRefreshListView pullListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__lvxing_di);
        init();

    }

    private void init() {
        LinearLayout linner = (LinearLayout) findViewById(R.id.articlesback);
        linner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pullListView = ((PullToRefreshListView) findViewById(R.id.articleslistview));
        pullListView.setMode(PullToRefreshBase.Mode.BOTH);
        pullListView.setOnRefreshListener(this);


    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        //下拉刷新


    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
      //上拉加载
    }
}
