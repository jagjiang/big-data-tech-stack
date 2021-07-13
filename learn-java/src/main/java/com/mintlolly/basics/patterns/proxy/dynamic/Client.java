package com.mintlolly.basics.patterns.proxy.dynamic;

import com.mintlolly.basics.patterns.proxy.staticproxy.HttpUtil;
import com.mintlolly.basics.patterns.proxy.staticproxy.IHttp;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        IHttp proxy = new HttpProxy().getInstance(httpUtil);
        proxy.request("request data");
        proxy.onSuccess("received result");
    }
}
