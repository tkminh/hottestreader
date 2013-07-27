package com.hotteststudio.epubreader;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class IntroActivity extends Activity {

	int width;
	int height;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		ImageView headerThemeSetting = (ImageView)findViewById(R.id.headerMainIntro);
		scaleViewR(headerThemeSetting,R.drawable.header);
	}
	
	public void backToMainActivity(View v) {
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public float calculateAspectRatio() {
		float wRatio = (width / (float)Default.WIDTH);
		float hRatio = (height / (float)Default.HEIGHT);
		float ratioMultiplier = wRatio;
		if (hRatio < wRatio) {
			ratioMultiplier = hRatio;
		}
		
		return ratioMultiplier;
	}
	
	public void scaleViewR(View v, int id) {
		BitmapDrawable bmap1 = (BitmapDrawable) this.getResources().getDrawable(id);
		float w = bmap1.getBitmap().getWidth();
		float h = bmap1.getBitmap().getHeight();
		
		float wRatio = width / w;
		float hRatio = height / h;
		 
		float f = wRatio;
		if (hRatio < wRatio) {
			f = hRatio;
		}
		
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams((int)(w*f), (int)(h*f));
		v.setLayoutParams(layout);
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
