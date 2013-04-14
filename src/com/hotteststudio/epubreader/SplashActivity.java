package com.hotteststudio.epubreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
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
