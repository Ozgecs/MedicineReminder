package com.ozge.medicinereminder.DatabaseIslemleri;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBPulseManager {

    private DatabaseHelperb dbHelper;
    private Context context;
    private SQLiteDatabase database;


    public DBPulseManager(Context cn){
        context = cn;
    }




    public DBPulseManager open() throws SQLException{
        dbHelper = new DatabaseHelperb(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void insertp(String pulse, String datep){
        ContentValues contentValuesp = new ContentValues();
        contentValuesp.put(DatabaseHelperb.PULSE, pulse);
        contentValuesp.put(DatabaseHelperb.DATEP, datep);
        database.insert(DatabaseHelperb.TABLE_NAME_PULSE, null, contentValuesp);
    }

    public Cursor fetchp() {
        String[] columns = new String[] {DatabaseHelperb._IDP,
                DatabaseHelperb.PULSE, DatabaseHelperb.DATEP};

        Cursor cursor = database.query(DatabaseHelperb.TABLE_NAME_PULSE,
                columns,
                null,
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public  int updatep(long _idp, String pulse, String datep){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperb.PULSE, pulse);
        contentValues.put(DatabaseHelperb.DATEP, datep);

        int i = database.update(DatabaseHelperb.TABLE_NAME_PULSE,
                contentValues, DatabaseHelperb._IDP +
                        " = " + _idp, null);
        return i;
    }

    public  void  deletep(long _idp){
        database.delete(DatabaseHelperb.TABLE_NAME_PULSE, DatabaseHelperb._IDP
                + " = " + _idp ,null );
    }
}
