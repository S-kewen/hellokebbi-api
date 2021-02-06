package com.boot.hellokebbi.controller;

import com.boot.hellokebbi.entity.User;
import com.boot.hellokebbi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class UserControllerTest {
    @Autowired
    private UserService userService;
    private User user = new User();

    @Before
    public void init() {
        user.setId(2);
        user.setUsername("skwen");
        user.setPassword("bd5af3176dcb2de824d3f65b41c15f5f");
    }

    @Test
    public void login() {
        user = userService.getByUsernamePassword(user);
        assertNotEquals(user, null);
    }

    @Test
    public void getUserInfo() {
        user = userService.getById(user);
        assertNotEquals(user, null);
    }

}