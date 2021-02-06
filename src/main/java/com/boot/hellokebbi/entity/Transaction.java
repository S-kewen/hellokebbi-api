package com.boot.hellokebbi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: Transaction
 * @Description: This is Transaction class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-21 14:12
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@Data
public class Transaction {
    private int id;
    private int uid;
    private int state;
    private int type;
    private int amount;
    private String title;
    private String msg;
    private Date add_time;

}
