package com.springboot.model;

public class Product{
    private int productId;
    private String productName;

    private Integer productCid;

    private Integer productFid;

    private String productBiaoqian;

    private String productDetail;

    private Integer productTupian;

    private Integer productNumber;

    private Integer productPrice;

    private String productAddress;

    private String productKuaidi;
    private int sellerId;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCid() {
        return productCid;
    }

    public void setProductCid(Integer productCid) {
        this.productCid = productCid;
    }

    public Integer getProductFid() {
        return productFid;
    }

    public void setProductFid(Integer productFid) {
        this.productFid = productFid;
    }

    public String getProductBiaoqian() {
        return productBiaoqian;
    }

    public void setProductBiaoqian(String productBiaoqian) {
        this.productBiaoqian = productBiaoqian;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Integer getProductTupian() {
        return productTupian;
    }

    public void setProductTupian(Integer productTupian) {
        this.productTupian = productTupian;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getProductKuaidi() {
        return productKuaidi;
    }

    public void setProductKuaidi(String productKuaidi) {
        this.productKuaidi = productKuaidi;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}