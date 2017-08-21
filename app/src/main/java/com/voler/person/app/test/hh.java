package com.voler.person.app.test;

import com.voler.person.app.proxy.ProxyHandle;

/**
 * hh Created by voler on 2017/5/19.
 * 说明：
 */

public class hh {
    public static void main(String[] args) {
        PriInterface priInterface = new ProxyHandle().create(PriInterface.class);
        priInterface.pri();
        priInterface.pri("how old are you");
        String print = priInterface.print();
        System.out.println(print);
        String that = priInterface.print("yes,that is me");
        System.out.println(that);
    }
}
