package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: OrderService
 * @Description: This is OrderService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:11
 */
public interface OrderService {
    int insertOne(Order order);

    List<Map<String, Object>> getList(Order order);

    Order getInfo(Order order);

    int getCount(Order order);

    int deleteOne(Order order);

    int updateOne(Order order);
}
