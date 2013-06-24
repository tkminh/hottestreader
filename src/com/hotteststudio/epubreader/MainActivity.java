package com.hotteststudio.epubreader;

import group.pals.android.lib.ui.filechooser.FileChooserActivity;
import group.pals.android.lib.ui.filechooser.io.localfile.LocalFile;
import group.pals.android.lib.ui.filechooser.services.IFileProvider;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.ads.AdView;
import com.hotteststudio.constant.Default;
import com.hotteststudio.model.BookListAdapter;
import com.hotteststudio.model.EpubInfo;
import com.hotteststudio.model.UserSettings;
import com.hotteststudio.model.XMLHandler;
import com.hotteststudio.util.XCommon;

public class MainActivity extends Activity {

	public static final int _readerResult = 1;
	public static final int FILE_SELECT_CODE = 0;
	public static String xmlContent = "";
	
	public static XMLHandler xmlHandler;
	public static UserSettings settings;
	
	public GridView listBook;
	public BookListAdapter bookAdapater;
	
	public AdView adView;
	public DisplayMetrics metrics ;
	
	public int height; 
	public int width;
	public BitmapDrawable bmap;
	
	private static final int _ReqChooseFile = 0;
	public static boolean isShowBookDetail = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		XCommon.strictmodePolicy();
		
		// xu li loading xml
		xmlHandler = new XMLHandler();
		xmlContent = getXMLSettings();
		if (xmlContent.length()>1) {
			Log.d("day ne",">>> " + xmlContent);
			settings = loadXML(xmlContent);
		} else {
			settings = new UserSettings(); 
		}
		
