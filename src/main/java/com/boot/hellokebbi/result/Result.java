package com.boot.hellokebbi.result;

import lombok.Data;

/**
 * @PackageName: com.boot.hellokebbi.pattern.result
 * @ClassName: Result
 * @Description: This is Result class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 17:41
 */
@Data
public class Result {
    private Integer code;

    private String msg;

    private long timestamp = System.currentTimeMillis() / 1000;

    private Object data;


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
