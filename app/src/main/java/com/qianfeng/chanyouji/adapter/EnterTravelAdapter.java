package com.qianfeng.chanyouji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.views.PinnedSectionListView;
import com.qianfeng.chanyouji.R;
import com.qianfeng.chanyouji.beans.EnterTravelBean;
import com.qianfeng.chanyouji.beans.ImageUrls;
import com.qianfeng.chanyouji.netutils.BitmapHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-5-5.
 */
public class EnterTravelAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter{
    private List<EnterTravelBean> list;
    private Context context;
    private static int tag=0;
    private ImageView []image = new ImageView[7];
    public EnterTravelAdapter(List<EnterTravelBean> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public int getCount() {
   Log.d("getCount",list.toString());
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
        EnterTravelBean enterTravelBean = list.get(position);
        if (enterTravelBean.getTag()==0) {
            //标题栏
            if (convertView==null) {
                convertView= LayoutInflater.from(context).inflate(R.layout.item_pinnedsectionlistview1,null);
            }
            TextView tab_Name = (TextView) convertView.findViewById(R.id.item_pinnedsectionlistview1_tv);
            tab_Name.setText(enterTravelBean.getTab_name());
        }else {
            //第二种布局
            if (convertView==null) {
                convertView= LayoutInflater.from(context).inflate(R.layout.item_pinnedsectionlistview2,null);
            }
            TextView text_content = (TextView) convertView.findViewById(R.id.item_pinnedsectionlistview2_tv1);
            text_content.setText(enterTravelBean.getDescription());


            TextView below_left = (TextView) convertView.findViewById(R.id.item_pinnedsectionlistview2_tv2);
            below_left.setText(enterTravelBean.getUser_name());
            TextView below_text_right = (TextView) convertView.findViewById(R.id.item_pinnedsectionlistview2_tv3);
            below_text_right.setText(enterTravelBean.getStart_date());



            List<ImageUrls> imageViews = enterTravelBean.getImageViews();

            image[0] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv1));
            image[1] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv2));
            image[2] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv3));
            image[3] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv4));
            image[4] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv5));
            image[5] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv6));
            image[6] = ((ImageView) convertView.findViewById(R.id.horizontalscrollviewiv7));

            if (imageViews != null&&imageViews.size()!=0) {
                for (int i = 0; i < imageViews.size(); i++) {
                    BitmapHelper.getBitmapUtils().display(image[i],imageViews.get(i).getPhoto_Url());
                    tag=imageViews.size();
                }

                for (imageViews.size();tag<7 ;tag++) {
                    image[tag].setVisibility(View.GONE);
                }








//                ImageView test_image = (ImageView) convertView.findViewById(R.id.test_image);
//
//                BitmapHelper.getBitmapUtils().display(test_image,imageViews.get(0).getPhoto_Url());
//                设置水平滑动
//                LinearLayoutManager manager = new LinearLayoutManager(context);
//                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
//                recyclerView.setLayoutManager(manager);
//                //设置适配器
//                recyclerView.setAdapter(new RecyclerAdapters(imageViews,context));
            }


        }


        return convertView;
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTag()==0) {
            //sub_name
            return 0;
        }
        return 1;
    }

    @Override
    public boolean isItemViewTypePinned(int viewType) {

        if (viewType==0) {
            return true;
        }
        return false;
    }



}
