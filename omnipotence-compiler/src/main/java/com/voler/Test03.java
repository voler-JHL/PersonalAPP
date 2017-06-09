package com.voler;

import java.util.Locale;

/**
 * Test03 Created by voler on 2017/6/3.
 * 说明：
 */

public class Test03 {

    public static void main(String[] args) {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;

        String format = String.format(Locale.KOREA, "%d", 123);
        System.out.println(format);
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
    }
}
