package com.companyname.elegantbeauty.Model;

public class Rating {
    private String userPhone;
    private String productId;
    private float rateValue;
    private String comment;
    private String brandNo;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getRateValue() {
        return rateValue;
    }

    public void setRateValue(float rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Rating() {
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public Rating(String userPhone, String productId, float rateValue, String comment, String brandNo) {
        this.userPhone = userPhone;
        this.productId = productId;
        this.rateValue = rateValue;
        this.comment = comment;
        this.brandNo=brandNo;
    }
}
