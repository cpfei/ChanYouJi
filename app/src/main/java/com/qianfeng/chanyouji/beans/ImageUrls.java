package com.qianfeng.chanyouji.beans;

/**
 * Created by aaa on 15-5-5.
 */
public class ImageUrls {
    private String photo_Url;
    private String text_content;

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getPhoto_Url() {
        return photo_Url;
    }

    public void setPhoto_Url(String photo_Url) {
        this.photo_Url = photo_Url;
    }

    @Override
    public String toString() {
        return "ImageUrls{" +
                "photo_Url='" + photo_Url + '\'' +
                ", text_content='" + text_content + '\'' +
                '}';
    }
}
