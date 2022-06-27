package com.ozge.medicinereminder.tansiyoekle;

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

import com.ozge.medicinereminder.DatabaseIslemleri.DBBPManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class BpListActivity extends AppCompatActivity {

    private DBBPManager dbbpManager;
    private ListView listViewbp;
    private SimpleCursorAdapter adapterbp;
    ActionBar actionBarbp;
    final String[] from = new String[]{DatabaseHelperb._IDBP,
            DatabaseHelperb.BPB, DatabaseHelperb.BPK, DatabaseHelperb.DATEBP};
    final int[] to = new int[]{R.id.idbp, R.id.bttansion, R.id.kttansion, R.id.daybp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bp_list);

        dbbpManager = new DBBPManager(this);
        dbbpManager.open();
        Cursor cursorbp = dbbpManager.fetchbp();

        listViewbp = findViewById(R.id.list_viewbp);
        listViewbp.setEmptyView(findViewById(R.id.emptybp));

        adapterbp = new SimpleCursorAdapter(this, R.layout.activity_bp_record,
                cursorbp, from, to, 0);
        adapterbp.notifyDataSetChanged();

        listViewbp.setAdapter(adapterbp);
        actionBarbp = getSupportActionBar();
        actionBarbp.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0000")));
        getSupportActionBar().setTitle("TANSİYON ÖLÇÜMÜ");

        //OnClickLister for List Items:
        listViewbp.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView idbpTextView = view.findViewById(R.id.idbp);
            TextView btTextView = view.findViewById(R.id.bttansion);
            TextView ktTextView = view.findViewById(R.id.kttansion);
            TextView datebpTextView = view.findViewById(R.id.daybp);

            String idbp = idbpTextView.getText().toString();
            String bt = btTextView.getText().toString();
            String kt = ktTextView.getText().toString();
            String daybp = datebpTextView.getText().toString();

            Intent modifybp = new Intent(getApplicationContext(), ModifyBpActivity.class);


            modifybp.putExtra("bt", bt);
            modifybp.putExtra("kt", kt);
            modifybp.putExtra("datebp", daybp);
            modifybp.putExtra("idbp", idbp);

            startActivity(modifybp);
        });
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menubp, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();
        if(id== R.id.addbp_record){

            Intent add_mem = new Intent(this, AddBpActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}



