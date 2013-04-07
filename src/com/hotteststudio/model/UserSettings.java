package com.hotteststudio.model;

import java.util.ArrayList;

public class UserSettings {
	
	public int fontSize = 20;
	public int flipper; // 0 Instant 1 Scroller 2 Slider
	public int brightness; // 10 to 100
	public int fontType; // list of 3 font
	public int lineSpacing;
	public int screenOrientation;
	
	public ArrayList<EpubInfo> arrRecentEpub = new ArrayList<EpubInfo>(); // books that user has selected
	
	
	public UserSettings() {
		
	}
}
