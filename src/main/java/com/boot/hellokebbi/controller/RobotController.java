package com.boot.hellokebbi.controller;

import com.boot.hellokebbi.annotation.LoginToken;
import com.boot.hellokebbi.entity.Robot;
import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.controller
 * @ClassName: RobotController
 * @Description: This is RobotController class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:23
 */
@RestController
@RequestMapping("/v1/api/robot")
public class RobotController {

    @LoginToken
    @RequestMapping("getRobotList")
    public Result getRobotList(@RequestHeader("Authorization") String authorization, @RequestParam(required = false, defaultValue = "0") int state, @RequestParam(required = false, defaultValue = "0") int region) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Robot robot = new Robot();
            robot.setState(state);
            robot.setRegion(region);
            PageHelper.startPage(1, 10000);
            List<Map<String, Object>> select = new ServiceFacade().robotServiceGetList(robot);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(select);
            return new Result(StatusCode.OK, StatusMsg.OK, pageInfo.getList());
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getRobotInfo")
    public Result getRobotInfo(@RequestHeader("Authorization") String authorization, @RequestParam int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Robot robot = new Robot();
            robot.setId(id);
            robot = new ServiceFacade().robotServiceGetById(robot);
            if (robot != null) {
                return new Result(StatusCode.OK, StatusMsg.OK, robot);
            } else {
                return new Result(StatusCode.GETERROR, StatusMsg.GETERROR);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }
}
