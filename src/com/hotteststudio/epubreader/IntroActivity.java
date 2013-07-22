package com.hotteststudio.epubreader;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
	}
	
	public void backToMainActivity(View v) {
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public void clickHowTo(View v) {
		//XCommon.openBrowser(this, Default.URL_BOOK);
	}
	
	public void clickConvert(View v) {
		XCommon.openBrowser(this, Default.URL_PDF_2_EPUB);
	}
	
	public void clickManyBook(View v) {
		XCommon.openBrowser(this, Default.URL_MANYBOOK);
	}
	
	public void clickSnee(View v) {
		XCommon.openBrowser(this, Default.URL_SNEE);
	}
	
	public void clickMobileRead(View v) {
		XCommon.openBrowser(this, Default.URL_MOBILEREAD);
	}
	
	public void clickFeedBook(View v) {
		XCommon.openBrowser(this, Default.URL_FEEDBOOK);
	}
	
	public void clickVNBook(View v) {
		XCommon.openBrowser(this, Default.URL_VIETNAMBOOK);
	}
}
