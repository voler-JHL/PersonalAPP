package com.voler.person.app.proxy;

import com.voler.person.app.test.PriInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyHandle Created by voler on 2017/5/19.
 * 说明：
 */

public class ProxyHandle implements InvocationHandler{

    private Object object;
    private Object tar;
    public <T> T create(Class<T> cls){
        return (T)Proxy.newProxyInstance(cls.getClassLoader(),new Class<?>[]{cls},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("--->>>before");
        result = method.invoke(new PriInterface() {
            @Override
            public void pri() {
                System.out.println("No parameter, no return values");
            }
            @Override
            public void pri(String string) {
                System.out.println("parameter, no return values");
                System.out.println("parameter:"+string);
            }
            @Override
            public String print() {
                System.out.println("No parameter, return 0");
                return "return 0";
            }
            @Override
            public String print(String string) {
                System.out.println("parameter, return 1");
                System.out.println("parameter:"+string);
                return "return 1";
            }
        }, args);
        System.out.println("--->>>after");
        return "return 2";
    }
}
