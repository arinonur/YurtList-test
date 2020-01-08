package com.example.yurtlist.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yurtlist.Dorms;
import com.example.yurtlist.DormsAnkara;
import com.example.yurtlist.DormsIstanbul;
import com.example.yurtlist.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DormsHelperAnkara extends SQLiteOpenHelper {

    private static final String DB_NAME = "Dorms_Ankara"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    public DormsHelperAnkara(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DORM_ANKARA (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PROVINCE TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER);");

        insertDorm(db, "Özel Ulu Çınar Erkek Öğrenci Yurdu", "Ankara", R.drawable.cinar);
        insertDorm(db, "Çaba Kız Öğrenci Yurdu", "Ankara", R.drawable.caba);
        insertDorm(db, "Fırat Yüksek Öğrenim Erkek Öğrenci Yurdu", "Ankara", R.drawable.firat);
        insertDorm(db, "Akköprü Erkek Öğrenci Yurdu", "Ankara", R.drawable.akkopru);
        insertDorm(db, "Koç Yaşam Kız Öğrenci Yurdu", "Ankara", R.drawable.koc);
        insertDorm(db, "Özel Başkent Erkek Öğrenci Yurdu", "Ankara", R.drawable.baskent);

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
        db.insert("DORM_ANKARA", null, dormValues);
    }

    public ArrayList<DormsAnkara> dormListAnkara() {
        String query;
        query = "SELECT  * FROM DORM_ANKARA" ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DormsAnkara> personLinkedList = new ArrayList<DormsAnkara>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new DormsAnkara(name,province,img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

}
