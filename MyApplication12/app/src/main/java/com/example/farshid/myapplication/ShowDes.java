package com.example.farshid.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDes extends AppCompatActivity {


    TextView txt1, txt2,text_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_des);
        txt1 = findViewById(R.id.show_text1);
        txt2 = findViewById(R.id.show_text2);
        text_toolbar = findViewById(R.id.text_toolbar);
        Typeface tf2 = Typeface.createFromAsset(getAssets(), "Font/dima.ttf");
        txt1.setTypeface(tf2);
        text_toolbar.setTypeface(tf2);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/by.ttf");
        txt2.setTypeface(tf);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String myVariable_1 = extras.getString("Title");
            String myVariable_2 = extras.getString("Des");
            txt1.setText(myVariable_1);
            txt2.setText(myVariable_2);
            text_toolbar.setText(myVariable_1);


        }


    }
}
