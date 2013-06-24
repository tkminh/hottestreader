package com.hotteststudio.epubreader;

import com.hotteststudio.constant.Default;
import com.hotteststudio.model.UserSettings;
import com.hotteststudio.model.XMLHandler;
import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemeSettings extends Activity {

	public int theme;
	public UserSettings settings;
	public XMLHandler xmlHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themesettings);
		if (MainActivity.xmlHandler!=null) {
			xmlHandler = MainActivity.xmlHandler;
		}
		settings = MainActivity.settings;
	}
	
	@Override
	public void onBackPressed() {
		save();
		super.onBackPressed();
	}
	
	public void save() {
		if (theme == 0) theme = R.drawable.classic_light_active;
		settings.theme = theme;
		String strXML = MainActivity.xmlHandler.xstream.toXML(settings);
		XCommon.saveTextToFile(XCommon.XML_FILE, strXML, false);
		MainActivity.settings = settings;
		Toast.makeText(this, getResources().getString(R.string.msg_save_setting), Toast.LENGTH_SHORT).show();
	}
	
	// buy books
	public void clickAds(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
	
	public void clearSelection() {
		ImageView vv = (ImageView)findViewById(R.id.imgClassicLight);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_light));
		
		vv = (ImageView)findViewById(R.id.imgGreenCity);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.green_city));
		
		vv = (ImageView)findViewById(R.id.imgAquarium);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.aquarium));
		
		vv = (ImageView)findViewById(R.id.imgBeachVacation);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.beach_vacation));
		
		vv = (ImageView)findViewById(R.id.imgBlueSky);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.blue_sky));
		
		vv = (ImageView)findViewById(R.id.imgClassicDark);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_dark));
		
		vv = (ImageView)findViewById(R.id.imgDailyNews);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.daily_news));
		
		vv = (ImageView)findViewById(R.id.imgGhostHouse);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.ghost_house));
		
		vv = (ImageView)findViewById(R.id.imgOldFashioned);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.old_fashioned));
		
		vv = (ImageView)findViewById(R.id.imgSnowMountain);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.snow_mountain));
	}
	
	// theme click
	public void clickClassicLight(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_light_active));
		theme = R.drawable.classic_light_active;
	}
	
	public void clickGreenCity(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.green_city_active));
		theme = R.drawable.green_city_active;
	}
	
	public void clickAquarium(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.aquarium_active));
		theme = R.drawable.aquarium_active;
	}
	
	public void clickBeachVacation(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.beach_vacation_active));
		theme = R.drawable.beach_vacation_active;
	}
	
	public void clickBlueSky(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.blue_sky_active));
		theme = R.drawable.blue_sky_active;
	}
	
	public void clickClassicDark(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_dark_active));
		theme = R.drawable.classic_dark_active;
	}
	
	public void clickDailyNews(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.daily_news_active));
		theme = R.drawable.daily_news_active;
	}
	
	public void clickGhostHouse(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.ghost_house_active));
		theme = R.drawable.ghost_house_active;
	}
	
	public void clickOldFashioned(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.old_fashioned_active));
		theme = R.drawable.old_fashioned_active;
	}
	
	public void clickSnowMountain(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.snow_mountain_active));
		theme = R.drawable.snow_mountain_active;
	}
}
