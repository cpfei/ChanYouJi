package com.qianfeng.chanyouji;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hb.views.PinnedSectionListView;
import com.qianfeng.chanyouji.adapter.EnterTravelAdapter;
import com.qianfeng.chanyouji.beans.EnterTravelBean;
import com.qianfeng.chanyouji.beans.ImageUrls;
import com.qianfeng.chanyouji.netutils.BitmapHelper;
import com.qianfeng.chanyouji.netutils.DownLoadData;
import com.qianfeng.chanyouji.urls.FinalUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class EnterTravelActivity extends ActionBarActivity {

    private String id;
    private ListView pinndListView;

    private List<EnterTravelBean> list;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1) {
                String s = (String) msg.obj;
                //解析数据
                try {
                    JSONObject object = new JSONObject(s);
                    String image_url = object.getString("image_url");
                    BitmapHelper.getBitmapUtils().display(head_iv,image_url);
                    //头部视图设置
                    text_name.setText(object.getString("name_zh_cn"));
                    text_name_En.setText(object.getString("name_en"));
                    photo_btn.setText("图片 "+object.getString("photos_count"));
                    youJi_btn.setText("游记 "+object.getString("attraction_trips_count"));
                    text_content.setText(object.getString("description"));

                    EnterTravelBean enterTravelBean1 = new EnterTravelBean();
                    enterTravelBean1.setTag(0);
                    enterTravelBean1.setTab_name("实用贴士");
                    list.add(enterTravelBean1);

                    EnterTravelBean enterTravelBean2 = new EnterTravelBean();
                    enterTravelBean2.setTag(1);
                    enterTravelBean2.setDescription(object.getString("tips_html"));
                    list.add(enterTravelBean2);
                    //
                    JSONArray array = object.getJSONArray("attraction_trip_tags");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        if (!"".equals(object1.getString("name"))) {
                            EnterTravelBean enterTravelBean = new EnterTravelBean();
                            enterTravelBean.setTab_name(object1.getString("name"));
                            enterTravelBean.setTag(0);

                            list.add(enterTravelBean);
                        }

                        JSONArray array1 = object1.getJSONArray("attraction_contents");
                        for (int j = 0; j < array1.length(); j++) {
                            JSONObject jsonObject = array1.getJSONObject(j);
                            EnterTravelBean enterTravelBean = new EnterTravelBean();
                            enterTravelBean.setTag(1);
                            enterTravelBean.setDescription(jsonObject.getString("description"));
                            if (jsonObject.has("trip")) {
                                JSONObject trip = jsonObject.getJSONObject("trip");
                                enterTravelBean.setTrip_name(trip.getString("name"));
                                enterTravelBean.setPhotos_count(trip.getString("photos_count"));
                                enterTravelBean.setDays(trip.getString("days"));
                                enterTravelBean.setStart_date(trip.getString("start_date"));
                                enterTravelBean.setFront_cover_photo_url(trip.getString("front_cover_photo_url"));
                            }

                            if (jsonObject.has("user")) {
                                JSONObject user = jsonObject.getJSONObject("user");
                                enterTravelBean.setUser_name(user.getString("name"));
                                enterTravelBean.setUser_image(user.getString("image"));
                            }

                            if (jsonObject.has("notes")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("notes");
                                List<ImageUrls> imageUrlses=new ArrayList<>();
                                for (int k = 0; k < jsonArray.length(); k++) {
                                    ImageUrls imageUrls = new ImageUrls();
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(k);
                                    imageUrls.setPhoto_Url(jsonObject1.getString("photo_url"));
                                    imageUrls.setText_content(jsonObject1.getString("description"));
                                    imageUrlses.add(imageUrls);
                                }
                                enterTravelBean.setImageViews(imageUrlses);
                            }

                            list.add(enterTravelBean);
                        }
                    }
                    EnterTravelAdapter adapter = new EnterTravelAdapter(list,EnterTravelActivity.this);

                    pinndListView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    };
    private ImageView head_iv;
    private TextView text_name,text_name_En,text_content;
    private RadioButton photo_btn;
    private RadioButton youJi_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        init();


    }

    private void init() {
        list=new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pinndListView = ((ListView) findViewById(R.id.item_travel_iv));

        View view = View.inflate(this, R.layout.header_activity_travel, null);
        head_iv = ((ImageView) view.findViewById(R.id.header_travel_iv1));

        text_name = ((TextView) view.findViewById(R.id.header_travel_tv_cn));
        text_name_En = ((TextView) view.findViewById(R.id.header_travel_tv_en));
        photo_btn = (RadioButton) view.findViewById(R.id.travel_rb1);
        //设置图片监听

        photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        youJi_btn = ((RadioButton) view.findViewById(R.id.travel_rb2));

        //设置邮寄监听
        youJi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        text_content=((TextView) view.findViewById(R.id.header_travel_text));

        //下载数据
        DownLoadData.downData(this, FinalUrl.ENTER_ENTERTRAVEL+id+".json",handler,1);
        //设置适配器
        pinndListView.addHeaderView(view);


    }


}
