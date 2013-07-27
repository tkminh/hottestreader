package com.hotteststudio.epubreader;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.google.ads.AdView;
import com.hotteststudio.model.UserSettings;
import com.hotteststudio.model.XMLHandler;
import com.hotteststudio.util.XAds;
import com.hotteststudio.util.XCommon;

public class TabLayoutActivity extends TabActivity {

	
	public AdView adView;
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        
        XCommon.strictmodePolicy();
        
        final TabHost tabHost = getTabHost();
       
        
        // Tab for Intro
        TabSpec photospec = tabHost.newTabSpec("Intro");
        // setting Title and Icon for the Tab
        photospec.setIndicator("", getResources().getDrawable(R.drawable.icon_introduce_tab));
        Intent photosIntent = new Intent(this, IntroActivity.class);
        photospec.setContent(photosIntent);
        
        // Tab for Main
        TabSpec mainspec = tabHost.newTabSpec("Main");
        // setting Title and Icon for the Tab
        mainspec.setIndicator("", getResources().getDrawable(R.drawable.icon_library_tab));
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainspec.setContent(mainIntent);
        //MainActivity.loadXMLSetting();
        
        // Tab for Setting
        TabSpec songspec = tabHost.newTabSpec("Setting");       
        songspec.setIndicator("", getResources().getDrawable(R.drawable.icon_setting_tab));
        Intent songsIntent = new Intent(this, Settings.class);
        songspec.setContent(songsIntent);
         
        // Tab for Theme
        TabSpec videospec = tabHost.newTabSpec("Theme");
        videospec.setIndicator("", getResources().getDrawable(R.drawable.icon_theme_tab));
        Intent videosIntent = new Intent(this, ThemeSettings.class);
        videospec.setContent(videosIntent);
        
        // Tab for bookstore
        TabSpec bookstorespec = tabHost.newTabSpec("Bookstore");
        bookstorespec.setIndicator("", getResources().getDrawable(R.drawable.icon_bookstore_tab));
        Intent bookstoreIntent = new Intent(this, BookstoreActivity.class);
        bookstorespec.setContent(bookstoreIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.getTabWidget().setBackgroundResource(R.drawable.footer);
        tabHost.addTab(photospec);
        tabHost.addTab(mainspec); 
        tabHost.addTab(songspec); 
        tabHost.addTab(videospec);
        tabHost.addTab(bookstorespec);
        
//        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener(){
//    	  @Override
//    	  public void onTabChanged(String tabId) {
//    	    int tab = tabHost.getCurrentTab();
//    	    tabHost.getTabWidget().getChildAt(tab).setBackgroundColor(Color.CYAN);
//    	  }
//    	});
        
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
        	tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#000000ff"));
        }

        
        
        adView = (AdView) findViewById(R.id.adViewTab);
    	XAds xads = new XAds(adView);
    	xads.loadAds();
        
        UserSettings settings = MainActivity.settings;
        if (settings!=null && settings.isFirst) {
        	settings.isFirst = false;
        	XMLHandler xmlHandler = new XMLHandler();
			String strXML = xmlHandler.xstream.toXML(settings);
			XCommon.saveTextToFile(XCommon.XML_FILE, strXML, false);
			tabHost.setCurrentTab(0);
        } else {
        	tabHost.setCurrentTab(1);
        }
        
        
        
        
	}
}
