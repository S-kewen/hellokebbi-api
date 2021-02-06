package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.entity.User;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.pattern.templateMethodCombineBuilder.CreateAndroidJwtToken;
import com.boot.hellokebbi.pattern.templateMethodCombineBuilder.CreateToken;
import com.boot.hellokebbi.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: TokenServiceImpl
 * @Description: This is TokenServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */

@Component
public class TokenServiceImpl implements TokenService {

    @Override
    public String createToken(User user) {
        CreateToken createAndroidJwtToken = new CreateAndroidJwtToken();
        return createAndroidJwtToken.execute(user);
    }

    @Override
    public Token parseToken(String tokenStr) {
        Claims claims = Jwts.parser()
                .setSigningKey("irga7hsue2z3kqetfue4xgwk5p4dzcyu")
                .parseClaimsJws(tokenStr)
                .getBody();
        User user = new User();
        user.setId(Integer.parseInt(claims.getId()));
        user = new ServiceFacade().userServiceGetById(user);
        if (user != null && user.getState() == 1) {
            Token token = new Token();
            token.setId(Integer.parseInt(claims.getId()));
            token.setType((String) claims.get("type"));
            token.setUsername(claims.getSubject());
            token.setPassword((String) claims.get("cipher"));
            token.setRole((String) claims.get("role"));
            token.setAdd_time(claims.getIssuedAt());
            token.setExpire_time(claims.getExpiration());
            return token;
        } else {
            return null;
        }
    }
}
