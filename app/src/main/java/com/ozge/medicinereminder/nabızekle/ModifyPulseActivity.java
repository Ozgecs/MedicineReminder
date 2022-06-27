package com.ozge.medicinereminder.nabızekle;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBPulseManager;
import com.ozge.medicinereminder.R;

public class ModifyPulseActivity extends Activity implements View.OnClickListener {

    //Widgets:
    private EditText pulseText;
    private Button deleteBtn;
    private EditText datepText;

    private long _idp;
    private DBPulseManager dbPulseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modify Record");
        setContentView(R.layout.activity_modify_pulse);

        dbPulseManager = new DBPulseManager(this);
        dbPulseManager.open();



        pulseText = findViewById(R.id.pulse_edittext);
        datepText = findViewById(R.id.datep_edittext);
        deleteBtn = findViewById(R.id.btn_deletep);

        Intent intent = getIntent();
        String idp = intent.getStringExtra("idp");
        String pulse = intent.getStringExtra("pulse");
        String datep = intent.getStringExtra("datep");



        _idp= Long.parseLong(idp);

        pulseText.setText(pulse);

        datepText.setText(datep);

        deleteBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_deletep:
                dbPulseManager.deletep(_idp);
                this.returnHome();
                break;

        }
    }

    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(),
                PulseListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);



    }
}