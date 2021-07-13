package com.mintlolly.basics.patterns.proxy.staticproxy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class HttpUtil implements IHttp{
    @Override
    public void request(String sendData) {
        System.out.println("网络请求中......");
    }

    @Override
    public void onSuccess(String receiveData) {
        System.out.println("网络请求完成。");
    }
}
