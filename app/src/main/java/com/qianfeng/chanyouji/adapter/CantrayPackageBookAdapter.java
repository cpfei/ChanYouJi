package com.qianfeng.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.qianfeng.chanyouji.ArticlesActivity;
import com.qianfeng.chanyouji.Enter_LvxingDi;
import com.qianfeng.chanyouji.PlansActivity;

import com.qianfeng.chanyouji.R;
import com.qianfeng.chanyouji.TripsActivity;
import com.qianfeng.chanyouji.beans.Entry_Destination;
import com.qianfeng.chanyouji.netutils.BitmapHelper;

import java.util.List;

/**
 * Created by aaa on 15-5-2.
 */
public class CantrayPackageBookAdapter extends BaseAdapter {
    private Context context;
    private List<Entry_Destination> list;
    private RadioGroup rg;

    public CantrayPackageBookAdapter(Context context, List<Entry_Destination> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        Log.d("getCount==",list.toString());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Entry_Destination entry_destination = list.get(position);
        convertView= LayoutInflater.from(context).inflate(R.layout.item_cantrybook,null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.cantr_image);
        TextView te = (TextView) convertView.findViewById(R.id.cantr_name);
        if (position==0) {
            te.setText(entry_destination.getName_Zh_Cn()+"概览 "+entry_destination.getName_En());
        }else {
            te.setText(entry_destination.getName_Zh_Cn()+" "+entry_destination.getName_En());

        }

        if(!entry_destination.getImage_Url().equals("")) {
            BitmapHelper.getBitmapUtils().display(imageView, entry_destination.getImage_Url());
        }

        ((RadioButton) convertView.findViewById(R.id.cantry_rb1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ArticlesActivity.class);
                intent.putExtra("name_Zh_Cn",entry_destination.getName_Zh_Cn()+"专题");
                intent.putExtra("id",entry_destination.getId());
                context.startActivity(intent);
            }
        });

        ((RadioButton) convertView.findViewById(R.id.cantry_rb2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlansActivity.class);
                intent.putExtra("name_Zh_Cn",entry_destination.getName_Zh_Cn()+"行程");
                intent.putExtra("id",entry_destination.getId());
                context.startActivity(intent);
            }
        });

        ((RadioButton) convertView.findViewById(R.id.cantry_rb4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TripsActivity.class);
                intent.putExtra("name_Zh_Cn",entry_destination.getName_Zh_Cn()+"游记");
                intent.putExtra("id",entry_destination.getId());
                context.startActivity(intent);
            }
        });

        //点击旅行地

        ((RadioButton) convertView.findViewById(R.id.cantry_rb3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Enter_LvxingDi.class);
                intent.putExtra("name_Zh_Cn",entry_destination.getName_Zh_Cn()+"旅行地");
                intent.putExtra("id",entry_destination.getId());
                context.startActivity(intent);
            }
        });


        return convertView;
    }





}
