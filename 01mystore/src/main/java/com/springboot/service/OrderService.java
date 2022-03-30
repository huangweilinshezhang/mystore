package com.springboot.service;

import org.springframework.stereotype.Service;


public interface OrderService {
    int insertOrder(int userId, Integer productId, Integer productNumber, String format, int expressId, int total, String evaluate, int orderState);

}
