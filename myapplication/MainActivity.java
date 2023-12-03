package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    Button btn_convertor;
    Button btn_random;
    Button btn_sms;
    TextView schoolNo;
    TextView myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_convertor = findViewById(R.id.btn_convertor);
        btn_random = findViewById(R.id.btn_random);
        btn_sms = findViewById(R.id.btn_sms);
        schoolNo = findViewById(R.id.schoolNo);
        myName = findViewById(R.id.myName);

        applyAnimation(btn_convertor);
        applyAnimation(btn_random);
        applyAnimation(btn_sms);


        btn_convertor.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConvertorActivity.class);
                startActivity(intent);
            }
        }));

        btn_random.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        RandomActivity.class);
                startActivity(intent);
            }
        }));

        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, smsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void applyAnimation(Button button) {
        // Animasyonu yükle
        Animation fadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        fadeInAnimation.setDuration(5000);

        // Butona animasyonu uygula
        button.setVisibility(View.VISIBLE);
        button.startAnimation(fadeInAnimation);
    }
}