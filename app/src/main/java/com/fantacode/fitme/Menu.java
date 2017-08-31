package com.fantacode.fitme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by Athul on 28-02-2016.
 */
public class Menu extends Activity {

    private SharedPreferences alarmSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        alarmSettings = getSharedPreferences("AlarmPrefs", 0);
        boolean alarmSet = alarmSettings.getBoolean("alarmSet", false);
        if(!alarmSet) {
            //First notification
            Alarms.setNotificationAlarm(Menu.this, 17, 10);

            //Save in shared preferences
            SharedPreferences.Editor editor = alarmSettings.edit();
            editor.putBoolean("alarmSet", true);
            editor.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void workoutClicked(View view){
        Intent intent = new Intent(Menu.this,Main.class);
        intent.putExtra("type","harder");
        intent.putExtra("typenum",1);
        startActivity(intent);
    }

    public void motivationClicked(View view){
        Intent intent = new Intent(Menu.this,Main.class);
        intent.putExtra("type","notmotivated");
        intent.putExtra("typenum",2);
        startActivity(intent);
    }

    public void junkClicked(View view){
        Intent intent = new Intent(Menu.this,Main.class);
        intent.putExtra("type","junk");
        intent.putExtra("typenum",3);
        startActivity(intent);
    }

    public void resultClicked(View view){
        Intent intent = new Intent(Menu.this,Main.class);
        intent.putExtra("type","noresult");
        intent.putExtra("typenum",4);
        startActivity(intent);
    }

}
