package com.hotteststudio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

import android.os.Environment;
import android.os.StrictMode;

public class XCommon {
	
	public static final String ROOT_FOLDER = "hottestreader";
	public static final String XML_FILE = "settings.xml";
	
	public static String genUUID() {
		String uuid = "";
		try {
			UUID u = UUID.randomUUID();
			uuid = u.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uuid;
	}
	
	public static void saveTextToFile(String file, String text, boolean append) {
		try {
			String localfile = getRootPath() + file;
			FileWriter f = new FileWriter(new File(localfile), append);
			BufferedWriter writer = new BufferedWriter(f);
			writer.write(text);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getRootPath() {
		String f = Environment.getExternalStorageDirectory().getPath() + "/" + ROOT_FOLDER + "/";
		File ff = new File(f);
		ff.mkdirs();
		return f;
	}
	
	// stop error android.os.NetworkOnMainThreadException
	public static void strictmodePolicy() {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}
	
	public static String getImagePath() {
		return getRootPath() + "Images";
	}
	
	public static String getCoverPath() {
		String f = getRootPath() + "Cover";
		File ff = new File(f);
		if (!ff.exists())
		ff.mkdirs();
		return f;
	}
	
	public static String getFileContent(String fileName) {
		try {
			File f = new File(getRootPath() + fileName);
			if (f.exists()==false) {
				f.createNewFile();
			}
			StringBuilder text = new StringBuilder();
		    BufferedReader br = new BufferedReader(new FileReader(f));
		    String line;

		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		    }
		    
		    return text.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
