package com.spectraapps.aakarat.model;

/**
 * Created by MahmoudAyman on 04/06/2018.
 */
public class HomeModel {
    private String name;
   private int image;

    public HomeModel(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public HomeModel(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
