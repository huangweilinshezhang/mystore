package com.springboot.mapper;

import com.springboot.model.Cate;
import com.springboot.model.Product;
import com.springboot.model.User;

import java.util.List;
//import com.springboot.model.ProductKey;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

//     getAllProduct();
    List<Product> getAllProduct();

    void productAdd(String productName,Integer productCid,Integer productFid,
                    String productBiaoqian,String productDetail,Integer productTupian,Integer productNumber,
                    Integer productPrice,String productAddress,String productKuaidi,Integer sellerId);

    void updateByPrimaryKeySelective(Integer productId, String productName, Integer productCid, Integer productFid, String productBiaoqian, String productDetail, Integer productTupian, Integer productNumber, Integer productPrice, String productAddress, String productKuaidi, Integer sellerId);


    List selectFid();

    List selectCid();
}