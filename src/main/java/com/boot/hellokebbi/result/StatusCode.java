package com.boot.hellokebbi.result;

/**
 * @PackageName: com.boot.hellokebbi.pattern.result
 * @ClassName: StatusCode
 * @Description: This is StatusCode class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-13 17:41
 */
public class StatusCode {
    public static final int OK = 200;//成功
    public static final int NOTFOUND = 404;//404
    public static final int ERROR = 500;//失败
    public static final int LOGINERROR = -1001;//用户名或密码错误
    public static final int ACCESSERROR = -1002;//权限不足
    public static final int USEREXIST = -1005;//用户已存在
    public static final int USERSTATEEXCEPTION = -1006;//用户状态异常
    public static final int MODIFYFAIL = -1007;//修改失败
    public static final int PASSWORDERROR = -1008;//密码错误
    public static final int GETERROR = -1009;//获取数据失败
    public static final int EMAILCODEERROR = -1010;//验证码错误
    public static final int REGISTERFAIL = -1011;//注册失败
    public static final int SENDEMAILFAIL = -1012;//发送失败
    public static final int INSERTFAIL = -1013;//插入数据失败
    public static final int USERNOTEXIST = -1014;//用户不存在
    public static final int DELETEFAIL = -1016;//删除失败
    public static final int CARDEXIST = -1017;//信用卡已存在
    public static final int ROBOTSNOTBORROW = -1018;//该机器人当前不允许租借
    public static final int ORDERNOTCOMPLETE = -1019;//该订单现在不能完成
    public static final int SENDESMSFAIL = -1020;//发送短信失败
    public static final int ORDERNOTCANCEL = -1021;//该订单现在不能取消
    public static final int SMSCODEERROR = -1022;//简讯验证码错误
    public static final int PICKUPTIMEOREXPIRETIMEWRONG = -1023;//提取时间或到期时间有误
    public static final int PICKUPTIMEWRONG = -1024;//提取时间输入有误
    public static final int CARDNOTEXIST = -1025;//信用卡不存在
    public static final int IDCARDNOTEXIST = -1026;//身份证不存在
}
