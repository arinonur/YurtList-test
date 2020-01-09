package com.example.yurtlist.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yurtlist.Dorms;
import com.example.yurtlist.R;

import java.util.ArrayList;


public class DormsHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Dorms"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public DormsHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DORM (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PROVINCE TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");
        insertDorm(db, "Kılıçoğlu Öğrenci Yurdu", "Izmir", R.drawable.kilicoglu);
        insertDorm(db, "Egeyurt Kız Öğrenci Yurdu", "Izmir",
                R.drawable.egeyurt);
        insertDorm(db, "Ilgaz Öğrenci Yurdu", "Izmir", R.drawable.ilgaz);
        insertDorm(db, "Palmira Rezidans Kız Öğrenci Yurdu", "Izmir", R.drawable.palmira);
        insertDorm(db, "İYTE Yaşam Merkezi", "Izmir", R.drawable.iyte);
        insertDorm(db, "Yükseliş Öğrenci Yurdu", "Izmir", R.drawable.yukselis);
        insertDorm(db, "Özel Ulu Çınar Erkek Öğrenci Yurdu", "Ankara", R.drawable.cinar);
        insertDorm(db, "Çaba Kız Öğrenci Yurdu", "Ankara", R.drawable.caba);
        insertDorm(db, "Fırat Yüksek Öğrenim Erkek Öğrenci Yurdu", "Ankara", R.drawable.firat);
        insertDorm(db, "Akköprü Erkek Öğrenci Yurdu", "Ankara", R.drawable.akkopru);
        insertDorm(db, "Koç Yaşam Kız Öğrenci Yurdu", "Ankara", R.drawable.koc);
        insertDorm(db, "Özel Başkent Erkek Öğrenci Yurdu", "Ankara", R.drawable.baskent);
        insertDorm(db, "Esenyurt İstasyon Kız Öğrenci Yurdu", "Istanbul", R.drawable.esenyurt);
        insertDorm(db, "Bağcılar Eda Kız Öğrenci Yurdu", "Istanbul", R.drawable.bagcilar);
        insertDorm(db, "Eyüp Studio Santral Öğrenci Yurdu", "Istanbul", R.drawable.eyup);
        insertDorm(db, "Zeytinburnu Novu Öğrenci Rezidansı", "Istanbul", R.drawable.zeytinburnu);
        insertDorm(db, "Kadıköy Yıldız Kız Öğrenci Yurdu\n", "Istanbul", R.drawable.kadikoy);
        insertDorm(db, "Dormia İstanbul Kız Öğrenci Yurdu", "Istanbul", R.drawable.dormia);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private static void insertDorm(SQLiteDatabase db, String name, String province,
                                   int resourceId) {
        ContentValues dormValues = new ContentValues();
        dormValues.put("NAME", name);
        dormValues.put("PROVINCE", province);
        dormValues.put("IMAGE_RESOURCE_ID", resourceId);
        db.insert("DORM", null, dormValues);
    }

    public ArrayList<Dorms> dormList() {
        String query;
        query = "SELECT * FROM DORM";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Dorms> personLinkedList = new ArrayList<Dorms>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new Dorms(name, province, img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

    public ArrayList<Dorms> dormListIzmir() {
        String query;
        query = "SELECT * FROM DORM WHERE PROVINCE like 'Izmir'";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Dorms> personLinkedList = new ArrayList<Dorms>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new Dorms(name, province, img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

}
