package com.example.contentprovider;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SmsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sms_detail);
        TextView tvDetailAddress = findViewById(R.id.tvDetailAddress);
        TextView tvDetailDate = findViewById(R.id.tvDetailDate);
        TextView tvDetailBody = findViewById(R.id.tvDetailBody);

        // Lấy dữ liệu từ Intent
        String address = getIntent().getStringExtra("address");
        String body = getIntent().getStringExtra("body");
        String date = getIntent().getStringExtra("date");

        tvDetailAddress.setText("From: " + address);
        tvDetailDate.setText("Date: " + date);
        tvDetailBody.setText("Message:\n" + body);
    }
}