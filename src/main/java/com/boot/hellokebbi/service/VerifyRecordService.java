package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.VerifyRecord;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: VerifyRecordService
 * @Description: This is VerifyRecordService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:09
 */
public interface VerifyRecordService {
    VerifyRecord getById(VerifyRecord verifyRecord);

    int updateOne(VerifyRecord verifyRecord);

    int insertOne(VerifyRecord verifyRecord);

    int updateByCode(VerifyRecord verifyRecord);
}
