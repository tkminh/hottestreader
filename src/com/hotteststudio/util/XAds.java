package com.hotteststudio.util;

import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.ads.AdRequest.ErrorCode;

public class XAds {
	public AdView adView;
	
	
	public XAds(AdView _adView) {
		adView = _adView;

		
		adView.setAdListener(new AdListener() {
			
			public void onReceiveAd(Ad arg0) {
				// TODO Auto-generated method stub
				adView.setPadding(2, 0, 2, 0);
		        //adView.setBackgroundColor(Color.parseColor("#a54bff"));
				adView.setBackgroundColor(Color.BLACK);
		        Log.d("quang cao","received");
			}
			
			public void onPresentScreen(Ad arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void onLeaveApplication(Ad arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
				// TODO Auto-generated method stub
				adView.setPadding(0, 0, 0, 0);
				Log.d("quang cao mimi","failed " + arg1.name());
			}
			
			public void onDismissScreen(Ad arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void loadAds() {
		try {
			final Handler handle = new Handler();
			final Runnable run = new Runnable() {

				@Override
				public void run() {
					AdRequest request = new AdRequest();
					request.setTesting(true);
					request.addTestDevice("5AA908BF9DFBF93EB786BD3F55E43AD7");
					if (adView.getVisibility()==View.VISIBLE) {
						adView.loadAd(request);
					}
					handle.postDelayed(this, 10000);
				}
			};
			handle.postDelayed(run, 10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
