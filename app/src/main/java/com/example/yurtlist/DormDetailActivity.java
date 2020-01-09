package com.example.yurtlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yurtlist.Helpers.DormsHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class DormDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DORMID = "dormId";
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;

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

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSmsPermission();



            }
        });

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


                TextView name = (TextView)findViewById(R.id.dorm_detail_name);
                name.setText(nameText);

                TextView description = (TextView)findViewById(R.id.dorm_detail_province);
                description.setText(provinceText);

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
                scheduleNotification(getNotification( "Dil Türkçeye çevrildi!" ) , 1000 ) ;

                return true;
            case R.id.english:
                Locale locale_en = new Locale("en");
                Locale.setDefault(locale_en);
                Configuration config_en = getBaseContext().getResources().getConfiguration();
                config_en.locale = locale_en;
                getBaseContext().getResources().updateConfiguration(config_en,
                        getBaseContext().getResources().getDisplayMetrics());
                Intent intent_en = new Intent(this, MainActivity.class);
                startActivity(intent_en);
                scheduleNotification(getNotification( "Language set to English!" ) , 1000 ) ;

                return true;
        }
        return(super.onOptionsItemSelected(item));
    }
    private void scheduleNotification (android.app.Notification notification , int delay) {
        Intent notificationIntent = new Intent( this, NotificationLanguage. class ) ;
        notificationIntent.putExtra(NotificationLanguage. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(NotificationLanguage. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        long futureInMillis = SystemClock.elapsedRealtime () + delay ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , futureInMillis , pendingIntent) ;
    }
    private android.app.Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "YurtList" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_launcher_foreground) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.SEND_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }

        else if(grant == PackageManager.PERMISSION_GRANTED){
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"));
            sendIntent.putExtra("sms_body", sms);
            startActivity(sendIntent);
        }


    }

}

