package com.hotteststudio.epubreader;

import android.app.Activity;
import android.os.Bundle;

public class ThemeSettings extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themesettings);
	}
	
	@Override
	public void onBackPressed() {
		save();
		super.onBackPressed();
	}
	
	public void save() {
		
	}
}
