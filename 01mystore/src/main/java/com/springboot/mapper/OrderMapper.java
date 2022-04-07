package com.springboot.mapper;

import com.springboot.model.Order;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int insertOrder(int userId, Integer productId, Integer productNumber, Date orderTime, int expressId, int total, String evaluate, int orderState);

    List<Order> getAllOrder(int pageNum, int pageSize);

    void delOrder(int orderId);
}