package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.RobotMapper;
import com.boot.hellokebbi.entity.Robot;
import com.boot.hellokebbi.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: RobotServiceImpl
 * @Description: This is RobotServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */
@Component
public class RobotServiceImpl implements RobotService {
    @Autowired
    private RobotMapper mapper;


    @Override
    public List<Map<String, Object>> getList(Robot robot) {
        return mapper.getList(robot);
    }

    @Override
    public Robot getById(Robot robot) {
        return mapper.getById(robot);
    }


    @Override
    public int getCount(Robot robot) {
        return mapper.getCount(robot);
    }


}
