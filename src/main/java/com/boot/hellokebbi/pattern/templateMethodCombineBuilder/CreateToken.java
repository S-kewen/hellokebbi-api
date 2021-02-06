package com.boot.hellokebbi.pattern.templateMethodCombineBuilder;

import com.boot.hellokebbi.entity.User;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

/**
 * @PackageName: com.boot.hellokebbi.pattern.templateMethodCombineBuilder
 * @ClassName: CreateToken
 * @Description: This is CreateToken class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-24 20:14
 * @remark: 通過template method定義一個createToken的骨架，最後通過不同的子類重寫abstract method實現create多種token，便於維護和拓展
 */
public abstract class CreateToken {
    protected ClaimsMutator<JwtBuilder> jwtBuilder = null;

    public final String execute(User user) {
        createBuilder();
        setDefaultInfo();
        setUserInfo(user);
        encrypt();
        return compact();
    }

    public void createBuilder() {
        //builder part
        this.jwtBuilder = Jwts.builder();
    }

    public String compact() {
        //get result method
        return ((JwtBuilder) this.jwtBuilder).compact();
    }

    public void setDefaultInfo() {
        //builder part
        this.jwtBuilder.setIssuedAt(new Date());
    }

    abstract void setUserInfo(User user);

    abstract void encrypt();
}
