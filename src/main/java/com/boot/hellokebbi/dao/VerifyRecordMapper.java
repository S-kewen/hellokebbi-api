package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.VerifyRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: VerifyRecordMapper
 * @Description: This is VerifyRecordMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:01
 */
@Mapper
public interface VerifyRecordMapper {
    VerifyRecord getById(VerifyRecord verifyRecord);

    int updateOne(VerifyRecord verifyRecord);

    int insertOne(VerifyRecord verifyRecord);

    int updateByCode(VerifyRecord verifyRecord);
}