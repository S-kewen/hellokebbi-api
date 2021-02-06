package com.boot.hellokebbi.entity;

import lombok.Data;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: Card
 * @Description: This is Card class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:44
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@Data
public class Card {
    private int id;
    private int uid;
    private String name;
    private int state;
    private String card_num;
    private String good_thru;
    private String password;
    private Date add_time;
}
