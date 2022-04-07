package com.springboot.mapper;

import com.springboot.model.ShoppingCat;

import java.util.List;

public interface ShoppingCatMapper {
    int ShoppingCatAdd(int userId,int productId, int sellerId, int productNumber);

    int deleteByPrimaryKey(Integer shoppingId);

    int insert(ShoppingCat record);

    int insertSelective(ShoppingCat record);

    ShoppingCat selectByPrimaryKey(Integer shoppingId);

    int updateByPrimaryKeySelective(ShoppingCat record);

    int updateByPrimaryKey(ShoppingCat record);

    List<ShoppingCat> selectSellerId(int userId);

    void productNumberAdd(int userId, int productId, int productNumber,int shoppingId);

    void productNumberSub(int userId, int productId, int productNumber,int shoppingId);

    void updataByPrimaryKey(Integer userId, int orderState);
}