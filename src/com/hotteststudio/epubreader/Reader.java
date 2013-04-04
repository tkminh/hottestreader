package com.hotteststudio.epubreader;

import java.io.File;
import java.io.FileInputStream;

import nl.siegmann.epublib.epub.Main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hotteststudio.model.EpubInfo;
import com.hotteststudio.model.GenBookHTML;
import com.hotteststudio.model.JSInterface;
import com.hotteststudio.util.XCommon;
import com.thoughtworks.xstream.XStream;

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_reader);
		EPUB_PATH = this.getIntent().getExtras().getString("epubPath");
		
		init();
		loadEbookContent();
	}
	
	public void init() {
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
		     }  
		});
		
		webview.setWebChromeClient(new WebChromeClient());

	}
	
	public void loadEbookContent() {
		try {
			Log.d("zz","fak dm");
			File epubfile = new File(EPUB_PATH);
			EBOOK_FILE = epubfile.getName();
			EBOOK_FOLDER = EBOOK_FILE.replace(".epub", "");
			EBOOK_FOLDER = XCommon.deAccent(EBOOK_FOLDER);
			EBOOK_FOLDER = EBOOK_FOLDER.replace(" ", "");
			
			String folderPath = XCommon.getRootPath() + EBOOK_FOLDER;
			//String filePath = XCommon.getRootPath() + EBOOK_FILE;
			File fol = new File(folderPath);
			fol.mkdir();
			
			FileInputStream file = new FileInputStream(EPUB_PATH);
			genbook = new GenBookHTML(file, EBOOK_FOLDER);
			
			// tam thoi bo~ dong o duoi
			//webview.loadUrl("file://" + folderPath + "/epubtemp.html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void justClick(View v) {
		webview.loadUrl("javascript:showTOC();");
		//Log.d("aaa","fak");
	}
	
	
	
}
