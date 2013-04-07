package com.hotteststudio.model;

import com.hotteststudio.epubreader.MainActivity;

public class Setting {
	
	public Setting() {
		
	}
	
	public String getFlipper() {
		if (MainActivity.settings.flipper == 0)
			return "Monocle.Flippers.Instant";
		if (MainActivity.settings.flipper == 1)
			return "Monocle.Flippers.Scroller";
		return "Monocle.Flippers.Slider";
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
}
