package com.voler.person.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.voler.annotation.FieldInject;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    @FieldInject
    String name;
    @FieldInject
    String age;
    @FieldInject
    String getName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
//    tv.setText(stringFromJNI());

        PriInterface pri = new ProxyHandle().create(PriImpl.class);
        pri.pri();

        try {
            new FUtil().find(nb.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
