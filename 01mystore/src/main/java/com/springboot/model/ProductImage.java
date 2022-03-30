package com.springboot.model;

public class ProductImage {
    private Integer userId;
    private String  imgNumber;
    private Integer imgId;
    private Integer productId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImgNumber() {
        return imgNumber;
    }

    public void setImgNumber(String imgNumber) {
        this.imgNumber = imgNumber;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
}
