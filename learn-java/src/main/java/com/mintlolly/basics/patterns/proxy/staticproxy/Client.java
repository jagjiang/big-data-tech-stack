package com.mintlolly.basics.patterns.proxy.staticproxy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class Client {
    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        HttpProxy httpProxy = new HttpProxy(httpUtil);
        httpProxy.request("request data");
        httpProxy.onSuccess("receive result");
    }
}
