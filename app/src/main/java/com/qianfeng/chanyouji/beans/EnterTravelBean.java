package com.qianfeng.chanyouji.beans;

import java.util.List;

/**
 * Created by aaa on 15-5-5.
 */
public class EnterTravelBean {

    private int tag;
    private String tips_html,id,tab_name,display_count,description,start_date,user_name,trip_name,photos_count,days,front_cover_photo_url,user_image;
    private List<ImageUrls> imageViews;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTips_html() {
        return tips_html;
    }

    public void setTips_html(String tips_html) {
        this.tips_html = tips_html;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTab_name() {
        return tab_name;
    }

    public void setTab_name(String tab_name) {
        this.tab_name = tab_name;
    }

    public String getDisplay_count() {
        return display_count;
    }

    public void setDisplay_count(String display_count) {
        this.display_count = display_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<ImageUrls> getImageViews() {
        return imageViews;
    }

    public void setImageViews(List<ImageUrls> imageViews) {
        this.imageViews = imageViews;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getPhotos_count() {
        return photos_count;
    }

    public void setPhotos_count(String photos_count) {
        this.photos_count = photos_count;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getFront_cover_photo_url() {
        return front_cover_photo_url;
    }

    public void setFront_cover_photo_url(String front_cover_photo_url) {
        this.front_cover_photo_url = front_cover_photo_url;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    @Override
    public String toString() {
        return "EnterTravelBean{" +
                "tag=" + tag +
                ", tips_html='" + tips_html + '\'' +
                ", id='" + id + '\'' +
                ", tab_name='" + tab_name + '\'' +
                ", display_count='" + display_count + '\'' +
                ", description='" + description + '\'' +
                ", start_date='" + start_date + '\'' +
                ", user_name='" + user_name + '\'' +
                ", trip_name='" + trip_name + '\'' +
                ", photos_count='" + photos_count + '\'' +
                ", days='" + days + '\'' +
                ", front_cover_photo_url='" + front_cover_photo_url + '\'' +
                ", user_image='" + user_image + '\'' +
                ", imageViews=" + imageViews +
                '}';
    }
}
