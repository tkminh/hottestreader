package com.hotteststudio.epubreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

public class BookstoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookstore);
	}
	
	public void openAds1(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
}
