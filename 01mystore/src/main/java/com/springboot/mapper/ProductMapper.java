package com.springboot.mapper;

import com.springboot.model.Product;
import java.util.List;

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

    void updateByPrimaryKeySelective(Integer productId, String productName, Integer productCid, Integer productFid, String productBiaoqian, String productDetail, Integer productNumber, Integer productPrice, String productAddress, String productKuaidi, Integer sellerId);


    List selectFid();

    List selectCid();

    int selectProductIdBysellerAndProductTuPian(Integer sellerId, int productTupian);

//    int productImgAdd(int sellerId, String fileName, int productId);

    void updateTupianByProId(int productId);

//    ProductImg selectImgByPrimaryKey(int productId);
}