package com.hotteststudio.epubreader;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

public class BookstoreActivity extends Activity {

	int width;
	int height; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookstore);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		ImageView headerThemeSetting = (ImageView)findViewById(R.id.headerMBookstore);
		scaleViewR(headerThemeSetting,R.drawable.header);
		
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
	
	public void openAds1(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
}
