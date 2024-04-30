package com.companyname.elegantbeauty;

public class StaticRVModelClass {
    private int image;
    private  String text;

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public StaticRVModelClass(int image, String text) {
        this.image = image;
        this.text=text;
    }
}
