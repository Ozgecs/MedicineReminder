package com.ozge.medicinereminder.doktorekle;

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

import com.ozge.medicinereminder.DatabaseIslemleri.DBDoctorManager;
import com.ozge.medicinereminder.DatabaseIslemleri.DatabaseHelperb;
import com.ozge.medicinereminder.R;


public class DoctorListActivity extends AppCompatActivity {

    private DBDoctorManager dbDoctorManager;
    private ListView listViewd;
    private SimpleCursorAdapter adapterd;
    ActionBar actionBard;

    final String[] from = new String[] {DatabaseHelperb._IDD,
            DatabaseHelperb.NAMESURNAME, DatabaseHelperb.EXPERTISE, DatabaseHelperb.PNUMBER, DatabaseHelperb.ADDRESS};

    final int[] to = new int[] {R.id.idd, R.id.namesurname, R.id.expertıse, R.id.pnumber, R.id.address};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_doc_list);
        getSupportActionBar().setTitle("DOKTORLAR");

        dbDoctorManager = new DBDoctorManager(this);
        dbDoctorManager.open();
        Cursor cursord = dbDoctorManager.fetchd();

        listViewd = findViewById(R.id.list_viewd);
        listViewd.setEmptyView(findViewById(R.id.emptyd));

        adapterd = new SimpleCursorAdapter(this, R.layout.activity_doctor_record,
                cursord,from,to,0);
        adapterd.notifyDataSetChanged();

        listViewd.setAdapter(adapterd);






        listViewd.setOnItemClickListener((parent, view, position, viewid) -> {
            TextView iddTextView = view.findViewById(R.id.idd);
            TextView namesurnameTextView = view.findViewById(R.id.namesurname);
            TextView expertıseTextView = view.findViewById(R.id.expertıse);
            TextView pnumberTextView = view.findViewById(R.id.pnumber);
            TextView addressTextView = view.findViewById(R.id.address);


            String idd = iddTextView.getText().toString();
            String namesurname = namesurnameTextView.getText().toString();
            String expertıse = expertıseTextView.getText().toString();
            String pnumber = pnumberTextView.getText().toString();
            String address = addressTextView.getText().toString();

            Intent modify_intentd = new Intent(getApplicationContext(), ModifyDoctorActivity.class);

            modify_intentd.putExtra("namesurname",namesurname);
            modify_intentd.putExtra("expertıse",expertıse);
            modify_intentd.putExtra("pnumber",pnumber);
            modify_intentd.putExtra("address",address);
            modify_intentd.putExtra("id",idd);

            startActivity(modify_intentd);

        });

    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menud){
        getMenuInflater().inflate(R.menu.menud, menud);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemd){
        int idd = itemd.getItemId();
        if(idd== R.id.addd_record){

            Intent add_memd = new Intent(this, AddDoctorActivity.class);
            startActivity(add_memd);
        }
        return super.onOptionsItemSelected(itemd);
    }
}