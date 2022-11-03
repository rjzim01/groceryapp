package com.rjzim.mygrocerystore.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String name;
    String description;
    String ratting;
    String img_url;
    String type;
    int price;

    public ViewAllModel() {

    }

    public ViewAllModel(String name, String description, String ratting, String img_url, String type, int price) {
        this.name = name;
        this.description = description;
        this.ratting = ratting;
        this.img_url = img_url;
        this.type = type;
        this.price = price;
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

    public String getRatting() {
        return ratting;
    }

    public void setRatting(String ratting) {
        this.ratting = ratting;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
