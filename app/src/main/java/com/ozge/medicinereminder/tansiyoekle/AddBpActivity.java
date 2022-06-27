package com.ozge.medicinereminder.tansiyoekle;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ozge.medicinereminder.DatabaseIslemleri.DBBPManager;
import com.ozge.medicinereminder.R;

import java.util.Calendar;

public class AddBpActivity extends Activity implements View.OnClickListener {

    private Button addbpButton;
    private EditText btTansiyonEditText;
    public int bpYear, bpMonth, bpDay;
    private EditText ktTansiyonEditText;
    private EditText datebpEditText;
    private DBBPManager dbbpManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bp);

        btTansiyonEditText = findViewById(R.id.bt_edittext);
        ktTansiyonEditText = findViewById(R.id.kt_edittext);
        datebpEditText = findViewById(R.id.datebp_edittext);
        addbpButton = findViewById(R.id.addbp_record);

        dbbpManager = new DBBPManager(this);
        dbbpManager.open();
        addbpButton.setOnClickListener(this);
        datebpEditText.setOnClickListener(this);




    }
    @Override
    public void onClick(View m){
        switch (m.getId()){
            case R.id.addbp_record:
                final String bt = btTansiyonEditText.getText().toString();
                final String kt = ktTansiyonEditText.getText().toString();
                final String daybp = datebpEditText.getText().toString();

                dbbpManager.insertbp(bt,kt,daybp);

                Intent intent = new Intent(getApplicationContext(),
                        BpListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);


            case R.id.datebp_edittext:
                final Calendar calendar2 = Calendar.getInstance ();
                bpYear = calendar2.get ( Calendar.YEAR );
                bpMonth = calendar2.get ( Calendar.MONTH );
                bpDay = calendar2.get ( Calendar.DAY_OF_MONTH );

                //show dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog ( this, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        datebpEditText.setText ( dayOfMonth + "/" + (month + 1) + "/" + year );


                    }
                }, bpYear, bpMonth, bpDay );
                datePickerDialog.show ();

        }

    }
}