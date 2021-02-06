package com.boot.hellokebbi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: Robot
 * @Description: This is Robot class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:52
 */
/*通過lombok自動生成構造器、get、set、toString方法*/
@Data
public class Robot {
    private int id;
    private String nickname;
    private int state;
    private int region;
    private String msg;
    private String remark;
    private Date add_time;
}
