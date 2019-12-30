package com.example.yurtlist.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yurtlist.Dorms;
import com.example.yurtlist.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "Yaşar Üniversitesi Öğrenci Yurdu", "Izmir", R.drawable.yasar);
        insertDorm(db, "test", "Istanbul", R.drawable.yasar);
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
        //regular query
        query = "SELECT * FROM DORM" ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Dorms> personLinkedList = new ArrayList<Dorms>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new Dorms(name,province,img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

    public ArrayList<Dorms> dormListIzmir() {
        String query;
        //regular query
        query = "SELECT  NAME,PROVINCE,IMAGE_RESOURCE_ID FROM DORM WHERE PROVINCE like 'Izmir'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Dorms> personLinkedList = new ArrayList<Dorms>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()){
                String name = cursor.getString(0);
                String province = cursor.getString(1);
                int img_resource_id = Integer.parseInt(cursor.getString(2));
                personLinkedList.add(new Dorms(name,province,img_resource_id));
                cursor.moveToNext();
            }
        }
        cursor.close();


        return personLinkedList;
    }
    public ArrayList<Dorms> dormListIstanbul() {
        String query;
        //regular query
        query = "SELECT  * FROM DORM WHERE PROVINCE like 'Istanbul'" ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Dorms> personLinkedList = new ArrayList<Dorms>();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String province = cursor.getString(2);
                int img_resource_id = Integer.parseInt(cursor.getString(3));
                personLinkedList.add(new Dorms(name,province,img_resource_id));
            } while (cursor.moveToNext());
        }
        cursor.close();


        return personLinkedList;
    }

}
