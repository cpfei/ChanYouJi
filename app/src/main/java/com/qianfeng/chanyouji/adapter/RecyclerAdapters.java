package com.qianfeng.chanyouji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.chanyouji.R;
import com.qianfeng.chanyouji.beans.ImageUrls;
import com.qianfeng.chanyouji.netutils.BitmapHelper;

import java.util.List;

/**
 * Created by aaa on 15-5-5.
 */
public class RecyclerAdapters extends RecyclerView.Adapter<RecyclerAdapters.ViewHodler> {
    private List<ImageUrls> imageViews;
    private Context context;

    public RecyclerAdapters(List<ImageUrls> imageViews, Context context) {
        this.imageViews = imageViews;
        this.context = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, null);

        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler viewHodler, int i) {
        ImageUrls imageUrls = imageViews.get(i);
        viewHodler.conten_tv.setText(imageUrls.getText_content());
        BitmapHelper.getBitmapUtils().display(viewHodler.content_iv,imageUrls.getPhoto_Url());
    }

    @Override
    public int getItemCount() {
        return imageViews.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private ImageView content_iv;
        private TextView conten_tv;

        public ViewHodler(View itemView) {
            super(itemView);
            content_iv= (ImageView) itemView.findViewById(R.id.item_recyclervew_iv);
            conten_tv= (TextView) itemView.findViewById(R.id.item_recyclervew_tv);

        }
    }

}
