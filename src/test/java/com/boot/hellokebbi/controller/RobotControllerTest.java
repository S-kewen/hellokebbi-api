package com.boot.hellokebbi.controller;

import com.boot.hellokebbi.entity.Robot;
import com.boot.hellokebbi.service.RobotService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class RobotControllerTest {
    @Autowired
    private RobotService robotService;
    private Robot robot = new Robot();

    @Before
    public void init() {
        robot.setId(1);
        robot.setRegion(1000);
    }

    @Test
    public void getRobotList() {
        List<Map<String, Object>> list = robotService.getList(robot);
        assertNotEquals(list, null);
    }

    @Test
    public void getRobotInfo() {
        robot = robotService.getById(robot);
        assertNotEquals(robot, null);
    }
}