package com.harissaeed.metaldetector;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//This is the metal detector code developed by Muhammad Harris Saeed as a final year project for Cardiff Metropolitan University.

public class MetalDetectorActivity extends Activity implements SensorEventListener {

	private TextView xTV;
	private TextView yTV;
	private TextView zTV;
	private TextView Res;
	
	private ProgressBar mProgress;

	private SensorManager sensorManager = null;
	private float[] geomag = new float[3];
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setting up a sensor manager for getting sensor service.
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.main);

		// Setting up TextView
		xTV = (TextView) findViewById(R.id.xTV);
		yTV = (TextView) findViewById(R.id.yTV);
		zTV = (TextView) findViewById(R.id.zTV);
        Res = (TextView) findViewById(R.id.Res);
        
       // Initializing values for TextView.
		xTV.setText("X: 0.00");
		yTV.setText("Y: 0.00");
		zTV.setText("Z: 0.00");
		Res.setText("R: 0.00");
		
		// Setting up a progress bar
		mProgress = (ProgressBar) findViewById(R.id.progress_bar);
		mProgress.setMax(100);
	}
	    //Getting Absolute value of Geo-magnetic Field.
	public void onSensorChanged(SensorEvent sensorEvent) {
		synchronized (this) {
			geomag = sensorEvent.values.clone();
			float magAbsVal = (float) Math.sqrt(geomag[0]*geomag[0] + geomag[1]*geomag[1] + geomag[2]*geomag[2]);
			mProgress.setProgress((int) magAbsVal);

			if (geomag != null) {
				
				// Displaying the Value along all axis.
				xTV.setText("Magnetic field along X-axis = "+Float.toString(geomag[0])+ "uT");
				yTV.setText("Magnetic field along Y-axis = "+Float.toString(geomag[1])+ "uT");
				zTV.setText("Magnetic field along Z-axis = "+Float.toString(geomag[2])+ "uT");    
				Res.setText( String.format( "Magnetic Field: %.2f",magAbsVal) + "uT");
				
				mProgress.setProgress((int) magAbsVal);
				
				Resources res = getResources();
				Rect bounds = mProgress.getProgressDrawable().getBounds();
				
			// Setting Progress bar color to respective value.
			if( magAbsVal <= 55){
				mProgress.setProgressDrawable(res.getDrawable(R.drawable.yellowprogressbar));}
			else{
				mProgress.setProgressDrawable(res.getDrawable(R.drawable.greenprogressbar));}
			if ( magAbsVal >= 75){
				mProgress.setProgressDrawable(res.getDrawable(R.drawable.redprogressbar));}
			mProgress.getProgressDrawable().setBounds(bounds);
					
			}
				
			//Playing alarm and displaying Message on detection. 
			
				if( magAbsVal > 65){
					playAlarm();
					final Toast toast = Toast.makeText(getApplicationContext(), "Metal Detected !", Toast.LENGTH_SHORT);
					toast.show();
					Handler handler = new Handler();
					    handler.postDelayed(new Runnable() {
					       @Override
					       public void run() {
					           toast.cancel(); 
					       }
					}, 500);
					}
				
						
					}
				}
			
		
	

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// Registering this class as a listener for the sensors.
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		// Unregistering the listener
		sensorManager.unregisterListener(this);
	}
	
	/**
	 * Method for playing alarm for signal half time or full time..
	 */
	private void playAlarm() {
		MediaPlayer mp = MediaPlayer.create(MetalDetectorActivity.this, R.raw.met);
		
		if(!mp.isPlaying()){ //If media player is not already playing.
			
			mp.start();
			mp.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					mp.release();
				}

			});
		}

	}
	

}