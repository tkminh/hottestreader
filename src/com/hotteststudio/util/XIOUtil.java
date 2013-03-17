package com.hotteststudio.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class XIOUtil {

	public static void saveTextToFile(String file, String text, boolean append) {
		try {
			String localfile = file;
			FileWriter f = new FileWriter(new File(localfile), append);
			BufferedWriter writer = new BufferedWriter(f);
			writer.write(text);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
