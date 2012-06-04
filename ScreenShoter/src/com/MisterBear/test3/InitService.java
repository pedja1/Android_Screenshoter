package com.MisterBear.test3;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources.NotFoundException;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class InitService extends Service {
	/**
	 Connection to Screenshot service
	 */
	private ServiceConnection aslServiceConn = new ServiceConnection() {
/**
 	When diconnected
 */
		public void onServiceDisconnected(ComponentName name) {
			/// TODO Auto-generated method stub

		}
	/**
	 	When Connected
	*/
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v("Service","Bind to ScreenShot Service");
			aslProvider = IScreenshotProvider.Stub.asInterface(service);
			try {
				if (aslProvider == null)
					Toast.makeText(getApplicationContext(), R.string.n_a, Toast.LENGTH_SHORT).show();
				else if (!aslProvider.isAvailable())
					Toast.makeText(getApplicationContext(), R.string.native_n_a, Toast.LENGTH_SHORT).show();
				else {
					Log.v("Service","Take ScreenShot");
					aslProvider.takeScreenshot();
				}
			} catch (NotFoundException e) {
				/// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				/// squelch
			}
			Log.v("Service","Unbind");
			aslServiceConn.onServiceDisconnected(name);
		}
	};
	private IScreenshotProvider aslProvider = null;
/**
 When service start
 */
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v("Service","Start Service");
	    Intent intents = new Intent();
	    intents.setAction("pl.polidea.asl.ScreenshotService.BIND");
	    ///intent.addCategory(Intent.ACTION_DEFAULT);
	    bindService (intents, aslServiceConn, BIND_AUTO_CREATE);
	    Log.v("Service","Stop Service");
 
	   return 1;
	}
	/**
	 When service create
	 */
	 @Override
	public  void onCreate() {
		        Log.v("Service","Start Service");
		        Intent intents = new Intent();
		        intents.setAction("pl.polidea.asl.ScreenshotService.BIND");
		        ///intent.addCategory(Intent.ACTION_DEFAULT);
		        bindService (intents, aslServiceConn, BIND_AUTO_CREATE);
		        Log.v("Service","Stop Service");
		        stopSelf();
	}
	 /**
	  When bind to service
	  */
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
/**
 When Service Destroy
 */
  @Override
  public void onDestroy() {
    super.onDestroy();
  }
}