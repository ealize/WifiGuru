package com.ealize.wifiguru.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }


    @Override
    public void onReceive(Context context, Intent intent)
    {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();

        Log.d("WifiReceiver", "Alarm Called");

        //it's been a while and we don't have Wifi, turn WIFI off.
        if (netInfo == null || netInfo.getType() != ConnectivityManager.TYPE_WIFI)
        {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            wifiManager.setWifiEnabled(false);
            Toast.makeText(context, "No Connection detected. Turning off WIFI", Toast.LENGTH_LONG).show();
            Log.d("WifiReceiver", "Turning off WIFI");


        }
    }
}
