package com.MisterBear.ScreenShoter;

import com.MisterBear.ScreenShoter.CustomPhoneStateListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
 
public class BroadCastReciverClass extends BroadcastReceiver {

private static final String TAG = "CustomBroadcastReceiver";

@Override
public void onReceive(Context context, Intent intent) {
    Log.v(TAG, "WE ARE INSIDE!!!!!!!!!!!");
    TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    CustomPhoneStateListener customPhoneListener = new CustomPhoneStateListener();
    telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);   
    Bundle bundle = intent.getExtras();
    String phoneNr= bundle.getString("incoming_number");
    Toast.makeText(context, "phoneNr: "+phoneNr, Toast.LENGTH_LONG).show();
}
}