package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.Robot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: OrderRecordMapper
 * @Description: This is OrderRecordMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:01
 */
@Mapper
public interface RobotMapper {
    List<Map<String, Object>> getList(Robot robot);

    Robot getById(Robot robot);

    int getCount(Robot robot);
}
