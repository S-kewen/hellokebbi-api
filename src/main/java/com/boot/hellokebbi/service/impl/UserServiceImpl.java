package com.boot.hellokebbi.service.impl;

import com.boot.hellokebbi.dao.UserMapper;
import com.boot.hellokebbi.entity.User;
import com.boot.hellokebbi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.boot.hellokebbi.service.impl
 * @ClassName: UserServiceImpl
 * @Description: This is UserServiceImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:04
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public User getById(User user) {
        return mapper.getById(user);
    }

    @Override
    public User getByUsername(User user) {
        return mapper.getByUsername(user);
    }

    @Override
    public User getByUsernamePassword(User user) {
        return mapper.getByUsernamePassword(user);
    }

    @Override
    public int updateOne(User user) {
        return mapper.updateOne(user);
    }

    @Override
    public int insertOne(User user) {
        return mapper.insertOne(user);
    }

    @Override
    public int getCount(User user) {
        return mapper.getCount(user);
    }
}
