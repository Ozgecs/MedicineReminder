package com.ozge.medicinereminder.randevuekle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBMeetManager;
import com.ozge.medicinereminder.R;


public class ModifyMeetActivity extends Activity implements View.OnClickListener {

    //Widgets
    private EditText meetnameText;
    private Button deleteBtnM;
    private EditText maddressText;
    private EditText datemText;
    private Long _idm;
    private DBMeetManager dbMeetManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_meet);

        dbMeetManager = new DBMeetManager(this);
        dbMeetManager.open();

        meetnameText = findViewById(R.id.meetname_edittext);
        datemText = findViewById(R.id.date_edittext);
        maddressText = findViewById(R.id.maddress_edittext);


        deleteBtnM = findViewById(R.id.btn_deletem);
        datemText.setOnClickListener(this);

        Intent intentm = getIntent();
        String idm = intentm.getStringExtra("id");
        String meetname = intentm.getStringExtra("meetname");
        String date = intentm.getStringExtra("date");
        String maddress = intentm.getStringExtra("maddress");

        _idm = Long.parseLong(idm);

        meetnameText.setText(meetname);
        datemText.setText(date);
        maddressText.setText(maddress);

        deleteBtnM.setOnClickListener(this);



    }
    @Override
    public void onClick(View m){
        switch (m.getId()){
            case R.id.btn_deletem:
                dbMeetManager.deletem(_idm);
                this.returnHome();
                break;
        }

    }
    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(),
                MeetListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);



    }
}