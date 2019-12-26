package com.example.yurtlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurtlist.Helpers.DormsHelper;

public class DormDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DORMID = "dormId" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        int dormId = (Integer) getIntent().getExtras().get(EXTRA_DORMID);

        //Create a cursor
        SQLiteOpenHelper dormsHelper = new DormsHelper(this);
        try {
            SQLiteDatabase db = dormsHelper.getReadableDatabase();
            Cursor cursor = db.query("DORM",
                    new String[]{"NAME","IMAGE_RESOURCE_ID","PROVINCE"},
                    "_id = ?",
                    new String[]{Integer.toString(dormId)},
                    null, null, "_id");

            if (cursor.moveToFirst()) {

                String nameText = cursor.getString(0);
                setTitle(nameText);

                int photoId = cursor.getInt(1);
                String provinceText = cursor.getString(2);



                TextView name = (TextView)findViewById(R.id.dorm_detail_name);
                name.setText(nameText);

                TextView description = (TextView)findViewById(R.id.dorm_detail_province);
                description.setText(provinceText);

                ImageView photo = (ImageView) findViewById(R.id.dorm_image);
                photo.setImageResource(photoId);


            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

