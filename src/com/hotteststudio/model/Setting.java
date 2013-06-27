package com.hotteststudio.model;

import com.hotteststudio.epubreader.MainActivity;
import com.hotteststudio.epubreader.R;

public class Setting {
	
	public Setting() {
		
	}
	
	public String getFlipper() {
		if (MainActivity.settings.flipper == 0)
			return "Monocle.Flippers.Instant";
//		if (MainActivity.settings.flipper == 1)
//			return "Monocle.Flippers.Scroller";
//		return "Monocle.Flippers.Slider";
		return "Monocle.Flippers.Instant";
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
		if (theme==R.drawable.classic_light_active) {
			return "bg/classic_light.jpg";
		}
		if (theme==R.drawable.aquarium_active) {
			return "bg/aquarium.jpg";
		}
		if (theme==R.drawable.beach_vacation_active) {
			return "bg/beach_vacation.jpg";
		}
		if (theme==R.drawable.blue_sky_active) {
			return "bg/blue_sky.jpg";
		}
		if (theme==R.drawable.classic_dark_active) {
			return "bg/classic_dark.jpg";
		}
		if (theme==R.drawable.daily_news_active) {
			return "bg/daily_news.jpg";
		}
		if (theme==R.drawable.ghost_house_active) {
			return "bg/ghost_house.jpg";
		}
		if (theme==R.drawable.green_city_active) {
			return "bg/green_city.jpg";
		}
		if (theme==R.drawable.old_fashioned_active) {
			return "bg/old_fashioned.jpg";
		}
		if (theme==R.drawable.snow_mountain_active) {
			return "bg/snow_mountain.jpg";
		}
		if (theme==R.drawable.classic_paper_active) {
			return "bg/classic_paper.jpg";
		}
		if (theme==R.drawable.classic_wood_active) {
			return "bg/classic_wood.jpg";
		}
		if (theme==R.drawable.dmabstract_active) {
			return "bg/dmabstract.jpg";
		}
		
		return "bg/classic_light.jpg";
	}
}
