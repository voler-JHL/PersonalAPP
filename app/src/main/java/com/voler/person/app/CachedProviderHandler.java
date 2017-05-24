package com.voler.person.app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * CachedProviderHandler Created by voler on 2017/5/19.
 * 说明：
 */

public class CachedProviderHandler implements InvocationHandler{
    private Map<String, Object> cached = new HashMap<>();
    private Object target;

    public CachedProviderHandler(Object target) {
        this.target = target;
    }

    private Object tar;

    //绑定委托对象，并返回代理类
    public Object bind(Object tar)
    {
        this.tar = tar;
        //绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Type[] types = method.getParameterTypes();
        if (method.getName().matches("get.+") && (types.length == 1) &&
                (types[0] == String.class)) {
            String key = (String) args[0];
            Object value = cached.get(key);
            if (value == null) {
                value = method.invoke(target, args);
                cached.put(key, value);
            }
            return value;
        }
        return method.invoke(target, args);
    }
}
