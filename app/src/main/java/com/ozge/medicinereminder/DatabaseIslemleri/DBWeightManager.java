package com.ozge.medicinereminder.DatabaseIslemleri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBWeightManager {

    private DatabaseHelperb dbHelper;
    private Context context;
    private SQLiteDatabase databasew;

    //yapıcı metot
    public DBWeightManager(Context cw){
        context = cw;
    }

    public DBWeightManager open() throws SQLException{
        dbHelper = new DatabaseHelperb(context);
        databasew = dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void insertw(String weight, String datew){
        ContentValues contentValuesw = new ContentValues();
        contentValuesw.put(DatabaseHelperb.WEIGHT, weight);
        contentValuesw.put(DatabaseHelperb.DATEW, datew);
        databasew.insert(DatabaseHelperb.TABLE_NAME_WEIGHT, null, contentValuesw);
    }

    public Cursor fetchw() {
        String[] columns = new String[] {DatabaseHelperb._IDW,
                DatabaseHelperb.WEIGHT, DatabaseHelperb.DATEW};

        Cursor cursor = databasew.query(DatabaseHelperb.TABLE_NAME_WEIGHT,
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

    public  void  deletew(long _idp){
        databasew.delete(DatabaseHelperb.TABLE_NAME_WEIGHT, DatabaseHelperb._IDW
                + " = " + _idp ,null );
    }
}