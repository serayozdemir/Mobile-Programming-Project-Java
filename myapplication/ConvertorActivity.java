package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;


import androidx.appcompat.app.AppCompatActivity;

public class ConvertorActivity extends AppCompatActivity {

    TextView convertor;
    TextView decimalText;
    TextView dcmlBase;
    EditText decimalInput;
    Spinner conversionTypeSpinner;
    Button button1;
    EditText resultText;
    TextView mbyteText;
    EditText mbyteInput;
    TextView mbyteBase;
    Spinner mbyteTypeSpinner;
    Button button2;
    EditText result2Text;
    TextView celsiusText;
    EditText celsiusInput;
    RadioGroup radioGroup;
    RadioButton radioFahrenheit;
    RadioButton radioKelvin;
    Button button3;
    EditText result3Text;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);
        convertor = findViewById(R.id.convertor);
        decimalText = findViewById(R.id.decimalText);
        dcmlBase = findViewById(R.id.dcmlBase);
        decimalInput = findViewById(R.id.decimalInput);
        conversionTypeSpinner = findViewById(R.id.conversionTypeSpinner);
        button1 = findViewById(R.id.button1);
        resultText = findViewById(R.id.resultText);

        mbyteText = findViewById(R.id.mbyteText);
        mbyteInput = findViewById(R.id.mbyteInput);
        mbyteBase = findViewById(R.id.mbyteBase);
        mbyteTypeSpinner = findViewById(R.id.mbyteTypeSpinner);
        button2 = findViewById(R.id.button2);
        result2Text = findViewById(R.id.result2Text);

        celsiusText = findViewById(R.id.celsiusText);
        celsiusInput = findViewById(R.id.celsiusInput);
        radioGroup = findViewById(R.id.radioGroup);
        radioFahrenheit = findViewById(R.id.radioFahrenheit);
        radioKelvin = findViewById(R.id.radioKelvin);
        button3 = findViewById(R.id.button3);
        result3Text = findViewById(R.id.result3Text);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.conversion_types,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        conversionTypeSpinner.setAdapter(adapter);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertAndDisplayResult();
            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.conversion_types2,
                android.R.layout.simple_spinner_item
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbyteTypeSpinner.setAdapter(adapter2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertAndDisplayResult2();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertAndDisplayResult3();
            }
        });
    }
    private void convertAndDisplayResult() {
        String decimalStr = decimalInput.getText().toString();

        if (decimalStr.isEmpty()) {
            // Kullanıcı bir sayı girmemişse
            return;
        }

        int decimal = Integer.parseInt(decimalStr);

        String binary = Integer.toBinaryString(decimal);
        String octal = Integer.toOctalString(decimal);
        String hexa = Integer.toHexString(decimal);

        String selectedConversion = conversionTypeSpinner.getSelectedItem().toString();
        String result = "";

        switch (selectedConversion) {
            case "Binary (ikilik)":
                result = binary;
                break;
            case "Octal (sekizlik)":
                result = octal;
                break;
            case "Hexadecimal (on atlılık)":
                result = hexa;
                break;
        }

        // Sonucu EditText içine yazdır
        resultText.setText(result);
    }
    private void convertAndDisplayResult2() {
        // Kullanıcının girdiği Megabyte değerini al
        String megabyteStr = mbyteInput.getText().toString();

        if (megabyteStr.isEmpty()) {
            // Kullanıcı bir sayı girmemişse işlem yapma
            return;
        }

        double megabyte = Double.parseDouble(megabyteStr);

        String conversionType = mbyteTypeSpinner.getSelectedItem().toString();
        double mresult = 0;

        // Seçilen dönüştürme türüne göre dönüşüm yap
        switch (conversionType) {
            case "Kilobyte":
                mresult = megabyte * 1024;
                break;
            case "Byte":
                mresult = megabyte * 1024 * 1024;
                break;
            case "Kibibyte":
                mresult = megabyte * 1000 * 1000 / 1024;
                break;


        }

        // Sonucu TextView'e yazdır
        result2Text.setText(String.valueOf(mresult));
    }

    private void convertAndDisplayResult3() {
        String celsiusStr = celsiusInput.getText().toString();

        if (celsiusStr.isEmpty()) {
            return;
        }

        double celsius = Double.parseDouble(celsiusStr);
        double result;

        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId == -1) {
            // Hiçbir RadioButton seçilmemişse işlem yapma
            return;
        }

        if (selectedRadioButtonId == R.id.radioFahrenheit) {
            // Fahrenheit seçildiyse Celsius'i Fahrenheit'e çevir
            result = celsius * 9 / 5 + 32;
        } else {
            // Kelvin seçildiyse Celsius'i Kelvin'e çevir
            result = celsius + 273.15;
        }
        //%.2f virgülden sonraki iki basamağı almak için kullanılıyor
        result3Text.setText(String.format("%.2f", result));

    }
}


