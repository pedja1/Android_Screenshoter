package com.MisterBear.test3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
 
public class BroadCastReciverowerConnected extends BroadcastReceiver {
	

@Override
/*
Function calls when power connected, this function start service which bind to Screenshot Service
@Context Context current 
@Intent
 */
public void onReceive(Context context, Intent intent) {
    ///Start service which bind to Screnshot Service
	Intent service;
    service = new Intent(context, InitService.class);
    Log.v("PowerConnectedRecivere", "ready to start");
    context.startService(service);
}
}