package com.spectraapps.aakarat.model;

/**
 * Created by MahmoudAyman on 04/06/2018.
 */
public class ProductsModel {
    private String type;
    private boolean fav;
    private String price;
    private String measure;
    private String country;
    private String city;

    public ProductsModel(String type, String price, String measure, String country, String city, boolean fav) {
        this.type = type;
        this.fav = fav;
        this.price = price;
        this.measure = measure;
        this.country = country;
        this.city = city;
    }

    public ProductsModel() {
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
