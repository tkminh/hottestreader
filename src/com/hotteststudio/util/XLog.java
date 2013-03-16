package com.hotteststudio.util;

import android.util.Log;

public class XLog {
	
	public static void log(String msg) {
		Log.d(XLog.class.getName(), msg + " << @@");
	}
}
