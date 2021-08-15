package com.mintlolly.basics.patterns.proxy.staticproxy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public interface IHttp {
    void request(String sendData);
    void onSuccess(String receiveData);
}
