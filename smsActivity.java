package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;

public class smsActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    TextView sms_txt;
    TextView tel_txt;
    EditText tel_no;
    TextView mesaj_txt;
    EditText msj_icerik;
    Button gonder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        sms_txt = findViewById(R.id.sms_txt);
        tel_txt = findViewById(R.id.tel_txt);
        tel_no = findViewById(R.id.tel_no);
        mesaj_txt = findViewById(R.id.mesaj_txt);
        msj_icerik = findViewById(R.id.msj_icerik);
        gonder = findViewById(R.id.gonder);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAndRequestPermissions();
            }
        });
    }

    private void checkAndRequestPermissions() {
        // İzinler kontrol ediliyor
        int smsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        // Eğer izin verilmediyse, izin iste
        if (smsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_REQUEST_CODE
            );
        } else {
            // İzin zaten varsa SMS gönderme işlemine devam et
            sendSms();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // İzin talebinin sonucunu kontrol et
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // İzin verildiyse SMS gönderme işlemine devam et
                sendSms();
            } else {
                // İzin verilmediyse kullanıcıya bilgi ver
                Toast.makeText(this, "SMS gönderme izni verilmedi.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSms() {
        // Kullanıcıdan alınan telefon numarası ve mesaj içeriği
        String phoneNumber = tel_no.getText().toString();
        String message = msj_icerik.getText().toString();

        try {
            // SMS gönderme işlemi
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

            // SMS gönderme başarılı mesajı
            Toast.makeText(this, "SMS gönderildi.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // SMS gönderme başarısız mesajı
            Toast.makeText(this, "SMS gönderme hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
