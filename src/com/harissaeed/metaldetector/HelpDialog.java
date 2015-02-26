package com.harissaeed.metaldetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
	//Creating a pop up Help Dialogue.
public class HelpDialog extends Dialog {

	private static Context mContext = null;
	
	//Setting context for HelpDialouge View.
	public HelpDialog(Context context) {
		super(context);
		mContext = context;
	}
	//Enabling JavaScript.
@SuppressLint("SetJavaScriptEnabled")

	@Override
	//Creating WebView For HelpDialouge and redirecting to HTML file.
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_help);
		WebView help=(WebView) findViewById(R.id.helpView); 
		help.getSettings().setJavaScriptEnabled(true); 
		help.getSettings().setLoadWithOverviewMode(true);
		help.getSettings().setUseWideViewPort(true);
		help.getSettings().setBuiltInZoomControls(true);
		String helpText = readRawTextFile(R.raw.help_contents);
		help.loadData(helpText, "text/html; charset=utf-8", "utf-8");
		
	}

	private String readRawTextFile(int id) {
		InputStream inputStream = mContext.getResources().openRawResource(id);
		InputStreamReader in = new InputStreamReader(inputStream);
		BufferedReader buf = new BufferedReader(in);
		String line;
		StringBuilder text = new StringBuilder();
		try {
			while (( line = buf.readLine()) != null) 
				text.append(line);
		} catch (IOException e) {
			return null;
		}
		return text.toString();
	}
	     
	
}
