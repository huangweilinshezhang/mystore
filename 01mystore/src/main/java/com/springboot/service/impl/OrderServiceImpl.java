package com.springboot.service.impl;

import com.springboot.mapper.CateMapper;
import com.springboot.mapper.OrderMapper;
import com.springboot.model.Cate;
import com.springboot.model.Order;
import com.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    private Order order;

    @Override
    public int insertOrder(int userId, Integer productId, Integer productNumber, Date orderTime, int expressId, int total, String evaluate, int orderState) {
        return orderMapper.insertOrder(userId,productId,productNumber,orderTime,expressId,total,evaluate,orderState);
    }

    @Override
    public List<Order> getAllOrder(int pageNum, int pageSize) {
        return orderMapper.getAllOrder(pageNum,pageSize);
    }

    @Override
    public void delOrder(int orderId) {
        orderMapper.delOrder(orderId);
    }
}
