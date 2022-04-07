package com.springboot.service;

import com.springboot.model.Order;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


public interface OrderService {
    int insertOrder(int userId, Integer productId, Integer productNumber, Date orderTime, int expressId, int total, String evaluate, int orderState);

    List<Order> getAllOrder(int pageNum, int pageSize);

    void delOrder(int orderId);
}
