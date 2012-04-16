package com.MisterBear.ScreenShoter;


import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CustomPhoneStateListener extends PhoneStateListener {

private static final String TAG = "CustomPhoneStateListener";
@Override
public void onCallStateChanged(int state, String incomingNumber){
        switch(state){
                case TelephonyManager.CALL_STATE_RINGING:
                        Log.d(TAG, "RINGING");
                        break;
        }       
}
}