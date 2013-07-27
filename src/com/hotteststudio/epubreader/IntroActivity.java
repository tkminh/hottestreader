package com.hotteststudio.epubreader;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hotteststudio.constant.Default;
import com.hotteststudio.util.XCommon;

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
		
		setupEvent();
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
	
	public class CustomTouchListener implements View.OnTouchListener {     
		String color1 = "#3ca8ff";
		String color2 = "#ffffff";
		public CustomTouchListener(boolean type) {
			if (type==false) {
				String color = color1;
				color1 = color2;
				color2 = color1;
			}
		}
	    public boolean onTouch(View view, MotionEvent motionEvent) {
	    switch(motionEvent.getAction()){            
	            case MotionEvent.ACTION_DOWN:
	            	((TextView)view).setTextColor(Color.parseColor(color1));
	                break;          
	            case MotionEvent.ACTION_CANCEL:             
	            case MotionEvent.ACTION_UP:
	            	((TextView)view).setTextColor(Color.parseColor(color2)); 
	                break;
	    } 
	        return false;   
	    } 
	}
	
	public void setupEvent() {
		TextView txtWhatConvert = (TextView)findViewById(R.id.txtWhatConvert);
		txtWhatConvert.setOnTouchListener(new CustomTouchListener(false));
		txtWhatConvert.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_PDF_2_EPUB);
			}
		});
		
		TextView txtDownload1 = (TextView)findViewById(R.id.txtDownload1);
		txtDownload1.setOnTouchListener(new CustomTouchListener(true));
		txtDownload1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_MANYBOOK);
			}
		});
		
		TextView txtDownload2 = (TextView)findViewById(R.id.txtDownload2);
		txtDownload2.setOnTouchListener(new CustomTouchListener(true));
		txtDownload2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_SNEE);
			}
		});
		
		TextView txtDownload3 = (TextView)findViewById(R.id.txtDownload3);
		txtDownload3.setOnTouchListener(new CustomTouchListener(true));
		txtDownload3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_MOBILEREAD);
			}
		});
		
		TextView txtDownload4 = (TextView)findViewById(R.id.txtDownload4);
		txtDownload4.setOnTouchListener(new CustomTouchListener(true));
		txtDownload4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_FEEDBOOK);
			}
		});
		
		TextView txtDownload5 = (TextView)findViewById(R.id.txtDownload5);
		txtDownload5.setOnTouchListener(new CustomTouchListener(true));
		txtDownload5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				XCommon.openBrowser(IntroActivity.this, Default.URL_VIETNAMBOOK);
			}
		});
		
	}
}
