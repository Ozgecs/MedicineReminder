package com.ozge.medicinereminder.nabızekle;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBPulseManager;
import com.ozge.medicinereminder.R;

import java.util.Calendar;

public class AddPulseActivity extends Activity implements View.OnClickListener {


    //Widgets:
    private Button addpulseButton;
    private EditText pulseEditText;
    public int mYear, mMonth, mDay;
    private EditText datepEditText;
    private DBPulseManager dbPulseManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Kayıt Ekranı");
        setContentView(R.layout.activity_add_pulse);

        //Instantiation of widgets:
        pulseEditText = findViewById(R.id.pulse_edittext);
        datepEditText = findViewById(R.id.datep_edittext);
        addpulseButton = findViewById(R.id.addp_record);



        dbPulseManager = new DBPulseManager(this);
        dbPulseManager.open();
        addpulseButton.setOnClickListener(this);
        datepEditText.setOnClickListener(this);




    }

    @Override
    public void onClick(View m) {
        switch (m.getId()) {
            case R.id.addp_record:
                final String pulse = pulseEditText.getText().toString();
                final String datep = datepEditText.getText().toString();

                dbPulseManager.insertp(pulse, datep);
                Intent intent = new Intent(getApplicationContext(),
                        PulseListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            case R.id.datep_edittext:
                final Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                //show dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datepEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();

        }
    }
}