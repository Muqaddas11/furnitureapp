package com.example.furnitureapp.model;

import android.graphics.drawable.Drawable;

public class RecentlyViewed {

    String name;
    String description;
    String price;
    int imageUrl;
    int bigImageUrl;


    public RecentlyViewed(String name, String description, String price,  int imageUrl,int bigImageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.bigImageUrl= bigImageUrl;
    }

    public int getBigImageUrl() {
        return bigImageUrl;
    }

    public void setBigImageUrl(int bigImageUrl) {
        this.bigImageUrl = bigImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
