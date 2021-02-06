package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.TransactionMapper;
import com.boot.hellokebbi.entity.Transaction;
import com.boot.hellokebbi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: TransactionServiceImpl
 * @Description: This is TransactionServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-21 14:16
 */
@Component
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper mapper;

    @Override
    public List<Map<String, Object>> getList(Transaction transaction) {
        return mapper.getList(transaction);
    }

    @Override
    public Transaction getInfo(Transaction transaction) {
        return mapper.getInfo(transaction);
    }

    @Override
    public int deleteOne(Transaction transaction) {
        return mapper.deleteOne(transaction);
    }
}
