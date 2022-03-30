package com.springboot.service;


import com.springboot.model.ShoppingCat;

import java.util.List;

public interface ShoppingCatService {

     int ShoppingCatAdd(int userId,int productId, int sellerId, int productNumber);

    List<ShoppingCat> selectUserId(int userId);

    int deleteByPrimaryKey(int shoppingId);
}
