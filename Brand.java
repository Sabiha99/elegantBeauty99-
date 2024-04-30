package com.companyname.elegantbeauty.Model;

public class Brand {

    private String Name;
    private String Image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Brand(String image, String name) {
        Name=name;
        Image = image;
    }

    public Brand() {
    }
}
