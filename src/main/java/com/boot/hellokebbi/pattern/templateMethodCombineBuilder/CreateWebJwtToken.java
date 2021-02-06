package com.boot.hellokebbi.pattern.templateMethodCombineBuilder;

import cn.hutool.crypto.SecureUtil;
import com.boot.hellokebbi.entity.User;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.pattern.templateMethodCombineBuilder
 * @ClassName: CreateWebJwtToken
 * @Description: This is CreateWebJwtToken class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-24 20:49
 */

public class CreateWebJwtToken extends CreateToken {
    @Override
    void setUserInfo(User user) {
        //builder part
        super.jwtBuilder
                .setId(String.valueOf(user.getId()))
                .setSubject(user.getUsername())
                .claim("role", "user")
                .claim("type", "web")
                .claim("cipher", SecureUtil.md5(user.getPassword()));
    }

    @Override
    void encrypt() {
        //builder part
        super.jwtBuilder
                .setExpiration(new Date(System.currentTimeMillis() + 7200000L))
                .signWith(SignatureAlgorithm.HS256, "uLmvu9CCQeBACPp3XrWy0y0ik6mqirAq");
    }
}
