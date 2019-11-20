package com.develop.hua.jni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView titleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTv=findViewById(R.id.title_tv);
        System.loadLibrary("native-lib");//引用cmake编译的so
        System.loadLibrary("Jnitest");//

        titleTv.setText(createStrFromJni());
//        titleTv.setText(;

    }

    public native String createStrFromJni();
}
