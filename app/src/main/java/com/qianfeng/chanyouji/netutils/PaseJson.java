package com.qianfeng.chanyouji.netutils;

import android.util.Log;

import com.qianfeng.chanyouji.beans.Articles;
import com.qianfeng.chanyouji.beans.Destination;
import com.qianfeng.chanyouji.beans.DestinationsDatas;
import com.qianfeng.chanyouji.beans.Entry_Destination;
import com.qianfeng.chanyouji.beans.PlansData;
import com.qianfeng.chanyouji.beans.TripsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-4-30.
 */
public class PaseJson {

    public static List<DestinationsDatas> jsonToList(String json) {
        try {
            JSONArray array = new JSONArray(json);
            List<DestinationsDatas> list = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                DestinationsDatas destinationsDatas = new DestinationsDatas();
                JSONObject jsonObject = array.getJSONObject(i);
                JSONArray destinations = jsonObject.getJSONArray("destinations");
                List<Destination> list1 = new ArrayList<>();
                for (int j = 0; j < destinations.length(); j++) {
                    Destination destination = new Destination();
                    JSONObject jsonObject1 = destinations.getJSONObject(j);
                    destination.setId(jsonObject1.getString("id"));
                    destination.setImage_Url(jsonObject1.getString("image_url"));
                    destination.setName_En(jsonObject1.getString("name_en"));
                    destination.setName_Zh_Cn(jsonObject1.getString("name_zh_cn"));
                    destination.setPoi_Count(jsonObject1.getString("poi_count"));
                    list1.add(destination);
                }
                destinationsDatas.setDestinations(list1);
                list.add(destinationsDatas);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    //List<Entry_Destination>
    public static List<Entry_Destination> jsonToList2(String json) {
        try {
            JSONArray array = new JSONArray(json);
            ArrayList<Entry_Destination> entry_destinations = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Entry_Destination entry_destination = new Entry_Destination();
                JSONObject jsonObject = array.getJSONObject(i);
                entry_destination.setId(jsonObject.getString("id"));
                entry_destination.setImage_Url(jsonObject.getString("image_url"));
                entry_destination.setName_En(jsonObject.getString("name_en"));
                entry_destination.setName_Zh_Cn(jsonObject.getString("name_zh_cn"));

                entry_destinations.add(entry_destination);
            }
            return entry_destinations;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //List<Articles> list
    public static List<Articles> jsonToArticles(String s){
        try {
            JSONArray array = new JSONArray(s);
            ArrayList<Articles> articleses = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                Articles articles = new Articles();
                JSONObject jsonObject = array.getJSONObject(i);
                articles.setName(jsonObject.getString("name"));
                articles.setId(jsonObject.getString("id"));
                articles.setTitle(jsonObject.getString("title"));
                articles.setImage_url(jsonObject.getString("image_url"));
                articleses.add(articles);
            }
            return articleses;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<PlansData> jsonToPlansList(String json){
        try {
            JSONArray array = new JSONArray(json);
            ArrayList<PlansData> plansDatas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                PlansData plansData = new PlansData();
                plansData.setImage_url(jsonObject.getString("image_url"));
                plansData.setDescription(jsonObject.getString("description"));
                plansData.setId(jsonObject.getString("id"));
                plansData.setName(jsonObject.getString("name"));
                plansData.setPlan_days_count(jsonObject.getString("plan_days_count"));
                plansData.setPlan_nodes_count(jsonObject.getString("plan_nodes_count"));

                plansDatas.add(plansData);

            }
            return plansDatas;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<TripsData> jsonToTripsDataList(String json){
        try {
            JSONArray array = new JSONArray(json);
            ArrayList<TripsData> tripsDatas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                TripsData tripsData = new TripsData();
                tripsData.setName(jsonObject.getString("name"));
                tripsData.setDays(jsonObject.getString("days"));
                tripsData.setFront_Cover_Photo_Url(jsonObject.getString("front_cover_photo_url"));
                tripsData.setPhotos_Count(jsonObject.getString("photos_count"));
                tripsData.setStart_Date(jsonObject.getString("start_date"));
                JSONObject user = jsonObject.getJSONObject("user");
                tripsData.setImage(user.getString("image"));
                tripsDatas.add(tripsData);
            }
            return tripsDatas;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
