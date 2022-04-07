package com.springboot.model;

public class UserAddress {
    private Integer addressId;

    private Integer userId;

    private String consignee;

    private String address;

    private Integer addressStaus;

    private String userTel;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddressStaus() {
        return addressStaus;
    }

    public void setAddressStaus(Integer addressStaus) {
        this.addressStaus = addressStaus;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}