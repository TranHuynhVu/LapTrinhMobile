package com.example.contentprovider;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.Manifest;
import android.provider.Telephony;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SmsAdapter smsAdapter;
    private List<Sms> smsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        smsList = new ArrayList<>();
        smsAdapter = new SmsAdapter(this, smsList);
        recyclerView.setAdapter(smsAdapter);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            readSmsMessages();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 1);
        }
    }

    private void readSmsMessages() {
        Uri smsUri = Telephony.Sms.CONTENT_URI;
        Cursor cursor = getContentResolver().query(smsUri, null, null, null, null);

        if (cursor != null) {
            int addressIndex = cursor.getColumnIndex(Telephony.Sms.ADDRESS);
            int bodyIndex = cursor.getColumnIndex(Telephony.Sms.BODY);
            int dateIndex = cursor.getColumnIndex(Telephony.Sms.DATE);

            if (addressIndex != -1 && bodyIndex != -1 && dateIndex != -1) {
                while (cursor.moveToNext()) {
                    String address = cursor.getString(addressIndex);
                    String body = cursor.getString(bodyIndex);
                    String date = cursor.getString(dateIndex);
                    smsList.add(new Sms(address, body, date));
                }
                smsAdapter.notifyDataSetChanged();
            }
            cursor.close();
        }
    }
}
