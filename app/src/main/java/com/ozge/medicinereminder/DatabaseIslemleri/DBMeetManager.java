package com.ozge.medicinereminder.DatabaseIslemleri;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBMeetManager {

    private DatabaseHelperb databaseHelper;
    private Context contextm;
    private SQLiteDatabase databasem;

    public DBMeetManager(Context cm){
        contextm = cm;
    }

    public DBMeetManager open() throws SQLException {
        databaseHelper = new DatabaseHelperb(contextm);
        databasem = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public void insertm(String note, String day ,String maddress){
        ContentValues contentValuesm = new ContentValues();
        contentValuesm.put(DatabaseHelperb.NOTE, note);
        contentValuesm.put(DatabaseHelperb.DATE, day);
        contentValuesm.put(DatabaseHelperb.MADDRESS, maddress);

        databasem.insert(DatabaseHelperb.TABLE_NAME_MEETINGS,null,contentValuesm);

    }
    public Cursor fetchm() {
        String[] columns = new String[] {DatabaseHelperb._IDM,
                DatabaseHelperb.NOTE, DatabaseHelperb.DATE, DatabaseHelperb.MADDRESS};

        Cursor cursord = databasem.query(DatabaseHelperb.TABLE_NAME_MEETINGS,
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
    public  int updatem(long _idm, String note, String day, String maddress){
        ContentValues contentValuesd = new ContentValues();
        contentValuesd.put(DatabaseHelperb.NOTE, note);
        contentValuesd.put(DatabaseHelperb.DATE, day);
        contentValuesd.put(DatabaseHelperb.MADDRESS, maddress);



        int i_d = databasem.update(DatabaseHelperb.TABLE_NAME_DOCTORS,
                contentValuesd, DatabaseHelperb._IDM +
                        " = " + _idm, null);
        return i_d;
    }
    public  void  deletem(long _idm){
        databasem.delete(DatabaseHelperb.TABLE_NAME_MEETINGS, DatabaseHelperb._IDM
                + " = " + _idm ,null );
    }

}
