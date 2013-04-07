package com.hotteststudio.epubreader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hotteststudio.model.BookChapter;
import com.hotteststudio.model.BookChapterAdapter;
import com.hotteststudio.model.GenBookHTML;
import com.hotteststudio.model.JSInterface;
import com.hotteststudio.util.XCommon;

public class Reader extends Activity {
	
	// layout
	public static WebView webview;
	public static ProgressDialog progressDialog;
	
	// load epub
	public GenBookHTML genbook;
	public JSInterface jsi;
	
	// duong dan file ebook dc chon
	public static String EPUB_PATH;
	public String EBOOK_FILE;
	public String EBOOK_FOLDER;
	
	public BookChapterAdapter bookChapterAdapter;
	public ListView listChapter;
	
	public boolean isShowControl = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_reader);
		EPUB_PATH = this.getIntent().getExtras().getString("epubPath");
		
		if (MainActivity.settings.screenOrientation==1) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		init();
		loadEbookContent();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {
			onMenuClick();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Toast.makeText(this, "Quit ?", Toast.LENGTH_LONG).show();
	}
	
	public void onMenuClick() {
		if (isShowControl) {
			showControl();
			isShowControl = false;
		} else {
			hideControl();
			isShowControl = true;
		}
	}
	
	public void init() {
		listChapter = (ListView)findViewById(R.id.listChapter);
		
		webview = (WebView)findViewById(R.id.webview);
		
		jsi = new JSInterface(this, webview);
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(false);
		progressDialog.show();
		
		webview.addJavascriptInterface(jsi, "api");
		webview.getSettings().setJavaScriptEnabled(true);
		webview.setVerticalScrollBarEnabled(false);
		webview.setHorizontalScrollBarEnabled(false);
		webview.getSettings().setPluginsEnabled(true);
		webview.getSettings().setAllowFileAccess(true);
		webview.getSettings().setRenderPriority(RenderPriority.HIGH);
		webview.getSettings().setTextSize(TextSize.NORMAL);		

		webview.setWebViewClient(new WebViewClient() {
			@Override  
		     public void onPageFinished(WebView view, String url) {
		         super.onPageFinished(view, url);
		         progressDialog.dismiss();
		         Log.d("Load epub", "Successful");
		         setChapter();
		     }  
		});
		
		webview.setWebChromeClient(new WebChromeClient());

	}
	
	public void loadEbookContent() {
		try {
			File epubfile = new File(EPUB_PATH);
			EBOOK_FILE = epubfile.getName();
			EBOOK_FOLDER = EBOOK_FILE.replace(".epub", "");
			EBOOK_FOLDER = XCommon.deAccent(EBOOK_FOLDER);
			EBOOK_FOLDER = EBOOK_FOLDER.replace(" ", "");
			
			String folderPath = XCommon.getRootPath() + EBOOK_FOLDER;
			File fol = new File(folderPath);
			fol.mkdir();
			
			FileInputStream file = new FileInputStream(EPUB_PATH);
			genbook = new GenBookHTML(file, EBOOK_FOLDER);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setChapter() {
		if (bookChapterAdapter==null) {
			bookChapterAdapter = new BookChapterAdapter(this,genbook.arrBookChapter);
			listChapter.setAdapter(bookChapterAdapter);
			listChapter.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> av, View v, int position, long id) {
					Object o = listChapter.getItemAtPosition(position);
					BookChapter bc = (BookChapter)o;
					//webview.loadUrl("javascript:goToChapter('" + bc.chapId + "');");
					webview.loadUrl("javascript:window.reader1.moveTo({ componentId: '" + bc.chapId + "'});");
					hideControl();
				}
			});
		}
	}
	
	public void hideControl() {
		listChapter.setVisibility(View.GONE);
		webview.setVisibility(View.VISIBLE);
	}
	
	public void showControl() {
		listChapter.setVisibility(View.VISIBLE);
		webview.setVisibility(View.GONE);
	}
	
}
