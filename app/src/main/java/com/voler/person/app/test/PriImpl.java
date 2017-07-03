package com.voler.person.app.test;


/**
 * PriImpl Created by voler on 2017/5/19.
 * 说明：
 */

public class PriImpl implements PriInterface{
    @Override
    public String print() {
        System.out.println("print");
        return "1";
    }

    @Override
    public String print(String string) {
        return null;
    }

    @Override
    public void pri() {
        System.out.println("extend");
    }

    @Override
    public void pri(String string) {

    }
}
