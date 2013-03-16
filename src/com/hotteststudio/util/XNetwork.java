package com.hotteststudio.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class XNetwork {

	public static boolean checkInternetConnection(Context context) {
		if (context == null) return false;
		try {
			ConnectivityManager conn = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = conn.getActiveNetworkInfo();
			if (networkInfo != null) 
				return networkInfo.isConnected();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
