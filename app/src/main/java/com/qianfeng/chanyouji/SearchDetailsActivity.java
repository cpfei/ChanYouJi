package com.qianfeng.chanyouji;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.chanyouji.adapter.TravelNotesListAdapter;
import com.qianfeng.chanyouji.beans.TravelNotesInfo;
import com.qianfeng.chanyouji.urls.Urls;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class SearchDetailsActivity extends ActionBarActivity {

    private PullToRefreshListView listView;
    private ProgressBar search_pb;
    private RequestQueue requestQueue;
    private List<TravelNotesInfo> datas;
    private TravelNotesListAdapter adapter;
    private TextView searchDTitle;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);
        listView = ((PullToRefreshListView) findViewById(R.id.search_listView));
        search_pb= (ProgressBar) findViewById(R.id.search_pb);
        searchDTitle= (TextView) findViewById(R.id.searchDTitle);
        requestQueue = Volley.newRequestQueue(this);
        datas=new ArrayList<TravelNotesInfo>();


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");

        searchDTitle.setText(name+"游记");


        downData();

        adapter = new TravelNotesListAdapter(this,datas);
        listView.setAdapter(adapter);


    }

    //请求数据   12.json?page=1
    private void downData(){
        JsonArrayRequest request = new JsonArrayRequest(Urls.Search_SiJi+id+".json?page=1",new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
//                Log.d("Tag",jsonArray.toString());

                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String name = obj.getString("name");
                        int photos_count = obj.getInt("photos_count");

                        String start_date = obj.getString("start_date");
                        int days = obj.getInt("days");
                        String front_cover_photo_url = obj.getString("front_cover_photo_url");

                        boolean featured = obj.getBoolean("featured");
                        JSONObject user = obj.getJSONObject("user");
                        String image = user.getString("image");


                        TravelNotesInfo data=new TravelNotesInfo(front_cover_photo_url,name,
                                start_date,days,photos_count,featured,image);

                        datas.add(data);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



                adapter.notifyDataSetChanged();
                search_pb.setVisibility(View.GONE);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SearchDetailsActivity.this,"数据请求失败",Toast.LENGTH_SHORT).show();
                search_pb.setVisibility(View.GONE);
            }
        });

        requestQueue.add(request);

    }




}
