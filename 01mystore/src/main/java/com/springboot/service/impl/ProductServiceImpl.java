package com.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.mapper.ProductMapper;
import com.springboot.model.Product;
import com.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    private Product product;
    @Override
    public List<Product> getAllProduct(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return productMapper.getAllProduct();
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        return  productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void productAdd(String productName,Integer productCid,Integer productFid,
                           String productBiaoqian,String productDetail,Integer productTupian,Integer productNumber,
                           Integer productPrice,String productAddress,String productKuaidi,Integer sellerId) {
        productMapper.productAdd(productName, productCid, productFid, productBiaoqian, productDetail, productTupian, productNumber, productPrice, productAddress, productKuaidi, sellerId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return  productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKeySelective(Integer productId, String productName, Integer productCid, Integer productFid, String productBiaoqian,
                                            String productDetail, Integer productNumber, Integer productPrice, String productAddress, String productKuaidi, Integer sellerId) {
        productMapper.updateByPrimaryKeySelective(productId, productName, productCid, productFid, productBiaoqian, productDetail,
                productNumber, productPrice, productAddress, productKuaidi, sellerId);
    }

    @Override
    public List selectFid() {
        return  productMapper.selectFid();
    }

    @Override
    public List selectCid() {
        return productMapper.selectCid();
    }

    @Override
    public int selectProductIdBysellerAndProductTuPian(Integer sellerId, int productTupian) {
        return productMapper.selectProductIdBysellerAndProductTuPian(sellerId,productTupian);
    }

    @Override
    public void updateTupianByProId(int yuliuId) {
        productMapper.updateTupianByProId(yuliuId);
    }


}
