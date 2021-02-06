package com.boot.hellokebbi.controller;

import com.boot.hellokebbi.annotation.LoginToken;
import com.boot.hellokebbi.entity.Card;
import com.boot.hellokebbi.entity.Token;
import com.boot.hellokebbi.pattern.facade.ServiceFacade;
import com.boot.hellokebbi.result.Result;
import com.boot.hellokebbi.result.StatusCode;
import com.boot.hellokebbi.result.StatusMsg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: com.boot.hellokebbi.controller
 * @ClassName: CardController
 * @Description: This is CardController class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 2:03
 */
@RestController
@RequestMapping("/v1/api/card")
public class CardController {
    @LoginToken
    @RequestMapping("addCard")
    public Result addCard(@RequestHeader("Authorization") String authorization, String name, String cardNum, String goodThru, String password) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Card card = new Card();
            card.setUid(token.getId());
            card.setState(1);
            card.setName(name);
            card.setCard_num(cardNum);
            int count = new ServiceFacade().cardServiceGetCount(card);
            if (count > 0) {
                return new Result(StatusCode.CARDEXIST, StatusMsg.CARDEXIST);
            }
            card.setGood_thru(goodThru);
            card.setPassword(password);
            count = new ServiceFacade().cardServiceInsertOne(card);
            if (count > 0) {
                return new Result(StatusCode.OK, StatusMsg.OK);
            } else {
                return new Result(StatusCode.INSERTFAIL, StatusMsg.INSERTFAIL);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("modifyCardInfo")
    public Result modifyCardInfo(@RequestHeader("Authorization") String authorization, int id, String name, String cardNum, String goodThru, String password) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Card card = new Card();
            card.setUid(token.getId());
            card.setId(id);
            card.setState(1);
            card.setName(name);
            card.setCard_num(cardNum);
            card.setGood_thru(goodThru);
            card.setPassword(password);
            int count = new ServiceFacade().cardServiceUpdateOne(card);
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
    @RequestMapping("getCardList")
    public Result getCardList(@RequestHeader("Authorization") String authorization, String sortName, String sortType) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Card card = new Card();
            card.setUid(token.getId());
            card.setState(1);
            String sort = "";
            if (sortName != "" && sortName != "") {
                sort = sortName;
                if (sortType != "" && sortType != "") {
                    sort += " " + sortType;
                }
            }
            PageHelper.startPage(1, 10000, sort);
            List<Map<String, Object>> select = new ServiceFacade().cardServiceGetList(card);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(select);
            return new Result(StatusCode.OK, StatusMsg.OK, pageInfo.getList());
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

    @LoginToken
    @RequestMapping("deleteCard")
    public Result deleteCard(@RequestHeader("Authorization") String authorization, int id) {
        Token token = new ServiceFacade().tokenServiceParseToken(authorization);
        if (token != null) {
            Card card = new Card();
            card.setId(id);
            card.setUid(token.getId());
            int count = new ServiceFacade().cardServiceDeleteOne(card);
            if (count > 0) {
                return new Result(StatusCode.OK, StatusMsg.OK);
            } else {
                return new Result(StatusCode.DELETEFAIL, StatusMsg.DELETEFAIL);
            }
        } else {
            return new Result(StatusCode.ACCESSERROR, StatusMsg.ACCESSERROR);
        }
    }

}
