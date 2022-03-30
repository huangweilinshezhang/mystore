package com.springboot.model;
public class Cate {
    private Integer cateId;
    private String cateName;
    private Integer cateParentId;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getCateParentId() {
        return cateParentId;
    }

    public void setCateParentId(Integer cateParentId) {
        this.cateParentId = cateParentId;
    }
}
