package com.boot.hellokebbi.service;

import com.boot.hellokebbi.entity.User;

/**
 * @PackageName: com.boot.hellokebbi.service
 * @ClassName: UserService
 * @Description: This is UserService interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:09
 */
public interface UserService {
    User getById(User user);

    User getByUsername(User user);

    User getByUsernamePassword(User user);

    int updateOne(User user);

    int insertOne(User user);

    int getCount(User user);
}
