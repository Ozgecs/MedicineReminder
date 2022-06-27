package com.ozge.medicinereminder.tansiyoekle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBBPManager;
import com.ozge.medicinereminder.R;


public class ModifyBpActivity extends Activity implements View.OnClickListener {

    //Widgets
    private EditText btText;
    private Button deleteBtnBP;
    private EditText ktText;
    private EditText datebpText;
    private Long _idbp;
    private DBBPManager dbbpManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bp);

        dbbpManager = new DBBPManager(this);
        dbbpManager.open();

        btText = findViewById(R.id.bt_edittext);
        ktText = findViewById(R.id.kt_edittext);
        datebpText = findViewById(R.id.datebp_edittext);

        deleteBtnBP = findViewById(R.id.addbp_delete);
        datebpText.setOnClickListener(this);

        Intent intentbp = getIntent();
        String idbp = intentbp.getStringExtra("idbp");
        String bt = intentbp.getStringExtra("bt");
        String kt = intentbp.getStringExtra("kt");
        String datebp = intentbp.getStringExtra("datebp");


        _idbp = Long.parseLong(idbp);

        btText.setText(bt);
        ktText.setText(kt);
        datebpText.setText(datebp);

        deleteBtnBP.setOnClickListener(this);




    }
    @Override
    public void onClick(View m){
        switch (m.getId()){
            case R.id.addbp_delete:
                dbbpManager.deletebp(_idbp);
                this.returnHome();
                break;
        }

    }
    public void returnHome(){

        Intent home_intent = new Intent(getApplicationContext(),
                BpListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);



    }
}