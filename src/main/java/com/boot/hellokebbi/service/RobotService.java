package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.Robot;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: RobotService
 * @Description: This is RobotService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:09
 */
public interface RobotService {
    List<Map<String, Object>> getList(Robot robot);

    Robot getById(Robot robot);

    int getCount(Robot robot);
}
