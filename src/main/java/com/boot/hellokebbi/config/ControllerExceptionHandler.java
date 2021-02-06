package com.boot.hellokebbi.config;

import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @PackageName: com.boot.hellokebbi.config
 * @ClassName: ControllerExceptionHandler
 * @Description: This is ControllerExceptionHandler class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:01
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest request, Exception e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("query", request.getQueryString());
        map.put("method", request.getMethod());
        map.put("contextPath", request.getContextPath());
        map.put("errorMsg", e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            return new Result(StatusCode.NOTFOUND, StatusMsg.NOTFOUND, map);
        } else {
            return new Result(StatusCode.ERROR, StatusMsg.ERROR, map);
        }
    }
}