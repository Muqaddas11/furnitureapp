package com.example.furnitureapp.model;

public class DiscountedProducts {


    Integer id;
    Integer imageUrl;

    public DiscountedProducts(Integer id, int imageUrl) {

        this.id = id;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
