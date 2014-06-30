package com.ealize.wifiguru.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.GregorianCalendar;

public class WifiReceiver extends BroadcastReceiver {
    private int WIFI_TIMEOUT = 10*1000;
    public WifiReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            Log.d("WifiReceiver", "Have Wifi Connection");

        }
        else
        {
            //Setup an alarm and turn off the wifi if no connection has been made yet.
            Long time = new GregorianCalendar().getTimeInMillis() + WIFI_TIMEOUT;

            Intent intentAlarm = new Intent("com.ealize.wifiguru.WIFI_CHECK_ALARM");//context, AlarmReceiver.class);


            // create the object
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            //set the alarm for particular time
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
            Log.d("WifiReceiver", "No wifi. Timer Set");

        }
    }



    private class WifiCheckAlarm {

    }

}


