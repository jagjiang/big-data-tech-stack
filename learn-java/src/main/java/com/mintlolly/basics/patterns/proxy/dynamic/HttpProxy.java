package com.mintlolly.basics.patterns.proxy.dynamic;

import com.mintlolly.basics.patterns.proxy.staticproxy.HttpUtil;
import com.mintlolly.basics.patterns.proxy.staticproxy.IHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2021/7/13
 *
 * @author jiangbo
 * Description:
 */
public class HttpProxy implements InvocationHandler {
    final static Logger logger = LoggerFactory.getLogger(HttpProxy.class);
    private HttpUtil httpUtil;

    public IHttp getInstance(HttpUtil httpUtil){
        this.httpUtil = httpUtil;
        return (IHttp)Proxy.newProxyInstance(httpUtil.getClass().getClassLoader(), httpUtil.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(method.getName().equals("request")){
            //如果方法名是request,打印日志，并调用request方法
            logger.info("发送数据：{}",args[0]);
            result = method.invoke(httpUtil,args);
        }else if(method.getName().equals("onSuccess")){
            logger.info("收到数据：{}",args[0]);
            result = method.invoke(httpUtil,args);
        }
        return result;
    }
}
