package com.voler;

/**
 * 三尺春光驱我寒，一生戎马为长安
 * Created by Han on 17/6/8.
 */

public class haha {
    char[] chars={'a','b','c'};
    String string="str";
    public static void main(String[] args) {
        haha demo = new haha();
        haha demo1 = new haha();
        chage(demo.chars,demo.string);
        System.out.println(demo.string+demo.chars);
    }


    public static void chage(char[] chars,String string){
        string="test";
        chars[0]='g';
    }

        public static int a=1;
}
