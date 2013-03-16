package com.hotteststudio.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class InitLayout {
	public ScrollView verscrollView;
	public HorizontalScrollView horscrollView;
	public LinearLayout container;
	public Context myContext;
	
	public InitLayout(Context mContext, ScrollView sv, HorizontalScrollView hsv, LinearLayout ll) {
		myContext = mContext;
		verscrollView = sv;
		horscrollView = hsv;
		container = ll;
	}
	
	public void addItem(String val) {
		TextView textView = new TextView(myContext);
		textView.setWidth(300);
		textView.setHeight(200);
		textView.setText(val);
		textView.setTextSize(100);
		textView.setTextColor(Color.BLACK);
		textView.setGravity(Gravity.CENTER);
		textView.setBackgroundColor(Color.GREEN);
		container.addView(textView);
	}
}
