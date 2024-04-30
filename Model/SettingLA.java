package com.companyname.elegantbeauty.Model;

public class SettingLA {
    private String Name,Image,Description,Price,Discount,BrandNo;

    public SettingLA(String name, String image, String description, String price, String discount, String brandNo) {
        Name = name;
        Image = image;
        Description = description;
        Price = price;
        Discount = discount;
        BrandNo = brandNo;
    }

    public SettingLA() {
    }

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getBrandNo() {
        return BrandNo;
    }

    public void setBrandNo(String brandNo) {
        BrandNo = brandNo;
    }
}
