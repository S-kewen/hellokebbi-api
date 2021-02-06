package com.boot.hellokebbi.result;

/**
 * @PackageName: com.boot.hellokebbi.pattern.result
 * @ClassName: StatusMsg
 * @Description: This is StatusMsg class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 17:41
 */
public class StatusMsg {
    public static final String OK = "success";//成功
    public static final String NOTFOUND = "the request was not found!";//404
    public static final String ERROR = "request error!";//失败
    public static final String LOGINERROR = "username or password is wrong!";//用户名或密码错误
    public static final String ACCESSERROR = "token invalid,please login again!";//token无效
    public static final String USEREXIST = "user account already existed!";//用户已存在
    public static final String USERSTATEEXCEPTION = "user state exception!";//用户状态异常
    public static final String MODIFYFAIL = "modify fail!";//修改失败
    public static final String PASSWORDERROR = "wrong password!";//密码错误
    public static final String GETERROR = "failed to get data!";//获取数据失败
    public static final String EMAILCODEERROR = "email verification code error!";//验证码错误
    public static final String REGISTERFAIL = "register fail!";//注册失败
    public static final String SENDEMAILFAIL = "send fail,please check email!";//发送失败
    public static final String INSERTFAIL = "failed to save data!";//插入数据失败
    public static final String USERNOTEXIST = "user account doesn't exist!";//用户不存在
    public static final String DELETEFAIL = "failed to delete data!";//删除失败
    public static final String CARDEXIST = "this card already existed!";//信用卡已存在
    public static final String ROBOTSNOTBORROW = "this robot is not allowed to be rented now!";//该机器人当前不允许租借
    public static final String ORDERNOTCOMPLETE = "the order is not allowed to complete now";//该订单现在不能完成
    public static final String SENDESMSFAIL = "send fail,please check your phone!";//发送短信失败
    public static final String ORDERNOTCANCEL = "the order is not allowed to cancel now";//该订单现在不能取消
    public static final String SMSCODEERROR = "sms verification code error!";//简讯验证码错误
    public static final String PICKUPTIMEOREXPIRETIMEWRONG = "pickup time must more than the expire time!";//时间选择有误
    public static final String PICKUPTIMEWRONG = "pickup time must more than now!";//提取时间输入有误
    public static final String CARDNOTEXIST = "please bind your credit card first";//信用卡不存在
    public static final String IDCARDNOTEXIST = "please bind your id card first";//身份证不存在
}
