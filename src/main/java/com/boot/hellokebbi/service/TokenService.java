package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.entity.User;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: TokenService
 * @Description: This is TokenService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:09
 */
public interface TokenService {
    String createToken(User user);

    Token parseToken(String tokenStr);
}
