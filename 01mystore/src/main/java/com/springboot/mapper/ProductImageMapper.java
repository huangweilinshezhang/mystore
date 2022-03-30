package com.springboot.mapper;

import com.springboot.model.ProductImage;

import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Integer imgId);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByPrimaryKey(Integer productId, Integer sellerId);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);

    int productImgAdd(int sellerId, int productId, String fileName);

    int updateBySellerAndProductId(Integer sellerId, Integer productId, String imgNumber,Integer imgId);
}