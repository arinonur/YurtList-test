package com.example.yurtlist.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yurtlist.DormsAnkara;
import com.example.yurtlist.DormsIstanbul;
import com.example.yurtlist.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DormsHelperIstanbul extends SQLiteOpenHelper {

    private static final String DB_NAME = "Dorms_Istanbul"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public DormsHelperIstanbul(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DORM_ISTANBUL (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PROVINCE TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

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
        db.insert("DORM_ISTANBUL", null, dormValues);
    }

    public ArrayList<DormsIstanbul> dormListIstanbul() {
        String query;
        query = "SELECT  * FROM DORM_ISTANBUL" ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DormsIstanbul> personLinkedList = new ArrayList<DormsIstanbul>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new DormsIstanbul(name,province,img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

}
