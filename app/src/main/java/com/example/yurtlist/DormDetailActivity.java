package com.example.yurtlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurtlist.Helpers.DormsHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

public class DormDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DORMID = "dormId";
    PagerAdapter pageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;
    String sms = "Hayalindeki yurdu Yurtlist uygulaması ile bul!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        int dormId = (Integer) getIntent().getExtras().get(EXTRA_DORMID);


        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        tabChats = findViewById(R.id.tabChats);
        tabStatus = findViewById(R.id.tabStatus);
        tabCalls = findViewById(R.id.tabCalls);

        pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", sms);
                startActivity(sendIntent);
            }
        });

        //Create a cursor
        SQLiteOpenHelper dormsHelper = new DormsHelper(this);
        try {
            SQLiteDatabase db = dormsHelper.getReadableDatabase();
            Cursor cursor = db.query("DORM",
                    new String[]{"NAME", "IMAGE_RESOURCE_ID", "PROVINCE"},
                    "_id = ?",
                    new String[]{Integer.toString(dormId)},
                    null, null, null);

            if (cursor.moveToFirst()) {

                String nameText = cursor.getString(0);
                setTitle(nameText);

                int photoId = cursor.getInt(1);
                String provinceText = cursor.getString(2);


//                TextView name = (TextView)findViewById(R.id.dorm_detail_name);
////                name.setText(nameText);
////
////                TextView description = (TextView)findViewById(R.id.dorm_detail_province);
////                description.setText(provinceText);
////
                ImageView photo = (ImageView) findViewById(R.id.dorm_image);
                photo.setImageResource(photoId);


            }
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Error occured! Please try again", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.turkish:
                Locale locale = new Locale("tr");
                Locale.setDefault(locale);
                Configuration config = getBaseContext().getResources().getConfiguration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this,"Dil Türkçeye çevrildi", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.english:
                Locale locale_en = new Locale("en"); // where 'hi' is Language code, set this as per your Spinner Item selected
                Locale.setDefault(locale_en);
                Configuration config_en = getBaseContext().getResources().getConfiguration();
                config_en.locale = locale_en;
                getBaseContext().getResources().updateConfiguration(config_en,
                        getBaseContext().getResources().getDisplayMetrics());
                Intent intent_en = new Intent(this, MainActivity.class);
                startActivity(intent_en);
                Toast.makeText(this,"Language set to English", Toast.LENGTH_SHORT).show();
                return true;
        }
        return(super.onOptionsItemSelected(item));
    }
}

