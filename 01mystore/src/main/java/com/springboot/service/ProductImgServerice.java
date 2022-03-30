package com.springboot.service;

import com.springboot.model.ProductImage;

import java.util.List;

public interface ProductImgServerice {
    //根据商家id，文件名，产品id添加产品数据表
    int productImgAdd(int sellerId, int productId, String fileName);

    //根据productId查询产品图片表中的图片信息
    List<ProductImage> selectImgByPrimaryKey(int productId, int sellerId);

    int deleteByPrimaryKey(int productId);

    int updateBySellerAndProductId(Integer sellerId, Integer productId, String imgNumber,Integer imgId);

}
