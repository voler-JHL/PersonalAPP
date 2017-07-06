package com.voler.person.app.inject;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voler.annotation.FieldInject;
import com.voler.saber.Saber;

import java.util.ArrayList;

/**
 * InjectFragment Created by voler on 2017/6/30.
 * 说明：
 */

public class InjectFragment extends android.support.v4.app.Fragment{
    @FieldInject
    public String inject;
    @FieldInject
    public ArrayList<Parcelable> yui;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Saber.inject(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
