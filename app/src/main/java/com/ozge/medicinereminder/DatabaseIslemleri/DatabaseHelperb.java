package com.ozge.medicinereminder.DatabaseIslemleri;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperb extends SQLiteOpenHelper {

    /*Bilgi paneli ekranında girilecek olan verilerin tutulacağı tablolar ve veritabanı burada oluşturuldu.*/

    //DB NAME
    static final String DB_NAME = "Infonew.DB";
    static final int DB_VERSION = 1;
    //Table Note
    public static final String TABLE_NAME_NOTE = "NOTES";
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    //Table Doctors
    public static final String TABLE_NAME_DOCTORS = "DOCTORS";
    public static final String _IDD = "_id";
    public static final String NAMESURNAME = "namesurname";
    public static final String EXPERTISE = "expertıse";
    public static final String PNUMBER = "pnumber";
    public static final String ADDRESS = "address";
    //Table Meeting
    public static final String TABLE_NAME_MEETINGS = "MEETINGS";
    public static final String _IDM = "_id";
    public static final String NOTE = "note";
    public static final String DATE = "date";
    public static final String MADDRESS = "maddress";
    //Table-nabız
    public static final String TABLE_NAME_PULSE = "PULSE";
    public static final String _IDP = "_id";
    public static final String PULSE = "pulse";
    public static final String DATEP = "datep";
    //Table-kilo
    public static final String TABLE_NAME_WEIGHT = "WEIGHT";
    public static final String _IDW = "_id";
    public static final String WEIGHT = "weıght";
    public static final String DATEW = "datew";
    //Table-tansiyon
    public static final String TABLE_NAME_BP = "BP";
    public static final String _IDBP = "_id";
    public static final String BPK = "bpk";
    public static final String BPB = "bpb";
    public static final String DATEBP = "datebp";

    //Tabloların oluşturulmasını sağlayan kodlar
    //NOTES
    private static final String CREATE_TABLE_NOTES = "create table " +
            TABLE_NAME_NOTE + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL, " + DESC + " TEXT);";
    //DOCTORS
    private static final String CREATE_TABLE_DOCTORS = "create table " +
            TABLE_NAME_DOCTORS + "(" + _IDD
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMESURNAME +
            " TEXT NOT NULL, " + EXPERTISE + " TEXT, " + PNUMBER + " TEXT, " + ADDRESS + " TEXT);";
    //MEETINGS
    private static final String CREATE_TABLE_MEETINGS = "create table " +
            TABLE_NAME_MEETINGS + "(" + _IDM
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE +
            " TEXT NOT NULL, " + DATE + " TEXT, " + MADDRESS + " TEXT);";

    //PULSE
    private static final String CREATE_TABLE_PULSE = "create table " +
            TABLE_NAME_PULSE + "(" + _IDP
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PULSE +
            " TEXT NOT NULL, " + DATEP + " TEXT);";


    //WEIGHT
    private static final String CREATE_TABLE_WEIGHT = "create table " +
            TABLE_NAME_WEIGHT + "(" + _IDW
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WEIGHT +
            " TEXT NOT NULL, " + DATEW + " TEXT);";


    //TANSİYON
    private static final String CREATE_TABLE_BP = "create table " +
            TABLE_NAME_BP + "(" + _IDBP
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + BPK +
            " TEXT NOT NULL, " + BPB + " TEXT, " + DATEBP + " TEXT);";


    public DatabaseHelperb(Context context) {   //database oluşturan constructor
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //uygulama çalıştırıldığı zaman tablolar bu metotla oluşturulur
        //Creating Table
        db.execSQL(CREATE_TABLE_NOTES);
        db.execSQL(CREATE_TABLE_DOCTORS);
        db.execSQL(CREATE_TABLE_MEETINGS);
        db.execSQL(CREATE_TABLE_PULSE);
        db.execSQL(CREATE_TABLE_WEIGHT);
        db.execSQL(CREATE_TABLE_BP);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //Tablo kaldırma
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCTORS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEETINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PULSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BP);
        onCreate(db);


    }

}


