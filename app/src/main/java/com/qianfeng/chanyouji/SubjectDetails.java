package com.qianfeng.chanyouji;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.qianfeng.chanyouji.adapter.SubjectdetalilsAdapter;
import com.qianfeng.chanyouji.beans.MyListView;
import com.qianfeng.chanyouji.beans.SubjectDetalisData;
import com.qianfeng.chanyouji.netutils.BitmapHelper;
import com.qianfeng.chanyouji.urls.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;


public class SubjectDetails extends ActionBarActivity {

    private ProgressBar pb;
    private ImageView image_title;
    private TextView txtv_name,txtv_title,description;
    private ListView sub_listView;
    private List<SubjectDetalisData> datas;
    private SubjectdetalilsAdapter adapter;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        init();
        getData();

    }

    private void init(){
        pb= ((ProgressBar) findViewById(R.id.sub_details_pb));
        sub_listView= ((ListView) findViewById(R.id.sub_listView));
        datas=new ArrayList<SubjectDetalisData>();

        View view = View.inflate(this,R.layout.sub_deatils_head,null);
        image_title= ((ImageView) view.findViewById(R.id.image_title));
        txtv_name= ((TextView) view.findViewById(R.id.txtv_name));
        txtv_title= ((TextView) view.findViewById(R.id.txtv_title));
        description= ((TextView) view.findViewById(R.id.description));

        sub_listView.addHeaderView(view);




        adapter=new SubjectdetalilsAdapter(datas,SubjectDetails.this);
        sub_listView.setAdapter(adapter);



    }


    private void getData(){


        HttpUtils utils=new HttpUtils();


        utils.send(HttpRequest.HttpMethod.GET, Urls.Subject_XiangQing+id+".json",new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> s) {
//                Log.d("Tag",s.result);

                try {
                    JSONObject jsonObject = new JSONObject(s.result);
                    txtv_name.setText(jsonObject.getString("name"));
                    txtv_title.setText(jsonObject.getString("title"));
                    BitmapHelper.getBitmapUtils().display(image_title, jsonObject.getString("image_url"));

                    JSONArray article_sections = jsonObject.getJSONArray("article_sections");

                    description.setText(article_sections.getJSONObject(0).getString("description"));


                    SubjectDetalisData data=null;

                    for(int i=1;i<article_sections.length();i++){

                        JSONObject obj = article_sections.getJSONObject(i);
                        data=new SubjectDetalisData();

                        if(!obj.getString("title").equals("")){
//                            data.setTitle(obj.getString("title"));
                            data.setTag(1);
                        }else{
                            data.setTag(0);
                        }

                        data.setTitle(obj.getString("title"));
                        data.setImage_url(obj.getString("image_url"));
                        data.setDescription(obj.getString("description"));

                        if(obj.has("note")){
                            JSONObject note = obj.getJSONObject("note");
                            data.setTrip_name(note.getString("trip_name"));
                            data.setUser_name(note.getString("user_name"));

                        }

                        if(obj.has("attraction")) {
                            JSONObject attraction = obj.getJSONObject("attraction");

                            data.setName(attraction.getString("name"));
                        }

                        datas.add(data);

                    }
//                    Log.d("bb",datas.toString());


                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                pb.setVisibility(View.GONE);



            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(SubjectDetails.this,"数据请求失败",Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        });





    }
}
