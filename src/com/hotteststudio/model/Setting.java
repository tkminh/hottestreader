package com.hotteststudio.model;

import com.hotteststudio.epubreader.MainActivity;
import com.hotteststudio.epubreader.R;

public class Setting {
	
	public Setting() {
		
	}
	
	public boolean getShowProgress() {
		return MainActivity.settings.showProgress;
	}
	
	public String getFlipper() {
		if (MainActivity.settings.flipper == 0)
			return "Monocle.Flippers.Instant";
		if (MainActivity.settings.flipper == 1)
			return "Monocle.Flippers.Slider";
		return "Monocle.Flippers.Scroller";
		//return "Monocle.Flippers.Instant";
	}
	
	public int getFontSize() {
		return MainActivity.settings.fontSize;
	}
	
	public String getFontType() {
		if (MainActivity.settings.fontType==0)
			return "Arial";
		if (MainActivity.settings.fontType==1)
			return "Tahoma";
		if (MainActivity.settings.fontType==2)
			return "TimesNewRoman";
		return "Verdana";
	}
	
	public int getScreenOrientation() {
		return MainActivity.settings.screenOrientation;
	}
	
	public String getLineSpacing() {
		if (MainActivity.settings.lineSpacing ==0) 
			return "1.2";
		if (MainActivity.settings.lineSpacing ==1) 
			return "1.5";
		if (MainActivity.settings.lineSpacing ==2) 
			return "2.0";
		return "2.5";
	}
	
	public String getTheme() {
		int theme = MainActivity.settings.theme;
		if (theme==Theme.classic_light) {
			return "bg/classic_light.jpg";
		}
		if (theme==Theme.aquarium) {
			return "bg/aquarium.jpg";
		}
		if (theme==Theme.beach_vacation) {
			return "bg/beach_vacation.jpg";
		}
		if (theme==Theme.blue_sky) {
			return "bg/blue_sky.jpg";
		}
		if (theme==Theme.classic_dark) {
			return "bg/classic_dark.jpg";
		}
		if (theme==Theme.daily_news) {
			return "bg/daily_news.jpg";
		}
		if (theme==Theme.ghost_house) {
			return "bg/ghost_house.jpg";
		}
		if (theme==Theme.green_city) {
			return "bg/green_city.jpg";
		}
		if (theme==Theme.old_fashioned) {
			return "bg/old_fashioned.jpg";
		}
		if (theme==Theme.snow_mountain) {
			return "bg/snow_mountain.jpg";
		}
		if (theme==Theme.classic_paper) {
			return "bg/classic_paper.jpg";
		}
		if (theme==Theme.classic_wood) {
			return "bg/classic_wood.jpg";
		}
		if (theme==Theme.dmabstract) {
			return "bg/dmabstract.jpg";
		}
		
		return "bg/classic_light.jpg";
	}
}
