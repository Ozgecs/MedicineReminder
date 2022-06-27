package com.ozge.medicinereminder.DatabaseIslemleri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBDoctorManager {

    private DatabaseHelperb dbHelper;
    private Context contextd;
    private SQLiteDatabase databased;

    public DBDoctorManager(Context cd){
        contextd = cd;
    }

    public DBDoctorManager open() throws SQLException {
        dbHelper = new DatabaseHelperb(contextd);
        databased = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insertd(String namesurname, String expertıse, String pnumber, String address){
        ContentValues contentValuesd = new ContentValues();
        contentValuesd.put(DatabaseHelperb.NAMESURNAME, namesurname);
        contentValuesd.put(DatabaseHelperb.EXPERTISE, expertıse);
        contentValuesd.put(DatabaseHelperb.PNUMBER, pnumber);
        contentValuesd.put(DatabaseHelperb.ADDRESS, address);

        databased.insert(DatabaseHelperb.TABLE_NAME_DOCTORS, null, contentValuesd);
    }
    public Cursor fetchd() {
        String[] columns = new String[] {DatabaseHelperb._IDD,
                DatabaseHelperb.NAMESURNAME, DatabaseHelperb.EXPERTISE, DatabaseHelperb.PNUMBER, DatabaseHelperb.ADDRESS};

        Cursor cursord = databased.query(DatabaseHelperb.TABLE_NAME_DOCTORS,
                columns,
                null,
                null,
                null,
                null,
                null);
        if(cursord != null){
            cursord.moveToFirst();
        }
        return cursord;
    }
    public  int updated(long _idd, String namesurname, String expertıse, String pnumber, String address){
        ContentValues contentValuesd = new ContentValues();
        contentValuesd.put(DatabaseHelperb.NAMESURNAME, namesurname);
        contentValuesd.put(DatabaseHelperb.EXPERTISE, expertıse);
        contentValuesd.put(DatabaseHelperb.PNUMBER, pnumber);
        contentValuesd.put(DatabaseHelperb.ADDRESS, address);


        int i_d = databased.update(DatabaseHelperb.TABLE_NAME_DOCTORS,
                contentValuesd, DatabaseHelperb._IDD +
                        " = " + _idd, null);
        return i_d;
    }

    public  void  deleted(long _id){
        databased.delete(DatabaseHelperb.TABLE_NAME_DOCTORS, DatabaseHelperb._IDD
                + " = " + _id ,null );
    }

}
