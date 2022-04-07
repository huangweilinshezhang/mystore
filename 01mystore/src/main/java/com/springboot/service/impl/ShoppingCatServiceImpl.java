package com.springboot.service.impl;

import com.springboot.mapper.ShoppingCatMapper;
import com.springboot.model.ShoppingCat;
import com.springboot.service.ShoppingCatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCatServiceImpl implements ShoppingCatService {
    @Autowired

    private ShoppingCatMapper shoppingCatMapper;
    private ShoppingCat shoppingCat;
    @Override
    public int ShoppingCatAdd(int userId,int productId, int sellerId, int productNumber) {
        return shoppingCatMapper.ShoppingCatAdd(userId,productId,sellerId,productNumber);
    }

    @Override
    public List<ShoppingCat> selectUserId(int userId) {
        return shoppingCatMapper.selectSellerId(userId);
    }

    @Override
    public int deleteByPrimaryKey(int shoppingId) {
        return shoppingCatMapper.deleteByPrimaryKey(shoppingId);
    }

    @Override
    public void productNumberAdd(int userId, int productId, int productNumber,int shoppingId) {
         shoppingCatMapper.productNumberAdd(userId,productId,productNumber,shoppingId);
    }

    @Override
    public void productNumberSub(int userId, int productId, int productNumber,int shoppingId) {
         shoppingCatMapper.productNumberSub(userId,productId,productNumber,shoppingId);
    }

    @Override
    public void updataByPrimaryKey(Integer userId, int orderState) {
        shoppingCatMapper.updataByPrimaryKey(userId,orderState);
    }
}
