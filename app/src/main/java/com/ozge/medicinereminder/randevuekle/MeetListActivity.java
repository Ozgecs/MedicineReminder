package com.ozge.medicinereminder.randevuekle;

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

import com.ozge.medicinereminder.DatabaseIslemleri.DBMeetManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class MeetListActivity extends AppCompatActivity {

    private DBMeetManager dbMeetManager;
    private ListView listViewm;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelperb._IDM,
            DatabaseHelperb.NOTE, DatabaseHelperb.DATE, DatabaseHelperb.MADDRESS};
    final int[] to = new int[] {R.id.idm, R.id.meetname, R.id.day, R.id.maddress};



    ActionBar actionBarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_meet_list);

        dbMeetManager = new DBMeetManager(this);
        dbMeetManager.open();
        Cursor cursor = dbMeetManager.fetchm();

        listViewm = findViewById(R.id.list_viewm);
        listViewm.setEmptyView(findViewById(R.id.emptym));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_meet_record,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();



        listViewm.setAdapter(adapter);
        actionBarm = getSupportActionBar();
        actionBarm.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
        getSupportActionBar().setTitle("RANDEVULAR");

        //OnClickLister for List Items:
        listViewm.setOnItemClickListener((parent, view, position, viewid) -> {
             TextView idTextView = view.findViewById(R.id.idm);
             TextView meetnameTextView = view.findViewById(R.id.meetname);
             TextView dateTextView = view.findViewById(R.id.day);
             TextView maddressTextView = view.findViewById(R.id.maddress);

             String idm = idTextView.getText().toString();
             String meetname = meetnameTextView.getText().toString();
             String date = dateTextView.getText().toString();
             String maddress = maddressTextView.getText().toString();

             Intent modify = new Intent(getApplicationContext(), ModifyMeetActivity.class);


            modify.putExtra("meetname",meetname);
            modify.putExtra("date",date);
            modify.putExtra("maddress",maddress);
            modify.putExtra("id",idm);

            startActivity(modify);
        });
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menum, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id== R.id.addm_record){

            Intent add_mem = new Intent(this, AddMeetActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}