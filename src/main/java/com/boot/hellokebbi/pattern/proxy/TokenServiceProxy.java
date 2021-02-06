package com.boot.hellokebbi.pattern.proxy;

import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.entity.User;
import com.boot.hellokebbi.service.TokenService;
import com.boot.hellokebbi.service.impl.TokenServiceImpl;

/**
 * @PackageName: com.boot.hellokebbi.pattern.proxy
 * @ClassName: TokenServiceProxy
 * @Description: This is TokenServiceProxy class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-24 3:12
 * @remark: 通過protection proxy加強對tokenService的控制和監控，增強了拓展性
 */
public class TokenServiceProxy implements TokenService {

    @Override
    public String createToken(User user) {
        if (user != null && user.getId() > 0 && user.getState() == 1 && user.getUsername().length() > 0 && user.getPassword().length() > 0) {
            //監控日誌
            System.out.println("===========create token start===========");
            System.out.println(user.toString());
            System.out.println("===========create token end===========");
            return new TokenServiceImpl().createToken(user);
        } else {
            return null;
        }

    }

    @Override
    public Token parseToken(String tokenStr) {
        //加強控制
        if (tokenStr == null || "".equals(tokenStr)) {
            return null;
        }
        return new TokenServiceImpl().parseToken(tokenStr);
    }
}
