package com.ozge.medicinereminder.randevuekle;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBMeetManager;
import com.ozge.medicinereminder.R;

import java.util.Calendar;

public class AddMeetActivity extends Activity implements View.OnClickListener {

    //Widgets
    private Button addmToDoButton;
    private EditText meetEditText;
    public int mYear, mMonth, mDay;
    private EditText dateEditText;
    private EditText maddressEditText;
    private DBMeetManager dbMeetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meet);

        //Instantiation of widgets
        meetEditText = findViewById(R.id.meetname_edittext);
        dateEditText = findViewById(R.id.date_edittext);
        maddressEditText = findViewById(R.id.maddress_edittext);
        addmToDoButton = findViewById(R.id.addm_record);

        dbMeetManager = new DBMeetManager(this);
        dbMeetManager.open();
        addmToDoButton.setOnClickListener(this);
        dateEditText.setOnClickListener(this);





    }
    @Override
    public void onClick(View m){
        switch (m.getId()){
            case R.id.addm_record:
                final String note = meetEditText.getText().toString();
                final String date = dateEditText.getText().toString();
                final String maddress = maddressEditText.getText().toString();

                dbMeetManager.insertm(note,date,maddress);

                Intent intent = new Intent(getApplicationContext(),
                        MeetListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            case R.id.date_edittext:
                final Calendar calendar = Calendar.getInstance ();
                mYear = calendar.get ( Calendar.YEAR );
                mMonth = calendar.get ( Calendar.MONTH );
                mDay = calendar.get ( Calendar.DAY_OF_MONTH );

                //show dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog ( this, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateEditText.setText ( dayOfMonth + "/" + (month + 1) + "/" + year );


                    }
                }, mYear, mMonth, mDay );
                datePickerDialog.show ();

        }

    }

}