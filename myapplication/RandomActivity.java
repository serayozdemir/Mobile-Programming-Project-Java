package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class RandomActivity extends AppCompatActivity {

    TextView rnd_generator;
    TextView tv_adet;
    EditText adet_sayisi;
    TextView tv_max;
    EditText max_value;
    TextView tv_min;
    EditText min_value;
    Button btn_add;
    ScrollView scrollView;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        rnd_generator = findViewById(R.id.rnd_generator);
        tv_adet = findViewById(R.id.tv_adet);
        adet_sayisi = findViewById(R.id.adet_sayisi);
        tv_max = findViewById(R.id.tv_max);
        max_value = findViewById(R.id.max_value);
        tv_min = findViewById(R.id.tv_min);
        min_value = findViewById(R.id.min_value);
        btn_add = findViewById(R.id.btn_add);
        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProgressBars();
            }
        });
    }
    private void addProgressBars() {
        String adetStr = adet_sayisi.getText().toString();
        String minStr = min_value.getText().toString();
        String maxStr = max_value.getText().toString();

        if (adetStr.isEmpty() || minStr.isEmpty() || maxStr.isEmpty()) {
            return;
        }
        int adet = Integer.parseInt(adetStr);
        int min = Integer.parseInt(minStr);
        int max = Integer.parseInt(maxStr);

        if (min >= max) {
            return;
        }
        // Eğer daha önce eklenmiş olan ProgressBar varsa, onları temizle
        linearLayout.removeAllViews();

        for (int i = 0; i < adet; i++) {
            addRandomProgressBar(linearLayout, min, max);
        }
    }
    private void addRandomProgressBar(LinearLayout layout, int min, int max) {
        LinearLayout containerLayout = new LinearLayout(this);
        containerLayout.setOrientation(LinearLayout.HORIZONTAL);

        ProgressBar progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams progressBarParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
        );
        progressBarParams.setMargins(100, 50, 100, 50);
        progressBar.setLayoutParams(progressBarParams);

        int randomValue = generateRandomValue(min, max);
        progressBar.setMax(max - min);
        progressBar.setProgress(randomValue - min);

        TextView minValueTextView = new TextView(this);
        minValueTextView.setText(String.valueOf(min));
        minValueTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView maxValueTextView = new TextView(this);
        maxValueTextView.setText(String.valueOf(max));
        maxValueTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        containerLayout.addView(minValueTextView);
        containerLayout.addView(progressBar);
        containerLayout.addView(maxValueTextView);

        layout.addView(containerLayout);

        TextView valueTextView = new TextView(this);
        valueTextView.setText(String.format("%d (%d%%)", randomValue, ((randomValue - min) * 100 / (max - min))));
        valueTextView.setGravity(Gravity.CENTER);
        layout.addView(valueTextView);
    }

    private int generateRandomValue(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
