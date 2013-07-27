package com.hotteststudio.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.DisplayMetrics;

import com.hotteststudio.constant.Default;

public class XCommon {
	
	public static final String ROOT_FOLDER = "hottestreader";
	public static final String XML_FILE = "settings.xml";
	public static Context context;
	
	public XCommon() {
		
	}
	
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
			File f = new File(localfile);
			if (f.exists() == false) {
				f.createNewFile();
			}
			FileWriter fw = new FileWriter(f, append);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(text);
			writer.flush();
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
	
	public static void openBrowser(Context c, String url) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		c.startActivity(i);
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
	
	// check the application was installed or not
	public boolean appInstallOrNot(String uri) {
		if (context==null) return false;
		PackageManager pm = context.getPackageManager();
        boolean app_installed = false;
        try {
        	pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
        	app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
        	app_installed = false;
        }
        return app_installed ;
	}
	
	// open google play to download & install app
	public void requestInstallApp(String appID) {
		Intent goToMarket = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=" + appID));
		context.startActivity(goToMarket);
	}
	
	// remove accents from a String 
	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
    {
      // Raw height and width of image
      final int height = options.outHeight;
      final int width = options.outWidth;
      int inSampleSize = 1;

      if(height > reqHeight || width > reqWidth)
      {
         if(width > height)
         {
            inSampleSize = Math.round((float) height / (float) reqHeight);
         }
         else
         {
            inSampleSize = Math.round((float) width / (float) reqWidth);
         }
      }
      return inSampleSize;
    }
	
	private static Bitmap decodeFile(File file, int newWidth, int newHeight)
    {// target size
      try
      {

         Bitmap bmp = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.fromFile(file));
         if(bmp == null)
         {
            // avoid concurrence
            // Decode image size
            BitmapFactory.Options option = new BitmapFactory.Options();

            // option = getBitmapOutput(file);

            option.inDensity = context.getResources().getDisplayMetrics().densityDpi < DisplayMetrics.DENSITY_HIGH ? 120 : 240;
            option.inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;

            if(newHeight > 0 && newWidth > 0) 
                option.inSampleSize = calculateInSampleSize(option, newWidth, newWidth);

            option.inJustDecodeBounds = false;
            byte[] decodeBuffer = new byte[12 * 1024];
            option.inTempStorage = decodeBuffer;
            option.inPurgeable = true;
            option.inInputShareable = true;
            option.inScaled = true;

            bmp = BitmapFactory.decodeStream(new FileInputStream(file), null, option);
            if(bmp == null)
            {
               return null;
            }

         }
         else
         {
            int inDensity = context.getResources().getDisplayMetrics().densityDpi < DisplayMetrics.DENSITY_HIGH ? 120 : 240;
            int inTargetDensity = context.getResources().getDisplayMetrics().densityDpi;
            if(inDensity != inTargetDensity)
            {
               int newBmpWidth = (bmp.getWidth() * inTargetDensity) / inDensity;
               int newBmpHeight = (bmp.getHeight() * inTargetDensity) / inDensity;
               bmp = Bitmap.createScaledBitmap(bmp, newBmpWidth, newBmpHeight, true);
            }
         }

         return bmp;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      return null;
    }
	
