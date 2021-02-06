package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.VerifyRecordMapper;
import com.boot.hellokebbi.entity.VerifyRecord;
import com.boot.hellokebbi.service.VerifyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: VerifyRecordServiceImpl
 * @Description: This is VerifyRecordServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */
@Component
public class VerifyRecordServiceImpl implements VerifyRecordService {
    @Autowired
    private VerifyRecordMapper mapper;

    @Override
    public VerifyRecord getById(VerifyRecord verifyRecord) {
        return mapper.getById(verifyRecord);
    }

    @Override
    public int updateOne(VerifyRecord verifyRecord) {
        return mapper.updateOne(verifyRecord);
    }

    @Override
    public int insertOne(VerifyRecord verifyRecord) {
        return mapper.insertOne(verifyRecord);
    }

    @Override
    public int updateByCode(VerifyRecord verifyRecord) {
        return mapper.updateByCode(verifyRecord);
    }

}
