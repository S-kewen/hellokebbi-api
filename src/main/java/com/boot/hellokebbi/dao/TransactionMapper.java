package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.Transaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: TransactionMapper
 * @Description: This is TransactionMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-21 14:15
 */
@Mapper
public interface TransactionMapper {
    List<Map<String, Object>> getList(Transaction Transaction);

    Transaction getInfo(Transaction Transaction);

    int deleteOne(Transaction Transaction);
}
