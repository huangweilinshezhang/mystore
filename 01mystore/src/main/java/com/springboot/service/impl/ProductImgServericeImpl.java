package com.springboot.service.impl;

import com.springboot.mapper.ProductImageMapper;
import com.springboot.model.ProductImage;
import com.springboot.service.ProductImgServerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImgServericeImpl implements ProductImgServerice {

    @Autowired
    private ProductImageMapper productImageMapper;
    private ProductImage productImage;
    @Override
    public int productImgAdd(int sellerId, int productId, String fileName) {
        return productImageMapper.productImgAdd(sellerId,productId,fileName);
    }

    @Override
    public List<ProductImage> selectImgByPrimaryKey(int productId, int sellerId) {
        return productImageMapper.selectByPrimaryKey(productId,sellerId);
    }

    @Override
    public int deleteByPrimaryKey(int productId) {
        return productImageMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public int updateBySellerAndProductId(Integer sellerId, Integer productId, String imgNumber,Integer imgId) {
        return productImageMapper.updateBySellerAndProductId(sellerId,productId,imgNumber,imgId);
    }
}
