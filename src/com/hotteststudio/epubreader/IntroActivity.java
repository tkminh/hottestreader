package com.hotteststudio.epubreader;

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
		if (MainActivity.settings.isFirst) {
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
			finish();
		}
		super.onBackPressed();
	}
}
