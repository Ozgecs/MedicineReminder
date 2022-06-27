package com.ozge.medicinereminder.DatabaseIslemleri;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBManager {

    private DatabaseHelperb dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //Constructor
    public DBManager(Context c){
        context = c;
    }




    public DBManager open() throws SQLException{
        dbHelper = new DatabaseHelperb(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperb.SUBJECT, name);
        contentValues.put(DatabaseHelperb.DESC, desc);
        database.insert(DatabaseHelperb.TABLE_NAME_NOTE, null, contentValues);
    }

    public Cursor fetch() {
        String[] columns = new String[] {DatabaseHelperb._ID,
                DatabaseHelperb.SUBJECT, DatabaseHelperb.DESC};

        Cursor cursor = database.query(DatabaseHelperb.TABLE_NAME_NOTE,
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
    public  int update(long _id, String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperb.SUBJECT, name);
        contentValues.put(DatabaseHelperb.DESC, desc);

        int i = database.update(DatabaseHelperb.TABLE_NAME_NOTE,
                contentValues, DatabaseHelperb._ID +
                        " = " + _id, null);
        return i;
    }

    public  void  delete(long _id){
        database.delete(DatabaseHelperb.TABLE_NAME_NOTE, DatabaseHelperb._ID
                + " = " + _id ,null );
    }
}
