package com.voler.person.app;

/**
 * Main Created by voler on 2017/8/18.
 * 说明：
 */

public class Main {
    public static void main(String[] args) {
        String string="1234567890123456789012345678901234567";
        System.out.println(string.length());
        if (string.length()>36) {
            string=string.substring(0, 36);
        }

        System.out.println(string);
    }
}
