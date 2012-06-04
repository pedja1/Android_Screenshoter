package com.MisterBear.test3;

import com.MisterBear.test3.CustomPhoneStateListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
 
public class BroadCastReciverCall extends BroadcastReceiver {

private static final String TAG = "CustomBroadcastReceiver";

@Override
/**
Function calls when phone state changes, this function start service which bind to Screenshot Service
@Context Context current 
@Intent
 */
public void onReceive(Context context, Intent intent) {
	///Manager to handle the incoming call
    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    ///Incoming call handler to handle incoming calls
    CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
    ///Set handle
    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    ///Get info from handle 
    Bundle bundle = intent.getExtras();
    ///Get number
    String phoneNr= bundle.getString("incoming_number");
    ///Puts number in logs
    Log.v(TAG, "phoneNr: "+phoneNr);
    ///Start service which bind to Screenshot Service
    Intent service;
    service = new Intent(context, InitService.class);
    Log.v(TAG, "ready to start");
    context.startService(service);
}
}