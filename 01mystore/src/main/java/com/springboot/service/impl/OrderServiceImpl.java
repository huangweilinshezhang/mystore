package com.springboot.service.impl;

import com.springboot.mapper.CateMapper;
import com.springboot.mapper.OrderMapper;
import com.springboot.model.Cate;
import com.springboot.model.Order;
import com.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    private Order order;

    @Override
    public int insertOrder(int userId, Integer productId, Integer productNumber, String format, int expressId, int total, String evaluate, int orderState) {
        return orderMapper.insertOrder(userId,productId,productNumber,format,expressId,total,evaluate,orderState);
    }
}
