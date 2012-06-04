package com.MisterBear.test3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.MisterBear.test3.ScreenshotService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap; 
import android.graphics.drawable.BitmapDrawable; 
import android.widget.LinearLayout;



public class ScreenShoterActivity extends Activity {
	
	private ServiceConnection aslServiceConn = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			/// TODO Auto-generated method stub

		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			aslProvider = IScreenshotProvider.Stub.asInterface(service);
		}
	};
	private IScreenshotProvider aslProvider = null;
	Context context=this;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LinearLayout L1;
        L1 = (LinearLayout) findViewById(R.id.LinearLayout1);
        Button but = (Button) findViewById(R.id.PrintScreen); 
        Intent intent = new Intent();
        intent.setClass(this, ScreenshotService.class);
        intent.addCategory(Intent.ACTION_DEFAULT);
        bindService (intent, aslServiceConn, Context.BIND_AUTO_CREATE);
        L1.setOnTouchListener(OnTouchListener);
        L1.setOnKeyListener(KeyPress);
        but.setOnClickListener(new View.OnClickListener() 
        
        { 
        	public void onClick(View v) 
        	{ 
        	///Start service to bind to ScreenShot  service	
            Intent service;
            service = new Intent(context, InitService.class);
            startService(service);
	
        	TextView textview1;
        	String folderToSave = Environment.getExternalStorageDirectory().toString();
        	///folderToSave+="/";
        	textview1=(TextView) findViewById(R.id.TextView1);
        	
        	ImageView image;
        	View v1 = L1.getRootView();
        	v1.setDrawingCacheEnabled(true); 
        	Bitmap bm = v1.getDrawingCache(); 
        	BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
        	image = (ImageView) findViewById(R.id.imageView1);
        	image.setBackgroundDrawable(bitmapDrawable); 
        	OutputStream fOut;
            Time time = new Time();
            time.setToNow();
            try {
            File file = new File(folderToSave, Integer.toString(time.year)+"." + Integer.toString(time.month) +"."+ Integer.toString(time.monthDay)+"." + Integer.toString(time.hour) +".jpg"); 
            fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
           
        }
        catch (Exception e)
        {
        	textview1.setText(e.getMessage());
            return;
        }
        	}
        });
    }
    private View.OnTouchListener OnTouchListener  = new View.OnTouchListener(){
  
  public boolean onTouch(View view, MotionEvent motionEvent) {
   // TODO Auto-generated method stub
   
   Toast.makeText(context,"x: " + String.valueOf(motionEvent.getX())+"\ny: " + String.valueOf(motionEvent.getY()),Toast.LENGTH_SHORT).show();
  
   return true; //means event have been processed
  }

    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         switch (keyCode) {
         case KeyEvent.KEYCODE_MENU:
            /* Sample for handling the Menu button globally */
            return true;
         }
         return false;
    } 
    private View.OnKeyListener KeyPress  = new View.OnKeyListener(){
    	  
   
		public boolean onKey(View v, int keyCode, KeyEvent event) {
			switch (keyCode) {
		     case KeyEvent.KEYCODE_VOLUME_DOWN:
		    	 Toast.makeText(context,"Menu press",Toast.LENGTH_SHORT).show();
		    	  	        return true;
		     }
			return false;
		}

    	    };

    
    }
