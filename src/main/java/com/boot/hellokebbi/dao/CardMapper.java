package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: CardMapper
 * @Description: This is CardMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:59
 */
@Mapper
public interface CardMapper {
    int insertOne(Card card);

    List<Map<String, Object>> getList(Card card);

    Card getById(Card card);

    int getCount(Card card);

    int deleteOne(Card card);

    int updateOne(Card card);
}
