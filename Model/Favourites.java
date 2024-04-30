package com.companyname.elegantbeauty.Model;

public class Favourites {
    private String BrandId;
    private String UserPhone;
    private String ProductName;
    private String ProductImage;
    private String ProductDescription;
    private String ProductPrice;
    private String ProductDiscount;
    private String ProductBrandNo;

    public String getBrandId() {
        return BrandId;
    }

    public void setBrandId(String brandId) {
        BrandId = brandId;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        ProductDiscount = productDiscount;
    }

    public String getProductBrandNo() {
        return ProductBrandNo;
    }

    public void setProductBrandNo(String productBrandNo) {
        ProductBrandNo = productBrandNo;
    }

    public Favourites() {
    }

    public Favourites(String brandId, String userPhone, String productName, String productImage, String productDescription, String productPrice, String productDiscount, String productBrandNo) {
        BrandId = brandId;
        UserPhone = userPhone;
        ProductName = productName;
        ProductImage = productImage;
        ProductDescription = productDescription;
        ProductPrice = productPrice;
        ProductDiscount = productDiscount;
        ProductBrandNo = productBrandNo;
    }


}



