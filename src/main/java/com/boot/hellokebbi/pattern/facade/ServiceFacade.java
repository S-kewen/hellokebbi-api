package com.boot.hellokebbi.pattern.facade;

import com.boot.hellokebbi.entity.*;
import com.boot.hellokebbi.pattern.proxy.TokenServiceProxy;
import com.boot.hellokebbi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.pattern.facade
 * @ClassName: ServiceFacade
 * @Description: This is ServiceFacade class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-24 1:09
 * @Remark: 通過facade將controller中一對多轉換成一對一結構，減少controller調用複雜度，簡化controller
 */
@Component
//使用SpringBoot自動裝配(加入容器)
public class ServiceFacade {
    public static ServiceFacade serviceFacade;
    @Autowired
    private CardService cardService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RobotService robotService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyRecordService verifyRecordService;

    @PostConstruct
    //使用SpringBoot自動裝配(加入容器)
    private void init() {
        //使用SpringBoot自動裝配(加入容器)
        serviceFacade = this;
    } //1

    //cardService start
    public int cardServiceInsertOne(Card card) {
        return serviceFacade.cardService.insertOne(card);
    } //2

    public List<Map<String, Object>> cardServiceGetList(Card card) {
        return serviceFacade.cardService.getList(card);
    } //3

    public Card cardServiceGetById(Card card) {
        return serviceFacade.cardService.getById(card);
    } //4

    public int cardServiceGetCount(Card card) {
        return serviceFacade.cardService.getCount(card);
    } //5

    public int cardServiceDeleteOne(Card card) {
        return serviceFacade.cardService.deleteOne(card);
    } //6

    public int cardServiceUpdateOne(Card card) {
        return serviceFacade.cardService.updateOne(card);
    } //7
    //cardService end

    //orderService start
    public int orderServiceInsertOne(Order order) {
        return serviceFacade.orderService.insertOne(order);
    } //8

    public List<Map<String, Object>> orderServiceGetList(Order order) {
        return serviceFacade.orderService.getList(order);
    } //9

    public Order orderServiceGetInfo(Order order) {
        return serviceFacade.orderService.getInfo(order);
    } //10

    public int orderServiceGetCount(Order order) {
        return serviceFacade.orderService.getCount(order);
    } //11

    public int orderServiceDeleteOne(Order order) {
        return serviceFacade.orderService.deleteOne(order);
    } //12

    public int orderServiceUpdateOne(Order order) {
        return serviceFacade.orderService.updateOne(order);
    } //13
    //orderService end

    //robotService start
    public List<Map<String, Object>> robotServiceGetList(Robot robot) {
        return serviceFacade.robotService.getList(robot);
    } //14

    public Robot robotServiceGetById(Robot robot) {
        return serviceFacade.robotService.getById(robot);
    } //15

    public int robotServiceGetCount(Robot robot) {
        return serviceFacade.robotService.getCount(robot);
    } //16
    //robotService end

    //tokenService start
    public String tokenServiceCreateToken(User user) {
        return new TokenServiceProxy().createToken(user);
    } //17

    public Token tokenServiceParseToken(String tokenStr) {
        return new TokenServiceProxy().parseToken(tokenStr);
    } //18
    //tokenService end

    //transactionService start
    public List<Map<String, Object>> transactionServiceGetList(Transaction transaction) {
        return serviceFacade.transactionService.getList(transaction);
    } //19

    public Transaction transactionServiceGetInfo(Transaction transaction) {
        return serviceFacade.transactionService.getInfo(transaction);
    } //20

    public int transactionServiceDeleteOne(Transaction transaction) {
        return serviceFacade.transactionService.deleteOne(transaction);
    } //21
    //transactionService end


    //userService start

    public User userServiceGetById(User user) {
        return serviceFacade.userService.getById(user);
    } //22

    public User userServiceGetByUsername(User user) {
        return serviceFacade.userService.getByUsername(user);
    } //23

    public User userServiceGetByUsernamePassword(User user) {
        return serviceFacade.userService.getByUsernamePassword(user);
    } //24

    public int userServiceUpdateOne(User user) {
        return serviceFacade.userService.updateOne(user);
    } //25

    public int userServiceInsertOne(User user) {
        return serviceFacade.userService.insertOne(user);
    } //26

    public int userServiceGetCount(User user) {
        return serviceFacade.userService.getCount(user);
    } //27
    //userService end

    //verifyRecordService start
    public VerifyRecord verifyRecordServiceGetById(VerifyRecord verifyRecord) {
        return serviceFacade.verifyRecordService.getById(verifyRecord);
    } //28

    public int verifyRecordServiceUpdateOne(VerifyRecord verifyRecord) {
        return serviceFacade.verifyRecordService.updateOne(verifyRecord);
    } //29

    public int verifyRecordServiceInsertOne(VerifyRecord verifyRecord) {
        return serviceFacade.verifyRecordService.insertOne(verifyRecord);
    } //30

    public int verifyRecordServiceUpdateByCode(VerifyRecord verifyRecord) {
        return serviceFacade.verifyRecordService.updateByCode(verifyRecord);
    } //31
    //VerifyRecordService end
}
