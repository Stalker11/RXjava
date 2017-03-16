package com.olegel.rxjava.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.olegel.rxjava.R;

public class NetworkReciver extends BroadcastReceiver {
    private static final String TAG = NetworkReciver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn =  (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conn.getActiveNetworkInfo();

        // Checks the user prefs and the network connection. Based on the result, decides whether
        // to refresh the display or keep the current display.
        // If the userpref is Wi-Fi only, checks to see if the device has a Wi-Fi connection.
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            // If device has its Wi-Fi connection, sets refreshDisplay
            // to true. This causes the display to be refreshed when the user
            // returns to the app.

            Toast.makeText(context, "Type WI_FI", Toast.LENGTH_SHORT).show();

            // If the setting is ANY network and there is a network connection
            // (which by process of elimination would be mobile), sets refreshDisplay to true.
        } else if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            Toast.makeText(context, "Type mobile", Toast.LENGTH_SHORT).show();
            // Otherwise, the app can't download content--either because there is no network
            // connection (mobile or Wi-Fi), or because the pref setting is WIFI, and there
            // is no Wi-Fi connection.
            // Sets refreshDisplay to false.
        } else if(networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET){
            Toast.makeText(context,"Ethernet", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Not connection", Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "onReceive:1 "+110);
    }
}
