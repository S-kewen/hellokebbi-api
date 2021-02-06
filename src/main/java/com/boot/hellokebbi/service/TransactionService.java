package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: TransactionService
 * @Description: This is TransactionService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-21 14:16
 */
public interface TransactionService {
    List<Map<String, Object>> getList(Transaction transaction);

    Transaction getInfo(Transaction transaction);

    int deleteOne(Transaction transaction);
}
