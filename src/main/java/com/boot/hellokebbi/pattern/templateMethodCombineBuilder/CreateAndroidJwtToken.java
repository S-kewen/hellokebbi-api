package com.boot.hellokebbi.pattern.templateMethodCombineBuilder;

import cn.hutool.crypto.SecureUtil;
import com.boot.hellokebbi.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.pattern.templateMethodCombineBuilder
 * @ClassName: CreateAndroidJwtToken
 * @Description: This is CreateAndroidToken class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-24 20:49
 */

public class CreateAndroidJwtToken extends CreateToken {
    @Override
    public void setUserInfo(User user) {
        //builder part
        super.jwtBuilder
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .claim("role", "user")
                .claim("type", "android")
                .claim("cipher", SecureUtil.md5(user.getPassword()));
    }

    @Override
    public void encrypt() {
        //builder part
        super.jwtBuilder
                .setExpiration(new Date(System.currentTimeMillis() + 2592000000L))
                .signWith(SignatureAlgorithm.HS256, "irga7hsue2z3kqetfue4xgwk5p4dzcyu");
    }
}
