package com.ozge.medicinereminder.DatabaseIslemleri;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBBPManager {

    //tansiyon veritabanının yönetici sınıfı. Tablonun açılması, kapatılması

    private DatabaseHelperb dbHelper;
    private Context contextbp;
    private SQLiteDatabase database;


    //Constructor
    public DBBPManager(Context cbp){
        contextbp = cbp;
    }




    public DBBPManager open() throws SQLException{
        dbHelper = new DatabaseHelperb(contextbp);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        dbHelper.close();
    }

    public void insertbp(String bt, String kt, String daybp){ //tablonun veri ekleme fonk.
        ContentValues contentValuesbp = new ContentValues();
        contentValuesbp.put(DatabaseHelperb.BPB, bt);
        contentValuesbp.put(DatabaseHelperb.BPK, kt);
        contentValuesbp.put(DatabaseHelperb.DATEBP, daybp);
        database.insert(DatabaseHelperb.TABLE_NAME_BP, null, contentValuesbp);
    }

    public Cursor fetchbp() { //veriyi getiren fonk (listview kısımlarında kullanılacak)
        String[] columns = new String[] {DatabaseHelperb._IDBP,
                DatabaseHelperb.BPB, DatabaseHelperb.BPK, DatabaseHelperb.DATEBP};

        Cursor cursorbp = database.query(DatabaseHelperb.TABLE_NAME_BP,
                columns,
                null,
                null,
                null,
                null,
                null);
        if(cursorbp != null){
            cursorbp.moveToFirst();
        }
        return cursorbp;
    }
    public  int updatebp(long _idbp, String bt, String kt, String daybp){  //tablo güncelleme(Ölçümlerde ve randevuda hata verdiği için kullanmadım.)
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperb.BPB, bt);
        contentValues.put(DatabaseHelperb.BPK, kt);
        contentValues.put(DatabaseHelperb.DATEBP, daybp);

        int i = database.update(DatabaseHelperb.TABLE_NAME_BP,
                contentValues, DatabaseHelperb._IDP +
                        " = " + _idbp, null);
        return i;
    }

    public  void  deletebp(long _idbp){  //veri silme id'ye göre.
        database.delete(DatabaseHelperb.TABLE_NAME_BP, DatabaseHelperb._IDBP
                + " = " + _idbp ,null );
    }

}