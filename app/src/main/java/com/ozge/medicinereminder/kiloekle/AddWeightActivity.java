package com.ozge.medicinereminder.kiloekle;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBWeightManager;
import com.ozge.medicinereminder.R;

import java.util.Calendar;

public class AddWeightActivity extends Activity implements View.OnClickListener {

    private Button addweightButton;
    private EditText weightEditText;
    public int wYear, wMonth, wDay;
    private EditText datewEditText;
    private DBWeightManager dbWeightManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        weightEditText = findViewById(R.id.weight_edittext);
        datewEditText = findViewById(R.id.datew_edittext);
        addweightButton = findViewById(R.id.addw_record);

        dbWeightManager = new DBWeightManager(this);
        dbWeightManager.open();
        addweightButton.setOnClickListener(this);
        datewEditText.setOnClickListener(this);

    }
    @Override
    public void onClick(View m) {
        switch (m.getId()) {
            case R.id.addw_record:
                final String weight = weightEditText.getText().toString();
                final String datew = datewEditText.getText().toString();

                dbWeightManager.insertw(weight, datew);
                Intent intent = new Intent(getApplicationContext(),
                        WeightListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            case R.id.datew_edittext:
                final Calendar calendar = Calendar.getInstance();
                wYear = calendar.get(Calendar.YEAR);
                wMonth = calendar.get(Calendar.MONTH);
                wDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog1 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datewEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, wYear, wMonth, wDay);
                datePickerDialog1.show();

        }
    }

}