package com.MisterBear.test3;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CustomPhoneStateListener extends PhoneStateListener {

private static final String TAG = "CustomPhoneStateListener";
/**
Calls when phone state changes
 */
@Override
public void onCallStateChanged(int state, String incomingNumber){
        switch(state){
                case TelephonyManager.CALL_STATE_RINGING:
                        Log.d(TAG, "RINGING");
                        break;
        }       
}
}