package com.qianfeng.chanyouji.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qianfeng.chanyouji.R;
import com.qianfeng.chanyouji.netutils.BitmapHelper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aaa on 15-5-4.
 */
public class Enter_LvxingDi_Adapter extends BaseAdapter {
    private Context context;
    private List<HashMap<String,String>> hashMaps;

    public Enter_LvxingDi_Adapter(Context context, List<HashMap<String, String>> hashMaps) {
        this.context = context;
        this.hashMaps = hashMaps;
    }

    @Override
    public int getCount() {
        return hashMaps.size();
    }

    @Override
    public Object getItem(int position) {
        return hashMaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.item_travel_place,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        HashMap<String, String> HashMap1 = hashMaps.get(position);
        viewHolder.travel_place_tv1.setText(HashMap1.get("attraction_trips_count")+" 篇游记");
        viewHolder.travel_place_tv2.setText(HashMap1.get("name"));
        viewHolder.travel_place_tv3.setText(HashMap1.get("description"));
        String user_score = HashMap1.get("user_score");
        if (!TextUtils.isEmpty(user_score)) {
            double score = Double.parseDouble(user_score);
            double i = score;
            viewHolder.travel_place_iv2.setRating((float) i);
        }else {
            viewHolder.travel_place_iv2.setProgress(0);
        }


        BitmapHelper.getBitmapUtils().display(viewHolder.travel_place_iv,HashMap1.get("image_url"));

        return convertView;
    }
    
    class ViewHolder{
       private ImageView travel_place_iv;
        private RatingBar travel_place_iv2;
        private TextView travel_place_tv1,travel_place_tv2,travel_place_tv3;
        public ViewHolder(View itemView){
            travel_place_iv= (ImageView) itemView.findViewById(R.id.item_travel_place_iv);
            travel_place_iv2= (RatingBar) itemView.findViewById(R.id.ratinbar);
            travel_place_tv1= (TextView) itemView.findViewById(R.id.item_travel_place_tv1);
            travel_place_tv2= (TextView) itemView.findViewById(R.id.item_travel_place_tv2);
            travel_place_tv3= (TextView) itemView.findViewById(R.id.item_travel_place_tv3);
        }
    }


}
