package com.ozge.medicinereminder.kiloekle;

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

import com.ozge.medicinereminder.DatabaseIslemleri.DBWeightManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class WeightListActivity extends AppCompatActivity {

    private DBWeightManager dbWeightManager;
    private ListView listVieww;
    private SimpleCursorAdapter adapterw;

    final String[] from = new String[] {DatabaseHelperb._IDW,
            DatabaseHelperb.WEIGHT, DatabaseHelperb.DATEW};

    final int[] to = new int[] {R.id.idw, R.id.weight, R.id.datew};
    //BU KISIM MAİN OLARAK GEÇECEK DİĞER KISMI SİL NOTEKLE KISMI SİLİNDİ.

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_weight_list);

        dbWeightManager = new DBWeightManager(this);
        dbWeightManager.open();
        Cursor cursorw = dbWeightManager.fetchw();

        listVieww = findViewById(R.id.list_vieww);
        listVieww.setEmptyView(findViewById(R.id.emptyw));

        adapterw = new SimpleCursorAdapter(this, R.layout.activity_weight_record,
                cursorw,from,to,0);
        adapterw.notifyDataSetChanged();

        listVieww.setAdapter(adapterw);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
        getSupportActionBar().setTitle("KİLO ÖLÇÜMÜ");

        //Listedeki herhangi bir ögeye tıklandığı zaman
        listVieww.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView idwTextView = view.findViewById(R.id.idw);
            TextView weightTextView = view.findViewById(R.id.weight);
            TextView datewTextView = view.findViewById(R.id.datew);

            String idw = idwTextView.getText().toString();
            String weight = weightTextView.getText().toString();
            String datew = datewTextView.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(), ModifyWeightActivity.class);

            modify_intent.putExtra("weight",weight);
            modify_intent.putExtra("datew",datew);
            modify_intent.putExtra("idw",idw);

            startActivity(modify_intent);

        });
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuw, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id== R.id.addw_record){

            Intent add_memw = new Intent(this, AddWeightActivity.class);
            startActivity(add_memw);
        }
        return super.onOptionsItemSelected(item);
    }
}