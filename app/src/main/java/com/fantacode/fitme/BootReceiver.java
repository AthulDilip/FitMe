package com.fantacode.fitme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Athul on 24-06-2017.
 */

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Set the alarm.
        Alarms.setNotificationAlarm(context, 17, 10);
    }
}
