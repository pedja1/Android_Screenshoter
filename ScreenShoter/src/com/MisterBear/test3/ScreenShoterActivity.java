package com.MisterBear.test3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.format.Time;
import android.view.View.OnClickListener;
import android.view.View;
import android.graphics.Bitmap; 
import android.graphics.drawable.BitmapDrawable; 
import android.widget.LinearLayout;



public class ScreenShoterActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LinearLayout L1;
        L1 = (LinearLayout) findViewById(R.id.LinearLayout1);
        Button but = (Button) findViewById(R.id.PrintScreen); 
        but.setOnClickListener(new View.OnClickListener() 
        { 
        	public void onClick(View v) 
        	{ 
        	TextView textview1;
        	String folderToSave = Environment.getExternalStorageDirectory().toString();
        	//folderToSave+="/";
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


    }
