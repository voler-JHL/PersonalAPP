package com.voler.person.app;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyHandle Created by voler on 2017/5/19.
 * 说明：
 */

public class ProxyHandle implements InvocationHandler{

    private Object object;
    private PriInterface tar;
    public <T> T create(Class cls){
        tar= (PriInterface) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),this);
        return (T)tar;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = method.invoke(tar,args);
        Log.e("----","hou");
        return result;
    }
}
