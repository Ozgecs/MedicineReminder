package com.ozge.medicinereminder.doktorekle;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ozge.medicinereminder.DatabaseIslemleri.DBDoctorManager;
import com.ozge.medicinereminder.R;


public class AddDoctorActivity extends Activity implements View.OnClickListener {

    //Widgets
    private Button adddTodoButton;
    private EditText namesurnameEditText;
    private EditText expertıseEditText;
    private EditText pnumberEditText;
    private EditText addressEditText;
    private DBDoctorManager dbDoctorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Kayıt Ekranı");
        setContentView(R.layout.activity_add_doctor);

        //Instantiation of widgets:
        namesurnameEditText = findViewById(R.id.namesurname_edittext);
        expertıseEditText = findViewById(R.id.expertise_edittext);
        pnumberEditText = findViewById(R.id.pnumber_edittext);
        addressEditText = findViewById(R.id.address_edittext);
        adddTodoButton = findViewById(R.id.addd_record);


        dbDoctorManager = new DBDoctorManager(this);
        dbDoctorManager.open();
        adddTodoButton.setOnClickListener(this);


    }
    @Override
    public void onClick(View d) {
        switch (d.getId()){
            case R.id.addd_record:
                if (TextUtils.isEmpty(namesurnameEditText.getText().toString()) && TextUtils.isEmpty(expertıseEditText.getText().toString())){
                    Toast.makeText(AddDoctorActivity.this, "Boş Bırakmayınız!",
                            Toast.LENGTH_SHORT).show();
                }else {
                    final String namesurname = namesurnameEditText.getText().toString();
                    final String expertıse = expertıseEditText.getText().toString();
                    final String pnumber = pnumberEditText.getText().toString();
                    final String address = addressEditText.getText().toString();
                    dbDoctorManager.insertd(namesurname,expertıse,pnumber,address);

                }

                Intent maind = new Intent (AddDoctorActivity.this,
                        DoctorListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(maind);
                break;

        }
    }
}