package com.boot.hellokebbi.controller;

import com.boot.hellokebbi.annotation.LoginToken;
import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.entity.Transaction;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.controller
 * @ClassName: TransactionController
 * @Description: This is TransactionController class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-21 14:11
 */
@RestController
@RequestMapping("/v1/api/transaction")
public class TransactionController {

    @LoginToken
    @RequestMapping("getTransactionList")
    public Result getTransactionList(@RequestHeader("Authorization") String authorization, @RequestParam(required = false, defaultValue = "0") int type, String sortName, String sortType) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Transaction transaction = new Transaction();
            transaction.setUid(token.getId());
            transaction.setType(type);
            String sort = "";
            if (sortName != "" && sortName != "") {
                sort = sortName;
                if (sortType != "" && sortType != "") {
                    sort += " " + sortType;
                }
            }
            PageHelper.startPage(1, 10000, sort);
            List<Map<String, Object>> select = new ServiceFacade().transactionServiceGetList(transaction);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(select);
            return new Result(StatusCode.OK, StatusMsg.OK, pageInfo.getList());
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("getTransactionInfo")
    public Result getTransactionInfo(@RequestHeader("Authorization") String authorization, @RequestParam int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Transaction transaction = new Transaction();
            transaction.setId(id);
            transaction.setUid(token.getId());
            transaction = new ServiceFacade().transactionServiceGetInfo(transaction);
            if (transaction != null) {
                return new Result(StatusCode.OK, StatusMsg.OK, transaction);
            } else {
                return new Result(StatusCode.GETERROR, StatusMsg.GETERROR);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("deleteTransaction")
    public Result deleteTransaction(@RequestHeader("Authorization") String authorization, @RequestParam int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Transaction transaction = new Transaction();
            transaction.setId(id);
            transaction.setUid(token.getId());
            transaction = new ServiceFacade().transactionServiceGetInfo(transaction);
            if (transaction != null && transaction.getState() == 1) {
                int count = new ServiceFacade().transactionServiceDeleteOne(transaction);
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
}
