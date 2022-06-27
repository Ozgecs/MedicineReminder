package com.ozge.medicinereminder.notekle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ozge.medicinereminder.DatabaseIslemleri.DBManager;
import com.ozge.medicinereminder.R;


public class AddNoteActivity extends Activity implements View.OnClickListener {


    //Widgets:
    private Button addTodoButton;
    private EditText subjectEditText;
    private EditText descEditText;
    private DBManager dbManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Kayıt Ekranı");
        setContentView(R.layout.activity_add_note);

        //Instantiation of widgets:
        subjectEditText = findViewById(R.id.subject_edittext);
        descEditText = findViewById(R.id.description_edittext);
        addTodoButton = findViewById(R.id.add_record);


        dbManager = new DBManager(this);
        dbManager.open();
        addTodoButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_record:
                if (TextUtils.isEmpty(subjectEditText.getText().toString()) && TextUtils.isEmpty(descEditText.getText().toString())){
                    Toast.makeText(AddNoteActivity.this, "Boş Bırakmayınız!",
                    Toast.LENGTH_SHORT).show();
                }else {
                    final String name = subjectEditText.getText().toString();
                    final String desc = descEditText.getText().toString();
                    dbManager.insert(name,desc);

                }

                Intent main = new Intent (AddNoteActivity.this,
                        NoteListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

        }
    }
}