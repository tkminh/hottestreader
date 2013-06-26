package com.hotteststudio.epubreader;

import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		
		setContentView(R.layout.activity_splash);
		
//		System.gc();
//		String fileName = "";
//		ImageView img = (ImageView)findViewById(R.id.img_book_loading);
//		//img.setImageDrawable(getResources().getDrawable(R.drawable.book_loading));
//		img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.book_loading));
		
		
		handler = new Handler();
		startMainActivity();
	}
	
	public Handler getHandler() {
		return handler;
	}
	public Context getActivityContext(){
		return this;
	}
	
	private void startMainActivity() {
		getHandler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				if (SplashActivity.this.isFinishing()) {
					return;
				}
				startActivity(new Intent(getActivityContext(), MainActivity.class));
				finish();
			}
		}, 3000);
	}
}
