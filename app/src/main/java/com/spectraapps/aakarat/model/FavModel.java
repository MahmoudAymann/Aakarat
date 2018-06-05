package com.spectraapps.aakarat.model;

/**
 * Created by MahmoudAyman on 05/06/2018.
 */
public class FavModel {
    private String name;
    private int image;

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

    public FavModel(String name, int image) {
        this.name = name;
        this.image = image;

    }
}
