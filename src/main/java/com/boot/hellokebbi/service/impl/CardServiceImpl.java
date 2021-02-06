package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.CardMapper;
import com.boot.hellokebbi.entity.Card;
import com.boot.hellokebbi.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: CardServiceImpl
 * @Description: This is CardServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */
@Component
public class CardServiceImpl implements CardService {
    @Autowired
    private CardMapper mapper;


    @Override
    public int insertOne(Card card) {
        return mapper.insertOne(card);
    }

    @Override
    public List<Map<String, Object>> getList(Card card) {
        return mapper.getList(card);
    }


    @Override
    public Card getById(Card card) {
        return mapper.getById(card);
    }


    @Override
    public int getCount(Card card) {
        return mapper.getCount(card);
    }

    @Override
    public int deleteOne(Card card) {
        return mapper.deleteOne(card);
    }

    @Override
    public int updateOne(Card card) {
        return mapper.updateOne(card);
    }


}
