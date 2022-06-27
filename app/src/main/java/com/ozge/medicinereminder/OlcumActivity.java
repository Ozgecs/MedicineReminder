package com.ozge.medicinereminder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ozge.medicinereminder.kiloekle.WeightListActivity;
import com.ozge.medicinereminder.nabızekle.PulseListActivity;
import com.ozge.medicinereminder.tansiyoekle.BpListActivity;


public class OlcumActivity extends AppCompatActivity {

    ActionBar actionBar;
    Button nabiz,kilo,tansiyon;
    /*Bu kısım Ölçüm activity' de bulunan butonların aktif edildiği yer.*/


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olcum);
        actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("ÖLÇÜM KAYIT SAYFASI");

        nabiz = findViewById(R.id.nabız);
        kilo = findViewById(R.id.kilo);
        tansiyon = findViewById(R.id.tansiyon);

        nabiz.setOnClickListener(view -> {
            Intent intent1 = new Intent(OlcumActivity.this, PulseListActivity.class);
            startActivity(intent1);
        });
        kilo.setOnClickListener(view -> {
            Intent intent2 = new Intent(OlcumActivity.this, WeightListActivity.class);
            startActivity(intent2);
        });
        tansiyon.setOnClickListener(view -> {
            Intent intent3 = new Intent(OlcumActivity.this, BpListActivity.class);
            startActivity(intent3);
        });
    }
}