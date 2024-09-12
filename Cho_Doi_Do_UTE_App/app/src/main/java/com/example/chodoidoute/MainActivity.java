package com.example.chodoidoute;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner_DiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_DiaChi = (Spinner) findViewById(R.id.spinner_DiaChi);

        ArrayList<String> data_DiaChi = new ArrayList<>();
        data_DiaChi.add("Hà Nội");
        data_DiaChi.add("Hồ Chí Minh");
        data_DiaChi.add("Đà Nẵng");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data_DiaChi);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_DiaChi.setAdapter(adapter);
        spinner_DiaChi.setSelection(0);
    }
}
