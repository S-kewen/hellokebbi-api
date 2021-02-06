package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.OrderMapper;
import com.boot.hellokebbi.entity.Order;
import com.boot.hellokebbi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: OrderServiceImpl
 * @Description: This is OrderServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper mapper;


    @Override
    public int insertOne(Order order) {
        return mapper.insertOne(order);
    }

    @Override
    public List<Map<String, Object>> getList(Order order) {
        return mapper.getList(order);
    }


    @Override
    public Order getInfo(Order order) {
        return mapper.getInfo(order);
    }


    @Override
    public int getCount(Order order) {
        return mapper.getCount(order);
    }

    @Override
    public int deleteOne(Order order) {
        return mapper.deleteOne(order);
    }

    @Override
    public int updateOne(Order order) {
        return mapper.updateOne(order);
    }


}