		initGui();
		//loadRecentEpub(); 
	}

	public void test() {
		try {
			Intent test;
			test = new Intent(this,Reader.class);
			//test.putExtra("epub", bookid);

			this.startActivity(test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initGui() {
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		bmap = (BitmapDrawable) this.getResources().getDrawable(R.drawable.header);
		float bmapWidth = bmap.getBitmap().getWidth();
		float bmapHeight = bmap.getBitmap().getHeight();
		
		float wRatio = width / bmapWidth;
		float hRatio = height / bmapHeight;
		 
		float ratioMultiplier = wRatio;
		// Untested conditional though I expect this might work for landscape mode
		if (hRatio < wRatio) {
			ratioMultiplier = hRatio;
		}
		 
		int newBmapWidth = (int) (bmapWidth*ratioMultiplier);
		int newBmapHeight = (int) (bmapHeight*ratioMultiplier);
		
		RelativeLayout iView = (RelativeLayout) findViewById(R.id.mainTopLayout);
		iView.setLayoutParams(new RelativeLayout.LayoutParams(newBmapWidth, newBmapHeight));
		
		RelativeLayout main = (RelativeLayout)findViewById(R.id.activityMain);
		main.setBackgroundResource(R.drawable.bg_details);
		
		Button btnBrowse = (Button)findViewById(R.id.btnBrowse);
		scaleView(btnBrowse, (BitmapDrawable) this.getResources().getDrawable(R.drawable.btn_choose));
		
		ImageView mainleftLayout = (ImageView)findViewById(R.id.mainleftLayout);
		scaleView(mainleftLayout, (BitmapDrawable) this.getResources().getDrawable(R.drawable.title_books_library));
		
//		ImageView mainAdv = (ImageView)findViewById(R.id.mainAdv);
//		scaleView(mainAdv, (BitmapDrawable) this.getResources().getDrawable(R.drawable.adv1));
//		mainBottomLayout.setLayoutParams(new RelativeLayout.LayoutParams(newBmapWidth, newBmapHeight));
		//scaleView(mainBottomLayout, (BitmapDrawable) this.getResources().getDrawable(R.drawable.bg_adv));
		
		//ImageView mainAds = (ImageView)findViewById(R.id.mainAds);
		//scaleView(mainAds, (BitmapDrawable) this.getResources().getDrawable(R.drawable.adv1));
		GridView bookList = (GridView)findViewById(R.id.bookList);
		
		float density = getResources().getDisplayMetrics().density;
		float pxW = bookList.getWidth() * (density / 160f);
		float pxH = bookList.getHeight() * (density / 160f);
		Log.d("aaa xx", density + " " + bookList.getWidth() + " " + bookList.getHeight());
		Log.d("aaa xx", density + " " + pxW + " " + pxH);
		
		
		// xu li ke sach
		listBook = (GridView)findViewById(R.id.bookList);
		bookAdapater = new BookListAdapter(this,settings.arrRecentEpub);
		listBook.setAdapter(bookAdapater);
		listBook.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> av, View v, int position, long id) {
				Object o = listBook.getItemAtPosition(position);
				// continue here
				EpubInfo e = (EpubInfo)o;
				try {
					File file = new File(e.path);
	                if (file.exists()) {
	        			Intent reader = new Intent(MainActivity.this,Reader.class);
	        			reader.putExtra("epubPath", e.path);
	        			startActivity(reader);
	                }
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		//adView = (AdView) findViewById(R.id.adView);
//    	XAds xads = new XAds(adView);
//    	xads.loadAds();
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
	
	public void scaleView(View v, BitmapDrawable bmap1) {
		float w = bmap1.getBitmap().getWidth();
		float h = bmap1.getBitmap().getHeight();
		float f = calculateAspectRatio();
		LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams((int)(w*f), (int)(h*f));
		v.setLayoutParams(layout);
	}
	
	public void scaleView2(View v, int id) {
		BitmapDrawable bmap1 = (BitmapDrawable) this.getResources().getDrawable(id);
		float w = bmap1.getBitmap().getWidth();
		float h = bmap1.getBitmap().getHeight();
		float f = calculateAspectRatio();
		LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams((int)(w*f), (int)(h*f));
		v.setLayoutParams(layout);
	}
	
	public void openFileChooser(View v) {
		showFileChooser();
	}
	
	private void showFileChooser() {
		Intent intent = new Intent(getApplicationContext(), FileChooserActivity.class);
		intent.putExtra(FileChooserActivity._Rootpath, (Parcelable) new LocalFile("/sdcard/"));
		intent.putExtra(FileChooserActivity._MultiSelection, false);
		intent.putExtra(FileChooserActivity._RegexFilenameFilter, "(?si).*\\.(epub)$");
		startActivityForResult(intent, _ReqChooseFile);
		
    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//Log.d("minh debug",">> " + requestCode);
        switch (requestCode) {
            /*case FILE_SELECT_CODE:      
            	try {
		            if (resultCode == RESULT_OK) {  
		                // Get the Uri of the selected file 
		                Uri uri = data.getData();
		                // Get the path
		                String path = getPath(this, uri);
		                //Log.d("po tay", "File Path: " + path);
		                // Get the file instance
		                File file = new File(path);
		                if (file.getName().endsWith(".epub")) {
	            			Intent reader = new Intent(this,Reader.class);
	            			reader.putExtra("epubPath", path);
	            			this.startActivity(reader);
		                } else {
		                	Toast.makeText(this, "Error: Wrong format file.", Toast.LENGTH_LONG).show();
		                }
		                //Log.d("po tay", "File Name: " + file.getParent() + " > " + file.getAbsolutePath());
		            }        
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            break;*/
        	case _readerResult:
        		reloadListBook();
        		break;
            case _ReqChooseFile:
            	try {
		            if (resultCode == RESULT_OK) {  
		            	/*
		                 * you can use two flags included in data
		                 */
		                IFileProvider.FilterMode filterMode = (IFileProvider.FilterMode)
		                    data.getSerializableExtra(FileChooserActivity._FilterMode);
		                boolean saveDialog = data.getBooleanExtra(FileChooserActivity._SaveDialog, false);

		                /*
		                 * a list of files will always return,
		                 * if selection mode is single, the list contains one file
		                 */
		                List<LocalFile> files = (List<LocalFile>)data.getSerializableExtra(FileChooserActivity._Results);
		                
		                for (File file : files) {
		                	//File file = new File(path);
			                if (file.getName().endsWith(".epub")) {
		            			Intent reader = new Intent(this,Reader.class);
		            			reader.putExtra("epubPath", file.getPath());
		            			//this.startActivity(reader);
		            			startActivityForResult(reader, _readerResult);
			                } else {
			                	Toast.makeText(this, "Error: Wrong format file.", Toast.LENGTH_LONG).show();
			                }
		                }
		            }
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
	
	private String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor
                .getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
	
	public UserSettings loadXML(String xml) {
		try {
			return (UserSettings)xmlHandler.xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getXMLSettings() {
		try {
			return XCommon.getFileContent(XCommon.XML_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		reloadListBook();
	}

	public void reloadListBook() {
		xmlContent = getXMLSettings();
		Log.d("minh debug","resume activity " + xmlContent);
		if (xmlContent.length()>1) {
			settings = loadXML(xmlContent);
		} 
		
		//Log.d("aaa","resume activity " + settings.arrRecentEpub.size());
		
		bookAdapater = new BookListAdapter(MainActivity.this,settings.arrRecentEpub);
		bookAdapater.notifyDataSetChanged();
		listBook.invalidateViews();
	}
	
	
	// xu li menu
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menubar, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_settings:
				Intent setting = new Intent(MainActivity.this,Settings.class);
    			startActivity(setting);
				return true;
			case R.id.menu_themesettings:
				Intent settingTheme = new Intent(MainActivity.this,ThemeSettings.class);
    			startActivity(settingTheme);
				return true;
			default: 
				return false;
		}
	}
	
	
	// buy books
	public void clickAds(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
}
