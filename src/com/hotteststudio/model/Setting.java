package com.hotteststudio.model;

import com.hotteststudio.epubreader.MainActivity;

public class Setting {
	public int fontSize = 20;
	
	public Setting() {
		
	}
	
	public String getFlipper(int type) {
		if (type == 0)
			return "Monocle.Flippers.Instant";
		if (type == 1)
			return "Monocle.Flippers.Scroller";
		return "Monocle.Flippers.Slider";
	}
	
	public int getFontSize() {
		return 20;
	}
	
	public int getFontType() {
		return 0;
	}
	
	public int getScreenOrientation() {
		return 0;
	}
}
