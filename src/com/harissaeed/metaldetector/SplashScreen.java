package com.harissaeed.metaldetector;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		
			setContentView(R.layout.activity_splash_screen);
			
		// Handler for displaying the Application Splash Screen for 2 Seconds.
			Handler x = new Handler();
	   		x.postDelayed(new SplashHandler(),2000);
			}
	
	    //Ending the Splash Screen Activity and Starting Selection Screen Activity.
     class SplashHandler implements Runnable{
    	 	public void run(){
    		startActivity(new Intent(getApplication(), SelectionScreen.class));
    		SplashScreen.this.finish();
      			}
 
	
     		}
     }
