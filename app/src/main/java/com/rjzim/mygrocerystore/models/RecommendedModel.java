package com.rjzim.mygrocerystore.models;

public class RecommendedModel {

    String name;
    String description;
    String ratting;
    String img_url;
    int price;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String description, String ratting, String img_url, int price) {
        this.name = name;
        this.description = description;
        this.ratting = ratting;
        this.img_url = img_url;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
