package com.boot.hellokebbi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: User
 * @Description: This is User class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:54
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Cloneable {
    private int id;
    private String username;
    private String password;
    private int state;
    private String email;
    private String phone;
    private String id_card;
    private int point;
    private String remark;
    private Date add_time;


    @Override
    public User clone() throws CloneNotSupportedException {
        User result = null;
        try {
            result = (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
