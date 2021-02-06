package com.boot.hellokebbi.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @PackageName: com.boot.hellokebbi.util
 * @ClassName: YunCourierUtilImpl
 * @Description: This is YunCourierUtilImpl class by Skwen.
 * @Author: Skwen
 * @Date: 2020-12-14 2:20
 */
public class YunCourierUtilImpl {
    public static boolean easyMail(String to, String title, String msg) {
        final int appId = 22;
        final String token = "9a43db97d4d5e253f036191a168bb939";
        String result = HttpUtil.post("https://courier-api.iskwen.com/api/easyMail", "applyId=" + appId + "&token=" + token + "&to=" + to + "&title=" + title + "&msg=" + msg);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.getInteger("status") == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean twSms(String to, String title, String msg) {
        final int appId = 22;
        final String token = "9a43db97d4d5e253f036191a168bb939";
        String result = HttpUtil.post("https://courier-api.iskwen.com/api/smsToTaiwan", "applyId=" + appId + "&token=" + token + "&subject=OOSE-hellokebbi&to=" + to + "&msg=" + msg);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.getInteger("status") == 0) {
            return true;
        } else {
            return false;
        }
    }
}
