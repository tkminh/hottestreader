package com.hotteststudio.epubreader;

import com.hotteststudio.constant.Default;
import com.hotteststudio.model.UserSettings;
import com.hotteststudio.model.XMLHandler;
import com.hotteststudio.util.XCommon;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ThemeSettings extends Activity {

	public int theme;
	public UserSettings settings;
	public XMLHandler xmlHandler;
	
	public DisplayMetrics metrics ;
	public int height; 
	public int width;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.themesettings);
		if (MainActivity.xmlHandler!=null) {
			xmlHandler = MainActivity.xmlHandler;
		}
		settings = MainActivity.settings;
		
		setUpMultiScreen();
		loadCurrentSetting();
	}
	
	@Override
	public void onBackPressed() {
		save();
		super.onBackPressed();
	}
	
	public void loadCurrentSetting() {
		ImageView vv = (ImageView)findViewById(R.id.imgClassicLight);
		
		if (settings.theme==R.drawable.classic_light_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_light_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_light));
		}
		
		vv = (ImageView)findViewById(R.id.imgClassicPaper);
		if (settings.theme==R.drawable.classic_paper_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_paper_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_paper));
		}
		
		
		vv = (ImageView)findViewById(R.id.imgClassicWood);
		if (settings.theme==R.drawable.classic_wood_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_wood_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_wood));
		}
		
		
		vv = (ImageView)findViewById(R.id.dmabstract);
		if (settings.theme==R.drawable.dmabstract_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.dmabstract_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.dmabstract));
		}
		
		
		vv = (ImageView)findViewById(R.id.imgGreenCity);
		if (settings.theme==R.drawable.green_city_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.green_city_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.green_city));
		}
		
		vv = (ImageView)findViewById(R.id.imgAquarium);
		if (settings.theme==R.drawable.aquarium_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.aquarium_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.aquarium));
		}
		
		vv = (ImageView)findViewById(R.id.imgBeachVacation);
		if (settings.theme==R.drawable.beach_vacation_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.beach_vacation_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.beach_vacation));
		}
		
		vv = (ImageView)findViewById(R.id.imgBlueSky);
		if (settings.theme==R.drawable.blue_sky_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.blue_sky_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.blue_sky));
		}
		
		vv = (ImageView)findViewById(R.id.imgClassicDark);
		if (settings.theme==R.drawable.classic_dark_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_dark_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_dark));
		}
		
		vv = (ImageView)findViewById(R.id.imgDailyNews);
		if (settings.theme==R.drawable.daily_news_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.daily_news_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.daily_news));
		}
		
		vv = (ImageView)findViewById(R.id.imgGhostHouse);
		if (settings.theme==R.drawable.ghost_house_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.ghost_house_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.ghost_house));
		}
		
		vv = (ImageView)findViewById(R.id.imgOldFashioned);
		if (settings.theme==R.drawable.old_fashioned_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.old_fashioned_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.old_fashioned));
		}
		
		vv = (ImageView)findViewById(R.id.imgSnowMountain);
		if (settings.theme==R.drawable.snow_mountain_active) {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.snow_mountain_active));
		} else {
			vv.setImageDrawable(getResources().getDrawable(R.drawable.snow_mountain));
		}
	}
	
	public void setUpMultiScreen() {
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		ImageView headerThemeSetting = (ImageView)findViewById(R.id.headerThemeSetting);
		scaleViewR(headerThemeSetting,R.drawable.header);
		
		ImageView advThemeSetting = (ImageView)findViewById(R.id.imgAdvThemeSetting);
		scaleViewR(advThemeSetting,R.drawable.adv1);
		
		ImageView leftLayoutThemeSetting = (ImageView)findViewById(R.id.leftLayoutThemeSetting);
		scaleView(leftLayoutThemeSetting,R.drawable.title_theme_setting);
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
	
	public void scaleView(View v, int id) {
		BitmapDrawable bmap1 = (BitmapDrawable) this.getResources().getDrawable(id);
		float w = bmap1.getBitmap().getWidth();
		float h = bmap1.getBitmap().getHeight();
		float f = calculateAspectRatio();
		LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams((int)(w*f), (int)(h*f));
		v.setLayoutParams(layout);
	}
	
	public void scaleViewR(View v, int id) {
		BitmapDrawable bmap1 = (BitmapDrawable) this.getResources().getDrawable(id);
		float w = bmap1.getBitmap().getWidth();
		float h = bmap1.getBitmap().getHeight();
		
		float wRatio = width / w;
		float hRatio = height / h;
		 
		float f = wRatio;
		if (hRatio < wRatio) {
			f = hRatio;
		}
		
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams((int)(w*f), (int)(h*f));
		v.setLayoutParams(layout);
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
		
		vv = (ImageView)findViewById(R.id.imgClassicPaper);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_paper));
		
		vv = (ImageView)findViewById(R.id.imgClassicWood);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_wood));
		
		vv = (ImageView)findViewById(R.id.dmabstract);
		vv.setImageDrawable(getResources().getDrawable(R.drawable.dmabstract));
		
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
	
	public void clickClassicPaper(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_paper_active));
		theme = R.drawable.classic_paper_active;
	}
	
	public void clickClassicWood(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.classic_wood_active));
		theme = R.drawable.classic_wood_active;
	}
	
	public void clickAbstract(View v) {
		clearSelection();
		ImageView vv = (ImageView)v;
		vv.setImageDrawable(getResources().getDrawable(R.drawable.dmabstract_active));
		theme = R.drawable.dmabstract_active;
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
