package com.voler.person.app.inject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.voler.annotation.FieldInject;
import com.voler.person.app.R;
import com.voler.person.app.bean.Student;
import com.voler.saber.Saber;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 三尺春光驱我寒，一生戎马为长安
 * Created by Han on 17/7/10.
 */

public class InjectActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;

    @FieldInject
    String string;
    @FieldInject
    int  number;
    @FieldInject
    char[]  chars;
    @FieldInject
    Serializable obj;
    @FieldInject
    ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        initView();
        Saber.inject(this);

        tv1.setText(string);
        tv2.setText(String.valueOf(number));
        tv3.setText(Arrays.toString(chars));
        tv4.setText(obj.toString());
        tv5.setText(StringUtils.join(studentList,","));
    }

    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        tv5 = (TextView) findViewById(R.id.tv_5);
    }
}
