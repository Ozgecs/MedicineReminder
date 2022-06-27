package com.ozge.medicinereminder.doktorekle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBDoctorManager;
import com.ozge.medicinereminder.R;


public class ModifyDoctorActivity extends Activity implements View.OnClickListener {

    //Widgets
    private EditText namesurnameText;
    private Button updateBtnD, deleteBtnD;
    private EditText expertıseText;
    private EditText pnumberText;
    private EditText addressText;

    private Long _idd;
    private DBDoctorManager dbDoctorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modifiye Edildi");
        setContentView(R.layout.activity_modify_doctor);

        dbDoctorManager = new DBDoctorManager(this);
        dbDoctorManager.open();


        namesurnameText = findViewById(R.id.namesurname_edittext);
        expertıseText = findViewById(R.id.expertise_edittext);
        pnumberText = findViewById(R.id.pnumber_edittext);
        addressText = findViewById(R.id.address_edittext);
        updateBtnD = findViewById(R.id.btn_updated);
        deleteBtnD = findViewById(R.id.btn_deleted);

        Intent intentd = getIntent();
        String idd = intentd.getStringExtra("id");
        String namesurname = intentd.getStringExtra("namesurname");
        String expertıse = intentd.getStringExtra("expertıse");
        String pnumber = intentd.getStringExtra("pnumber");
        String address = intentd.getStringExtra("address");

        _idd = Long.parseLong(idd);

        namesurnameText.setText(namesurname);
        expertıseText.setText(expertıse);
        pnumberText.setText(pnumber);
        addressText.setText(address);
        updateBtnD.setOnClickListener(this);
        deleteBtnD.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_updated:
                String namesurname = namesurnameText.getText().toString();
                String expertıse = expertıseText.getText().toString();
                String pnumber = pnumberText.getText().toString();
                String address = addressText.getText().toString();
                dbDoctorManager.updated(_idd, namesurname,expertıse,pnumber,address);
                this.returnHome();
                break;

            case R.id.btn_deleted:
                dbDoctorManager.deleted(_idd);
                this.returnHome();
                break;

        }
    }

    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(),
                DoctorListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);



    }

}