	//
	public static String convertStringVietnamese(String str) {
        str = str.replace('\340', 'a');
        str = str.replace('\341', 'a');
        str = str.replace('\u1EA3', 'a');
        str = str.replace('\343', 'a');
        str = str.replace('\u1EA1', 'a');
        str = str.replace('\u0103', 'a');
        str = str.replace('\u1EB1', 'a');
        str = str.replace('\u1EAF', 'a');
        str = str.replace('\u1EB3', 'a');
        str = str.replace('\u1EB5', 'a');
        str = str.replace('\u1EB7', 'a');
        str = str.replace('\342', 'a');
        str = str.replace('\u1EA7', 'a');
        str = str.replace('\u1EA5', 'a');
        str = str.replace('\u1EA9', 'a');
        str = str.replace('\u1EAB', 'a');
        str = str.replace('\u1EAD', 'a');
        str = str.replace('\300', 'a');
        str = str.replace('\301', 'a');
        str = str.replace('\u1EA2', 'a');
        str = str.replace('\303', 'a');
        str = str.replace('\u1EA0', 'a');
        str = str.replace('\u0102', 'a');
        str = str.replace('\u1EB0', 'a');
        str = str.replace('\u1EAE', 'a');
        str = str.replace('\u1EB2', 'a');
        str = str.replace('\u1EB4', 'a');
        str = str.replace('\u1EB6', 'a');
        str = str.replace('\302', 'a');
        str = str.replace('\u1EA6', 'a');
        str = str.replace('\u1EA4', 'a');
        str = str.replace('\u1EA8', 'a');
        str = str.replace('\u1EAA', 'a');
        str = str.replace('\u1EAC', 'a');
        str = str.replace('\u0111', 'd');
        str = str.replace('\u0110', 'd');
        str = str.replace('\350', 'e');
        str = str.replace('\351', 'e');
        str = str.replace('\u1EBB', 'e');
        str = str.replace('\u1EBD', 'e');
        str = str.replace('\u1EB9', 'e');
        str = str.replace('\352', 'e');
        str = str.replace('\u1EC1', 'e');
        str = str.replace('\u1EBF', 'e');
        str = str.replace('\u1EC3', 'e');
        str = str.replace('\u1EC5', 'e');
        str = str.replace('\u1EC7', 'e');
        str = str.replace('\310', 'e');
        str = str.replace('\311', 'e');
        str = str.replace('\u1EBA', 'e');
        str = str.replace('\u1EBC', 'e');
        str = str.replace('\u1EB8', 'e');
        str = str.replace('\312', 'e');
        str = str.replace('\u1EC0', 'e');
        str = str.replace('\u1EBE', 'e');
        str = str.replace('\u1EC2', 'e');
        str = str.replace('\u1EC4', 'e');
        str = str.replace('\u1EC6', 'e');
        str = str.replace('\354', 'i');
        str = str.replace('\355', 'i');
        str = str.replace('\u1EC9', 'i');
        str = str.replace('\u0129', 'i');
        str = str.replace('\u1ECB', 'i');
        str = str.replace('\314', 'i');
        str = str.replace('\315', 'i');
        str = str.replace('\u1EC8', 'i');
        str = str.replace('\u0128', 'i');
        str = str.replace('\u1ECA', 'i');
        str = str.replace('\362', 'o');
        str = str.replace('\363', 'o');
        str = str.replace('\u1ECF', 'o');
        str = str.replace('\365', 'o');
        str = str.replace('\u1ECD', 'o');
        str = str.replace('\364', 'o');
        str = str.replace('\u1ED3', 'o');
        str = str.replace('\u1ED1', 'o');
        str = str.replace('\u1ED5', 'o');
        str = str.replace('\u1ED7', 'o');
        str = str.replace('\u1ED9', 'o');
        str = str.replace('\u01A1', 'o');
        str = str.replace('\u1EDD', 'o');
        str = str.replace('\u1EDB', 'o');
        str = str.replace('\u1EDF', 'o');
        str = str.replace('\u1EE1', 'o');
        str = str.replace('\u1EE3', 'o');
        str = str.replace('\322', 'o');
        str = str.replace('\323', 'o');
        str = str.replace('\u1ECE', 'o');
        str = str.replace('\325', 'o');
        str = str.replace('\u1ECC', 'o');
        str = str.replace('\324', 'o');
        str = str.replace('\u1ED2', 'o');
        str = str.replace('\u1ED0', 'o');
        str = str.replace('\u1ED4', 'o');
        str = str.replace('\u1ED6', 'o');
        str = str.replace('\u1ED8', 'o');
        str = str.replace('\u01A0', 'o');
        str = str.replace('\u1EDC', 'o');
        str = str.replace('\u1EDA', 'o');
        str = str.replace('\u1EDE', 'o');
        str = str.replace('\u1EE0', 'o');
        str = str.replace('\u1EE2', 'o');
        str = str.replace('\371', 'u');
        str = str.replace('\372', 'u');
        str = str.replace('\u1EE7', 'u');
        str = str.replace('\u0169', 'u');
        str = str.replace('\u1EE5', 'u');
        str = str.replace('\u01B0', 'u');
        str = str.replace('\u1EEB', 'u');
        str = str.replace('\u1EE9', 'u');
        str = str.replace('\u1EED', 'u');
        str = str.replace('\u1EEF', 'u');
        str = str.replace('\u1EF1', 'u');
        str = str.replace('\331', 'u');
        str = str.replace('\332', 'u');
        str = str.replace('\u1EE6', 'u');
        str = str.replace('\u0168', 'u');
        str = str.replace('\u1EE4', 'u');
        str = str.replace('\u01AF', 'u');
        str = str.replace('\u1EEA', 'u');
        str = str.replace('\u1EE8', 'u');
        str = str.replace('\u1EEC', 'u');
        str = str.replace('\u1EEE', 'u');
        str = str.replace('\u1EF0', 'u');
        str = str.replace('\u1EF3', 'y');
        str = str.replace('\375', 'y');
        str = str.replace('\u1EF7', 'y');
        str = str.replace('\u1EF9', 'y');
        str = str.replace('\u1EF5', 'y');
        str = str.replace('Y', 'y');
        str = str.replace('\u1EF2', 'y');
        str = str.replace('\335', 'y');
        str = str.replace('\u1EF6', 'y');
        str = str.replace('\u1EF8', 'y');
        str = str.replace('\u1EF4', 'y');
        return str;
    }
}
