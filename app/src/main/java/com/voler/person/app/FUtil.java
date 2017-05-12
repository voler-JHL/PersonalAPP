package com.voler.person.app;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * FUtil Created by voler on 2017/5/12.
 * 说明：
 */

public class FUtil {

    public void find(Class clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = clazz.getMethods();
        Object o = clazz.newInstance();
        for (Method method : methods) {
            if (method.getName().equals("pri")) {
                method.invoke(o);
            }
            if (method.getName().equals("prit")) {
                method.invoke(o);
            }
            Log.e("----", method.getName());
        }
    }

    public void find(String string) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            Class<?> aClass = Class.forName(string);
            find(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
