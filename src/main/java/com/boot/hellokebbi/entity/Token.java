package com.boot.hellokebbi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.entity
 * @ClassName: Token
 * @Description: This is Token class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 1:54
 */

/**
 * 通過lombok自動生成構造器、get、set、toString方法
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private int id;
    private String type;
    private String username;
    private String password;
    private String role;
    private Date add_time;
    private Date expire_time;
}
