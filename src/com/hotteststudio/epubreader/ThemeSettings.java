package com.hotteststudio.epubreader;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
	
	// buy books
	public void clickAds(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
}
