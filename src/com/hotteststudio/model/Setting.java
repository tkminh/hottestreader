package com.hotteststudio.model;

public class Setting {
	public int fontSize = 20;
	
	
	public String getFlipper(int type) {
		if (type == 0)
			return "Monocle.Flippers.Instant";
		if (type == 1)
			return "Monocle.Flippers.Scroller";
		return "Monocle.Flippers.Slider";
	}
}
