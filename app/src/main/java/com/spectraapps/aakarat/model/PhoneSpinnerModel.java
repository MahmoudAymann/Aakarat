package com.spectraapps.aakarat.model;

/**
 * Created by MahmoudAyman on 03/06/2018.
 */

public class PhoneSpinnerModel {
    private String text;
    private int imageId;

    public PhoneSpinnerModel(String text, int imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
