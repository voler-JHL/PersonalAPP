package com.voler.person.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.voler.annotation.FieldInject;
import com.voler.person.app.R;
import com.voler.person.app.bean.Person;
import com.voler.person.app.bean.Student;
import com.voler.person.app.inject.InjectActivity;
import com.voler.person.app.lock.LockService;
import com.voler.person.app.widget.RatingBarView;
import com.voler.person.http.Api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

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

        long l = 1495603459000l;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzzz");
        String dateString = formatter.format(l);
        tv.setText(dateString);

        Intent intent = new Intent(this, InjectActivity.class);
        intent.putExtra("string","这是由MainActivity传来的字符串");
        intent.putExtra("number",52054843);
        intent.putExtra("chars",new char[]{'v','o','l','e','r',' ','m','e','a','n','s',' ','l','o','v','e','r'});
        intent.putExtra("obj",new Person("Voler",false));
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Loof",69));
        students.add(new Student("Fool",25));
        intent.putParcelableArrayListExtra("studentList",students);
        startActivity(intent);
        startService(new Intent(this, LockService.class));
//        toggleFlowEntrance(this,SplashActivityAlias.class);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);
        Toast.makeText(this,today+"", Toast.LENGTH_SHORT).show();

        //
        RatingBarView viewById = (RatingBarView) findViewById(R.id.rb_view);
//        viewById.setStar(3,false);

        viewById.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                Toast.makeText(MainActivity.this, String.valueOf(RatingScore), Toast.LENGTH_SHORT).show();
            }
        });


        Api.getComApi()
                .getSplashAdv()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody s) {
                        try {
                            Log.e("-----", s.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

//        try {
//            new FUtil().find(PriImpl.class);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        finish();
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
