package com.hotteststudio.model;

import android.content.Context;
import android.webkit.WebView;
import android.widget.Toast;

import com.hotteststudio.epubreader.Reader;

public class JSInterface {
	
	public Context mContext;
	public WebView webview;
	
	public JSInterface(Context c, WebView _wv) {
		mContext = c;
		webview = _wv;
	}
	
	public void loadBookFinish() {
		Reader.progressDialog.dismiss();
	}
	
	public void showToast(String msg) {
		Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
	}
	
	public void executeJS(String js) {
		webview.loadUrl("javascript:(function(){" + js + "})()");
	}
}
