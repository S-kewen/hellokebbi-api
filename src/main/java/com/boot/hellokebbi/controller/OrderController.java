package com.boot.hellokebbi.controller;

import cn.hutool.core.util.RandomUtil;
import com.boot.hellokebbi.annotation.LoginToken;
import com.boot.hellokebbi.entity.*;
import com.boot.hellokebbi.pattern.cor.*;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import com.boot.hellokebbi.util.YunCourierUtilImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.controller
 * @ClassName: OrderController
 * @Description: This is OrderController class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:25
 */
@RestController
@RequestMapping("/v1/api/order")
public class OrderController {
    @LoginToken
    @RequestMapping("addOrder")
    public Result addOrder(@RequestHeader("Authorization") String authorization, int rid, String pickupTime, int day, String address, String expireTime, String code) throws ParseException {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user = new ServiceFacade().userServiceGetById(user);
            if (user != null) {
                VerifyRecord verifyRecord = new VerifyRecord();
                verifyRecord.setReceiver(user.getPhone());
                verifyRecord.setCode(code);
                int count = new ServiceFacade().verifyRecordServiceUpdateByCode(verifyRecord);
                if (count == 0) {
                    return new Result(StatusCode.SMSCODEERROR, StatusMsg.SMSCODEERROR);
                }
            } else {
                return new Result(StatusCode.USERNOTEXIST, StatusMsg.USERNOTEXIST);
            }
            Order order = new Order();
            Robot robot = new Robot();
            robot.setId(rid);
            robot = new ServiceFacade().robotServiceGetById(robot);
            if (robot != null) {
                if (robot.getState() != 1) {
                    return new Result(StatusCode.ROBOTSNOTBORROW, StatusMsg.ROBOTSNOTBORROW);
                }
            } else {
                return new Result(StatusCode.ROBOTSNOTBORROW, StatusMsg.ROBOTSNOTBORROW);
            }
            synchronized (OrderController.class) {
                robot = new Robot();
                robot.setId(rid);
                robot = new ServiceFacade().robotServiceGetById(robot);
                if (robot != null) {
                    if (robot.getState() == 1) {
                        order.setUid(token.getId());
                        order.setRid(rid);
                        order.setState(1);
                        order.setPickup_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(pickupTime));
                        order.setExpire_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(expireTime));
                        int amount = day * 100;
                        if (amount <= 0) {
                            return new Result(StatusCode.PICKUPTIMEOREXPIRETIMEWRONG, StatusMsg.PICKUPTIMEOREXPIRETIMEWRONG);
                        }
                        //use chain of responsibility
                        DiscountHandler discountHandler = new DiamondMember(new GoldMember(new SilverMember(new OrdinaryMember(null))));
                        amount = discountHandler.help(user.getPoint(), amount);
                        //use chain of responsibility
                        order.setAmount(amount);
                        if (order.getPickup_time().after(order.getExpire_time())) {
                            return new Result(StatusCode.PICKUPTIMEOREXPIRETIMEWRONG, StatusMsg.PICKUPTIMEOREXPIRETIMEWRONG);
                        }
                        order.setAddress(address);
                        int count = new ServiceFacade().orderServiceInsertOne(order);
                        if (count > 0) {
                            YunCourierUtilImpl.easyMail(user.getEmail(), "【hellokebbi】transaction", "transaction success ($" + amount + " deducted)");
                            return new Result(StatusCode.OK, StatusMsg.OK);
                        } else {
                            return new Result(StatusCode.INSERTFAIL, StatusMsg.INSERTFAIL);
                        }

                    } else {
                        return new Result(StatusCode.ROBOTSNOTBORROW, StatusMsg.ROBOTSNOTBORROW);
                    }
                } else {
                    return new Result(StatusCode.ROBOTSNOTBORROW, StatusMsg.ROBOTSNOTBORROW);
                }
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getOrderList")
    public Result getRobotList(@RequestHeader("Authorization") String authorization, int state, String sortName, String sortType) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Order order = new Order();
            order.setUid(token.getId());
            order.setState(state);
            String sort = "";
            if (sortName != "" && sortName != "") {
                sort = sortName;
                if (sortType != "" && sortType != "") {
                    sort += " " + sortType;
                }
            }
            PageHelper.startPage(1, 10000, sort);
            List<Map<String, Object>> select = new ServiceFacade().orderServiceGetList(order);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(select);
            return new Result(StatusCode.OK, StatusMsg.OK, pageInfo.getList());
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getOrderInfo")
    public Result getOrderInfo(@RequestHeader("Authorization") String authorization, int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Order order = new Order();
            order.setId(id);
            order.setUid(token.getId());
            order = new ServiceFacade().orderServiceGetInfo(order);
            if (order != null) {
                return new Result(StatusCode.OK, StatusMsg.OK, order);
            } else {
                return new Result(StatusCode.GETERROR, StatusMsg.GETERROR);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("deleteOrder")
    public Result deleteOrder(@RequestHeader("Authorization") String authorization, int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Order order = new Order();
            order.setId(id);
            order.setUid(token.getId());
            order = new ServiceFacade().orderServiceGetInfo(order);
            if (order != null && order.getState() == 3) {
                int count = new ServiceFacade().orderServiceDeleteOne(order);
                if (count > 0) {
                    return new Result(StatusCode.OK, StatusMsg.OK);
                } else {
                    return new Result(StatusCode.DELETEFAIL, StatusMsg.DELETEFAIL);
                }
            } else {
                return new Result(StatusCode.DELETEFAIL, StatusMsg.DELETEFAIL);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("completeOrder")
    public Result completeOrder(@RequestHeader("Authorization") String authorization, int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Order order = new Order();
            order.setId(id);
            order.setUid(token.getId());
            order = new ServiceFacade().orderServiceGetInfo(order);
            if (order != null && order.getState() == 2) {
                order = new Order();
                order.setId(id);
                order.setUid(token.getId());
                order.setState(3);
                int count = new ServiceFacade().orderServiceUpdateOne(order);
                if (count > 0) {
                    return new Result(StatusCode.OK, StatusMsg.OK);
                } else {
                    return new Result(StatusCode.MODIFYFAIL, StatusMsg.MODIFYFAIL);
                }
            } else {
                return new Result(StatusCode.ORDERNOTCOMPLETE, StatusMsg.ORDERNOTCOMPLETE);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("sendSmsCode")
    public Result sendSmsCode(@RequestHeader("Authorization") String authorization) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            User user = new User();
            user.setId(token.getId());
            user = new ServiceFacade().userServiceGetById(user);
            if (user != null && user.getPhone() != "") {
                if ("".equals(user.getId_card())) {
                    return new Result(StatusCode.IDCARDNOTEXIST, StatusMsg.IDCARDNOTEXIST);
                }
                Card card = new Card();
                card.setUid(user.getId());
                int count = new ServiceFacade().cardServiceGetCount(card);
                if (count == 0) {
                    return new Result(StatusCode.CARDNOTEXIST, StatusMsg.CARDNOTEXIST);
                }
                VerifyRecord verifyRecord = new VerifyRecord();
                String code = RandomUtil.randomNumbers(6);
                verifyRecord.setUid(0);
                verifyRecord.setReceiver(user.getPhone());
                verifyRecord.setType(2);
                verifyRecord.setState(1);
                verifyRecord.setCode(code);
                Date expireTime = new Date();
                expireTime.setTime(System.currentTimeMillis() + 60 * 10 * 1000);
                verifyRecord.setExpire_time(expireTime);
                count = new ServiceFacade().verifyRecordServiceInsertOne(verifyRecord);
                if (count > 0) {
                    if (YunCourierUtilImpl.twSms(user.getPhone(), "【hellokebbi】pay", "【hellokebbi】Your payment verification code is (valid within 10 minutes):" + code)) {
                        return new Result(StatusCode.OK, StatusMsg.OK);
                    } else {
                        return new Result(StatusCode.SENDESMSFAIL, StatusMsg.SENDESMSFAIL);
                    }
                } else {
                    return new Result(StatusCode.SENDESMSFAIL, StatusMsg.SENDESMSFAIL);
                }
            } else {
                return new Result(StatusCode.SENDESMSFAIL, StatusMsg.SENDESMSFAIL);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("cancelOrder")
    public Result cancelOrder(@RequestHeader("Authorization") String authorization, int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Order order = new Order();
            User user = new User();
            user.setId(token.getId());
            user = new ServiceFacade().userServiceGetById(user);
            if (user == null) {
                return new Result(StatusCode.USERNOTEXIST, StatusMsg.USERNOTEXIST);
            }
            order.setId(id);
            order.setUid(token.getId());
            order = new ServiceFacade().orderServiceGetInfo(order);
            if (order != null && order.getState() == 1) {
                order = new Order();
                order.setId(id);
                order.setUid(token.getId());
                order.setState(4);
                int count = new ServiceFacade().orderServiceUpdateOne(order);
                if (count > 0) {
                    order = new ServiceFacade().orderServiceGetInfo(order);
                    YunCourierUtilImpl.easyMail(user.getEmail(), "【hellokebbi】transaction", "refund amount($" + order.getAmount() + ")");
                    return new Result(StatusCode.OK, StatusMsg.OK);
                } else {
                    return new Result(StatusCode.MODIFYFAIL, StatusMsg.MODIFYFAIL);
                }
            } else {
                return new Result(StatusCode.ORDERNOTCANCEL, StatusMsg.ORDERNOTCANCEL);
            }

        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }
}
