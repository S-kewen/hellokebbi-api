package com.boot.hellokebbi.dao;

import com.boot.hellokebbi.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @PackageName: com.boot.hellokebbi.dao
 * @ClassName: UserMapper
 * @Description: This is UserMapper interface by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:01
 */
@Mapper
public interface UserMapper {
    User getById(User user);

    User getByUsername(User user);

    User getByUsernamePassword(User user);

    int updateOne(User user);

    int insertOne(User user);

    int getCount(User user);
}