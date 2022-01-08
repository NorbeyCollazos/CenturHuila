package com.ycjn.centurhuila.model;

/**
 * Created by Norbey on 18/04/2018.
 */

public class PostImages {
    private String images;
    private String descimages;

    public PostImages() {
    }

    public PostImages(String images, String descimages) {
        this.images = images;
        this.descimages = descimages;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescimages() {
        return descimages;
    }

    public void setDescimages(String descimages) {
        this.descimages = descimages;
    }
}
