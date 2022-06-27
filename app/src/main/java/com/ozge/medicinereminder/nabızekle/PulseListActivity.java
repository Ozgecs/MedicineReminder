package com.ozge.medicinereminder.nabızekle;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ozge.medicinereminder.DatabaseIslemleri.DBPulseManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class PulseListActivity extends AppCompatActivity {

    private DBPulseManager dbPulseManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelperb._IDP,
            DatabaseHelperb.PULSE, DatabaseHelperb.DATEP};

    final int[] to = new int[] {R.id.idp, R.id.pulse, R.id.datep};
    //BU KISIM MAİN OLARAK GEÇECEK DİĞER KISMI SİL NOTEKLE KISMI SİLİNDİ.

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pulse_list);

        dbPulseManager = new DBPulseManager(this);
        dbPulseManager.open();
        Cursor cursor = dbPulseManager.fetchp();

        listView = findViewById(R.id.list_viewp);
        listView.setEmptyView(findViewById(R.id.emptyp));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_pulse_record,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
        getSupportActionBar().setTitle("NABIZ ÖLÇÜMÜ");



        //OnClickLister for List Items:
        listView.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView idpTextView = view.findViewById(R.id.idp);
            TextView pulseTextView = view.findViewById(R.id.pulse);
            TextView datepTextView = view.findViewById(R.id.datep);

            String idp = idpTextView.getText().toString();
            String pulse = pulseTextView.getText().toString();
            String datep = datepTextView.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(), ModifyPulseActivity.class);

            modify_intent.putExtra("pulse",pulse);
            modify_intent.putExtra("datep",datep);
            modify_intent.putExtra("idp",idp);

            startActivity(modify_intent);

        });


    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menup, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id== R.id.addp_record){

            Intent add_mem = new Intent(this, AddPulseActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}