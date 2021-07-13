package com.mintlolly.basics.patterns.proxy.staticproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class HttpProxy implements IHttp{
    final static Logger logger  = LoggerFactory.getLogger(HttpProxy.class);
    private final HttpUtil httpUtil;
    public HttpProxy(HttpUtil httpUtil){
        this.httpUtil = httpUtil;
    }
    @Override
    public void request(String sendData) {
        logger.info("发送数据:{}",sendData);
        httpUtil.request(sendData);
    }

    @Override
    public void onSuccess(String receiveData) {
        logger.info("收到数据:{}",receiveData);
        httpUtil.onSuccess(receiveData);
    }
}
