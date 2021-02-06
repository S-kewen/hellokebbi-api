package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.Card;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: CardService
 * @Description: This is CardService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:07
 */
public interface CardService {
    int insertOne(Card card);

    List<Map<String, Object>> getList(Card card);

    Card getById(Card card);

    int getCount(Card card);

    int deleteOne(Card card);

    int updateOne(Card card);

}
