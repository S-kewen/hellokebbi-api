package com.boot.hellokebbi.entity;

import lombok.Data;

import java.util.Date;


/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: VerifyRecord
 * @Description: This is VerifyRecord class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:54
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@Data
public class VerifyRecord {
    private int id;
    private int uid;
    private String receiver;
    private int type;
    private int state;
    private String code;
    private Date expire_time;
    private Date add_time;
}
