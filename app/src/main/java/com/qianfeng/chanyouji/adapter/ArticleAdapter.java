package com.qianfeng.chanyouji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.chanyouji.R;
import com.qianfeng.chanyouji.SubjectDetails;
import com.qianfeng.chanyouji.beans.Articles;
import com.qianfeng.chanyouji.netutils.BitmapHelper;

import java.util.List;

/**
 * Created by aaa on 15-5-2.
 */
public class ArticleAdapter extends BaseAdapter {
    private Context context;
    private List<Articles> list;

    public ArticleAdapter(Context context, List<Articles> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
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
        Articles articles = list.get(position);
        convertView=LayoutInflater.from(context).inflate(R.layout.item_articles,null);
        TextView text_Name = (TextView) convertView.findViewById(R.id.articles_name);
        text_Name.setText(articles.getName());

        TextView text_Title = (TextView) convertView.findViewById(R.id.articles_texttitle);
        text_Title.setText(articles.getTitle());
        final String id = articles.getId();

        ImageView image_Image = (ImageView) convertView.findViewById(R.id.articles_imageview);
        BitmapHelper.getBitmapUtils().display(image_Image,articles.getImage_url());
        image_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubjectDetails.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
