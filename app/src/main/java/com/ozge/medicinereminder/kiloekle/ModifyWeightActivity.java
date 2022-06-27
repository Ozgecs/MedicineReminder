package com.ozge.medicinereminder.kiloekle;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBWeightManager;
import com.ozge.medicinereminder.R;


public class ModifyWeightActivity extends Activity implements View.OnClickListener {

    //Widgets:
    private EditText weightText;
    private Button deleteBtnw;
    private EditText datewText;

    private long _idw;
    private DBWeightManager dbWeightManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_weight);

        dbWeightManager = new DBWeightManager(this);
        dbWeightManager.open();

        weightText = findViewById(R.id.weight_edittext);
        datewText = findViewById(R.id.datew_edittext);
        deleteBtnw = findViewById(R.id.btn_deletew);

        Intent intent = getIntent();
        String idw = intent.getStringExtra("idw");
        String weight = intent.getStringExtra("weight");
        String datew = intent.getStringExtra("datew");

        _idw= Long.parseLong(idw);

        weightText.setText(weight);

        datewText.setText(datew);

        deleteBtnw.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_deletew:
                dbWeightManager.deletew(_idw);
                this.returnHome();
                break;

        }
    }

    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(),
                WeightListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);



    }
}