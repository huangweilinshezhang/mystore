package com.springboot.service;
;
import com.springboot.model.Product;
//import com.springboot.model.ProductImg;

import java.util.List;

public interface ProductService {
    List<Product>  getAllProduct(Integer pageNum,Integer pageSize);

    Product selectByPrimaryKey(Integer id);

//    void productAdd();

//    void productAdd(String productName, int productCid, int productFid, String productBiaoqian, String productDetail, String productTupian, int productNumber, int productPrice, String productAddress, String productKuaidi, int sellerId);

    void productAdd(String productName, Integer productCid, Integer productFid,
                    String productBiaoqian, String productDetail, Integer productTupian, Integer productNumber,
                    Integer productPrice, String productAddress, String productKuaidi, Integer sellerId);

    int deleteByPrimaryKey(Integer id);

    void updateByPrimaryKeySelective(Integer productId, String productName, Integer productCid,
                                     Integer productFid, String productBiaoqian, String productDetail ,
                                     Integer productNumber, Integer productPrice, String productAddress, String productKuaidi, Integer sellerId);

    List selectFid();

    List selectCid();

    int selectProductIdBysellerAndProductTuPian(Integer sellerId, int productTupian);


    void updateTupianByProId(int yuliuId);

}
