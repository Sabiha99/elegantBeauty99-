package com.companyname.elegantbeauty.Model;

import java.util.List;

public class Confirm {
    private String phone;
    private String name;
    private  String address;
   private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Confirm(String phone, String name, String address, String total, List<Order> products) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.products = products;
       this.status="0";

    }

    private String total;
    private List<Order> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getProducts() {
        return products;
    }

    public void setProducts(List<Order> products) {
        this.products = products;
    }



    public Confirm() {
    }


}
