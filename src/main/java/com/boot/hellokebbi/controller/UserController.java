package com.boot.hellokebbi.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.boot.hellokebbi.annotation.LoginToken;
import com.boot.hellokebbi.annotation.PassToken;
import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.entity.User;
import com.boot.hellokebbi.entity.VerifyRecord;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import com.boot.hellokebbi.util.YunCourierUtilImpl;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.controller
 * @ClassName: UserController
 * @Description: This is UserController class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:23
 */
@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    //use prototype
    private User newUser = new User(0, "", "", 1, "", "", "", 0, "", null);

    @PassToken
    @RequestMapping("login")
    public Result login(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user = new ServiceFacade().userServiceGetByUsernamePassword(user);
        if (user != null) {
            if (user.getState() == 1) {
                Map<String, Object> map = new HashMap<>();
                map.put("token", new ServiceFacade().tokenServiceCreateToken(user));
                return new Result(StatusCode.OK, StatusMsg.OK, map);
            } else {
                return new Result(StatusCode.USERSTATEEXCEPTION, StatusMsg.USERSTATEEXCEPTION);
            }
        } else {
            return new Result(StatusCode.LOGINERROR, StatusMsg.LOGINERROR);
        }
    }

    @LoginToken
    @RequestMapping("modifyInfo")
    public Result modifyInfo(@RequestHeader("Authorization") String authorization, String email, String phone, String idCard) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user.setEmail(email);
            user.setPhone(phone);
            user.setId_card(idCard);
            int count = new ServiceFacade().userServiceUpdateOne(user);
            if (count > 0) {
                return new Result(StatusCode.OK, StatusMsg.OK);
            } else {
                return new Result(StatusCode.MODIFYFAIL, StatusMsg.MODIFYFAIL);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("modifyPassword")
    public Result modifyPassword(@RequestHeader("Authorization") String authorization, String oldPassword, String newPassword) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setUsername(token.getUsername());
            user.setPassword(oldPassword);
            user = new ServiceFacade().userServiceGetByUsernamePassword(user);
            if (user != null) {
                user.setPassword(newPassword);
                int count = new ServiceFacade().userServiceUpdateOne(user);
                if (count > 0) {
                    return new Result(StatusCode.OK, StatusMsg.OK);
                } else {
                    return new Result(StatusCode.MODIFYFAIL, StatusMsg.MODIFYFAIL);
                }
            } else {
                return new Result(StatusCode.PASSWORDERROR, StatusMsg.PASSWORDERROR);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader("Authorization") String authorization) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user = new ServiceFacade().userServiceGetById(user);
            if (user != null) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", user.getId());
                map.put("username", token.getUsername());
                map.put("email", user.getEmail());
                map.put("phone", user.getPhone());
                map.put("id_card", user.getId_card());
                map.put("point", user.getPoint());
                return new Result(StatusCode.OK, StatusMsg.OK, map);
            } else {
                return new Result(StatusCode.GETERROR, StatusMsg.GETERROR);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @PassToken
    @RequestMapping("register")
    public Result register(String username, String password, String email, String phone, String code) throws CloneNotSupportedException {
        VerifyRecord verifyRecord = new VerifyRecord();
        verifyRecord.setReceiver(email);
        verifyRecord.setCode(code);
        int count = new ServiceFacade().verifyRecordServiceUpdateByCode(verifyRecord);
        if (count == 0) {
            return new Result(StatusCode.EMAILCODEERROR, StatusMsg.EMAILCODEERROR);
        }
        //use prototype
        User user = newUser.clone();
        //use prototype
        user.setUsername(username);
        count = new ServiceFacade().userServiceGetCount(user);
        if (count > 0) {
            return new Result(StatusCode.USEREXIST, StatusMsg.USEREXIST);
        }
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        count = new ServiceFacade().userServiceInsertOne(user);
        if (count > 0) {
            user = new ServiceFacade().userServiceGetByUsername(user);
            Map<String, Object> map = new HashMap<>();
            if (user != null) {
                map.put("token", new ServiceFacade().tokenServiceCreateToken(user));
            }
            return new Result(StatusCode.OK, StatusMsg.OK, map);
        } else {
            return new Result(StatusCode.REGISTERFAIL, StatusMsg.REGISTERFAIL);
        }
    }

    @PassToken
    @RequestMapping("sendCode")
    public Result sendCode(String email) {
        VerifyRecord verifyRecord = new VerifyRecord();
        String code = RandomUtil.randomNumbers(6);
        verifyRecord.setUid(0);
        verifyRecord.setReceiver(email);
        verifyRecord.setType(1);
        verifyRecord.setState(1);
        verifyRecord.setCode(code);
        Date expireTime = new Date();
        expireTime.setTime(System.currentTimeMillis() + 60 * 10 * 1000);
        verifyRecord.setExpire_time(expireTime);
        int count = new ServiceFacade().verifyRecordServiceInsertOne(verifyRecord);
        if (count > 0) {
            if (YunCourierUtilImpl.easyMail(email, "【hellokebbi】register", "【hellokebbi】Your verification code is (valid within 10 minutes):" + code)) {
                return new Result(StatusCode.OK, StatusMsg.OK);
            } else {
                return new Result(StatusCode.SENDEMAILFAIL, StatusMsg.SENDEMAILFAIL);
            }
        } else {
            return new Result(StatusCode.INSERTFAIL, StatusMsg.INSERTFAIL);
        }
    }

    @PassToken
    @RequestMapping("checkToken")
    public Result checkToken(@RequestHeader("Authorization") String authorization) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            if (token.getExpire_time().after(new Date())) {
                User user = new User();
                user.setId(token.getId());
                user = new ServiceFacade().userServiceGetById(user);
                if (user != null) {
                    if (user.getState() == 1) {
                        if (SecureUtil.md5(user.getPassword()).equals(token.getPassword())) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("username", token.getUsername());
                            map.put("ttl", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(token.getExpire_time()));
                            return new Result(StatusCode.OK, StatusMsg.OK, map);
                        } else {
                            return new Result(StatusCode.PASSWORDERROR, StatusMsg.PASSWORDERROR);
                        }
                    } else {
                        return new Result(StatusCode.USERSTATEEXCEPTION, StatusMsg.USERSTATEEXCEPTION);
                    }
                } else {
                    return new Result(StatusCode.USERNOTEXIST, StatusMsg.USERNOTEXIST);
                }
            } else {
                return new Result(StatusCode.USERNOTEXIST, StatusMsg.USERNOTEXIST);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @PassToken
    @RequestMapping("checkUsername")
    public Result checkUsername(String username) {
        User user = new User();
        user.setUsername(username);
        int count = new ServiceFacade().userServiceGetCount(user);
        if (count == 0) {
            return new Result(StatusCode.OK, StatusMsg.OK);
        }
        return new Result(StatusCode.USEREXIST, StatusMsg.USEREXIST);
    }

    @LoginToken
    @RequestMapping("bindIdCard")
    public Result bindIdCard(@RequestHeader("Authorization") String authorization, String idCard) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user.setId_card(idCard);
            int count = new ServiceFacade().userServiceUpdateOne(user);
            if (count > 0) {
                return new Result(StatusCode.OK, StatusMsg.OK);
            } else {
                return new Result(StatusCode.MODIFYFAIL, StatusMsg.MODIFYFAIL);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getUserDiscount")
    public Result getUserDiscount(@RequestHeader("Authorization") String authorization) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user = new ServiceFacade().userServiceGetById(user);
            if (user != null) {
                Map<String, Object> map = new HashMap<>();
                String level = null;
                Float discount = 0f;
                int point = user.getPoint();
                if (point >= 5000) {
                    level = "diamond";
                    discount = 0.7f;
                } else if (point >= 2000) {
                    level = "gold";
                    discount = 0.8f;
                } else if (point >= 1000) {
                    level = "silver";
                    discount = 0.9f;
                } else {
                    level = "ordinary";
                    discount = 1.0f;
                }
                map.put("point", user.getPoint());
                map.put("level", level);
                map.put("discount", discount);
                return new Result(StatusCode.OK, StatusMsg.OK, map);
            } else {
                return new Result(StatusCode.USERNOTEXIST, StatusMsg.USERNOTEXIST);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }
}
