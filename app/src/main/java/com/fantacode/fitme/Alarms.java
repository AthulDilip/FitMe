package com.fantacode.fitme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by Athul on 24-06-2017.
 */

public class Alarms {

    public static void setNotificationAlarm(Context context, int hour, int minute) {

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        long alarmTriggerTime = calendar.getTimeInMillis();

        if(alarmTriggerTime < System.currentTimeMillis())
            alarmTriggerTime = alarmTriggerTime + 1000*60*60*24;

        Intent myIntent = new Intent(context, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTriggerTime, 1000*60*60*24, pendingIntent);

    }
}
