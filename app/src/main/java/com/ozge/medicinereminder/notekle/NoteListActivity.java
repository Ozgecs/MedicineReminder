package com.ozge.medicinereminder.notekle;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ozge.medicinereminder.DatabaseIslemleri.DBManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class NoteListActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DatabaseHelperb._ID,
            DatabaseHelperb.SUBJECT, DatabaseHelperb.DESC};

    final int[] to = new int[] {R.id.id, R.id.title, R.id.desc};
    //BU KISIM MAİN OLARAK GEÇECEK DİĞER KISMI SİL NOTEKLE KISMI SİLİNDİ.

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_emp_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record,
                cursor,from,to,0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        getSupportActionBar().setTitle("NOTLAR");



        //OnClickLister for List Items:
        listView.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView idTextView = view.findViewById(R.id.id);
            TextView titleTextView = view.findViewById(R.id.title);
            TextView descTextView = view.findViewById(R.id.desc);

            String id = idTextView.getText().toString();
            String title = titleTextView.getText().toString();
            String desc = descTextView.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(), ModifyNoteActivity.class);

            modify_intent.putExtra("title",title);
            modify_intent.putExtra("desc",desc);
            modify_intent.putExtra("id",id);

            startActivity(modify_intent);

        });


    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id== R.id.add_record){

            Intent add_mem = new Intent(this, AddNoteActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}