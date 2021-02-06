package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: OrderMapper
 * @Description: This is OrderMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:01
 */
@Mapper
public interface OrderMapper {
    int insertOne(Order order);

    List<Map<String, Object>> getList(Order order);

    Order getInfo(Order order);

    int getCount(Order order);

    int deleteOne(Order order);

    int updateOne(Order order);
}
