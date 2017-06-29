package com.voler.person.app.util;

import android.app.Fragment;
import android.os.Bundle;

/**
 * FragmentFactory Created by voler on 2017/6/28.
 * 说明：
 */

public class FragmentFactory {
    public static Fragment create(String s){
        Bundle bundle=new Bundle();
        bundle.putString("s",s);
        Fragment fragment=new Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public static <T extends Fragment> T create(Class<T> aClass){
        T t = null;
        try {
            t = aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Bundle bundle=new Bundle();
//        bundle.putString("s",s);
        Fragment fragment=new Fragment();
        fragment.setArguments(bundle);
        t.setArguments(bundle);
        return t;
    }

}
