package com.boot.hellokebbi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: Order
 * @Description: This is Order class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:54
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@Data
public class Order {
    private int id;
    private int uid;
    private int rid;
    private int state;
    private int amount;
    private String address;
    private Date pickup_time;
    private Date expire_time;
    private Date add_time;
}
