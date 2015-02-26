package com.harissaeed.metaldetector;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// Setting listener for different menu buttons.
public class SelectionScreen extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_screen);
		
		//Button for starting Metal Detector Activity.
		Button met = (Button) findViewById(R.id.met);
		met.setVisibility(View.VISIBLE);
		met.setBackgroundColor(Color.TRANSPARENT);
        met.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(SelectionScreen.this,MetalDetectorActivity.class);
                startActivity(i);
            }});
        
        //Button for starting EMF Detector Activity.
        Button emf = (Button) findViewById(R.id.emf);
        emf.setVisibility(View.VISIBLE);
        emf.setBackgroundColor(Color.TRANSPARENT);
        emf.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(SelectionScreen.this,EMFDetectorActivity.class);
                startActivity(i);
            }});
        
        //Button for getting Help.
        Button he = (Button) findViewById(R.id.he);
        he.setVisibility(View.VISIBLE);
        he.setBackgroundColor(Color.TRANSPARENT);
        he.setOnClickListener(new OnClickListener() {
        	@Override
			public void onClick(View arg0) {
				HelpDialog helpDialog = new HelpDialog(SelectionScreen.this);
				helpDialog.setTitle("Help");
				helpDialog.show();           		
			}});
        //Button for Exiting the application.
        Button Exit = (Button) findViewById(R.id.exit);
        Exit.setVisibility(View.VISIBLE);
        Exit.setBackgroundColor(Color.TRANSPARENT);
        Exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
        
}


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		}
	}