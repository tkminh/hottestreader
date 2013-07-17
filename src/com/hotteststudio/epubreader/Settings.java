package com.hotteststudio.epubreader;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.hotteststudio.constant.Default;
import com.hotteststudio.model.UserSettings;
import com.hotteststudio.model.XMLHandler;
import com.hotteststudio.util.XCommon;

public class Settings extends Activity {
	
	public UserSettings settings;
	public XMLHandler xmlHandler;
	
	public SeekBar sbBrightness;
	public SeekBar sbFontSize;
	
	public RadioGroup rgFontType;
	public RadioGroup rgPageTurn;
	public RadioGroup rgScreenType;
	public RadioGroup rgLineSpacing;
	
	public RadioButton rb_TurnInstant;
	public RadioButton rb_TurnSlide;
	public RadioButton rb_TurnScroller;
	
	public RadioButton rb_screenPortrait;
	public RadioButton rb_screenLandscape;
	
	public RadioButton rb_Arial;
	public RadioButton rb_TimeNewRoman;
	public RadioButton rb_Verdana;
	public RadioButton rb_Tahoma;
	
	public RadioButton rb_LS_Small;
	public RadioButton rb_LS_Normal;
	public RadioButton rb_LS_Large;
	public RadioButton rb_LS_ExLarge;
	
	public CheckBox chkProgressbar;
	
	public DisplayMetrics metrics ;
	public int height; 
	public int width;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		if (MainActivity.xmlHandler!=null) {
			xmlHandler = MainActivity.xmlHandler;
		}
		settings = MainActivity.settings;
		initGui();
		setUpMultiScreen();
		addEvent();
		loadCurrentSetting();
	}
	
	public void initGui() {
		sbBrightness = (SeekBar)findViewById(R.id.seek_brightness);
		sbFontSize = (SeekBar)findViewById(R.id.seek_fontSize);
		
		rgPageTurn = (RadioGroup)findViewById(R.id.rg_PageTurnEffect);
		rgFontType = (RadioGroup)findViewById(R.id.rg_fontType);
		rgScreenType = (RadioGroup)findViewById(R.id.rg_ScreenType);
		rgLineSpacing = (RadioGroup)findViewById(R.id.rg_lineSpacing);
		
		rb_TurnInstant = (RadioButton)findViewById(R.id.turnInstant);
		rb_TurnScroller = (RadioButton)findViewById(R.id.turnScroll);
		rb_TurnSlide = (RadioButton)findViewById(R.id.turnSlide);
		
		rb_Arial = (RadioButton)findViewById(R.id.rg_fontArial);
		rb_Verdana = (RadioButton)findViewById(R.id.rg_fontVerdana);
		rb_Tahoma = (RadioButton)findViewById(R.id.rg_fontTahoma);
		rb_TimeNewRoman = (RadioButton)findViewById(R.id.rg_fontTimeNewRoman);
		
		rb_LS_Small = (RadioButton)findViewById(R.id.rg_ls_small);
		rb_LS_Normal = (RadioButton)findViewById(R.id.rg_ls_normal);
		rb_LS_Large = (RadioButton)findViewById(R.id.rg_ls_large);
		rb_LS_ExLarge = (RadioButton)findViewById(R.id.rg_ls_exlarge);
		
		rb_screenPortrait = (RadioButton)findViewById(R.id.screenPortrait);
		rb_screenLandscape = (RadioButton)findViewById(R.id.screenLandscape);
	}
	
	public void setUpMultiScreen() {
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		height = metrics.heightPixels;
		width = metrics.widthPixels;
		
		ImageView headerThemeSetting = (ImageView)findViewById(R.id.headerSetting);
		scaleViewR(headerThemeSetting,R.drawable.header);
		
		ImageView imgAdvSetting = (ImageView)findViewById(R.id.imgAdvSetting);
		scaleViewR(imgAdvSetting,R.drawable.adv2);
		
		ImageView leftSettingLayout = (ImageView)findViewById(R.id.leftSettingLayout);
		scaleView(leftSettingLayout,R.drawable.title_setting_configuration);
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
	
	public void addEvent() {
		sbBrightness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (progress>10 && progress<=100) {
					WindowManager.LayoutParams layout = getWindow().getAttributes();
					layout.screenBrightness = (float)progress/100;
					getWindow().setAttributes(layout);
				}
			}
		});
		
		sbFontSize.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (sbFontSize.getProgress() < 10) {
					Log.d("con me no","fak thiet");
					sbFontSize.setProgress(12);
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
					
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				
			}
		});
		
		chkProgressbar = (CheckBox) findViewById(R.id.chkProgressbar);
		 
		
	}
	
	public void loadCurrentSetting() {
		// setting font size
		if (settings.fontSize<10) {
			settings.fontSize=10;
			Toast.makeText(this, "Font size cannot be smaller than 10 !", Toast.LENGTH_SHORT).show();
		}
		sbFontSize.setProgress(settings.fontSize);
		
		chkProgressbar.setChecked(settings.showProgress);
		
		// setting brightness
		if (settings.brightness<1) settings.brightness=50;
		sbBrightness.setProgress(settings.brightness);
		
		// setting font type
		if (settings.fontType == 0)
			setCheckIndexForRadioGroup(rgFontType, rb_Arial.getId());
		else if (settings.fontType == 1)
			setCheckIndexForRadioGroup(rgFontType, rb_Tahoma.getId());
		else if (settings.fontType == 2)
			setCheckIndexForRadioGroup(rgFontType, rb_TimeNewRoman.getId());
		else if (settings.fontType == 3)
			setCheckIndexForRadioGroup(rgFontType, rb_Verdana.getId());
		
		// setting page turn
		if (settings.flipper == 0) 
			setCheckIndexForRadioGroup(rgPageTurn, rb_TurnInstant.getId());
		else if (settings.flipper == 1) 
			setCheckIndexForRadioGroup(rgPageTurn, rb_TurnSlide.getId());
		else if (settings.flipper == 2) 
			setCheckIndexForRadioGroup(rgPageTurn, rb_TurnScroller.getId());
		
		// setting font line spacing
		if (settings.lineSpacing == 0)
			setCheckIndexForRadioGroup(rgLineSpacing, rb_LS_Small.getId());
		else if (settings.lineSpacing == 1)
			setCheckIndexForRadioGroup(rgLineSpacing, rb_LS_Normal.getId());
		else if (settings.lineSpacing == 2)
			setCheckIndexForRadioGroup(rgLineSpacing, rb_LS_Large.getId());
		else if (settings.lineSpacing == 3)
			setCheckIndexForRadioGroup(rgLineSpacing, rb_LS_ExLarge.getId());

		// setting screen type
		if (settings.screenOrientation == 0)
			setCheckIndexForRadioGroup(rgScreenType, rb_screenPortrait.getId());
		else if (settings.screenOrientation == 1)
			setCheckIndexForRadioGroup(rgScreenType, rb_screenLandscape.getId());
	}
	
	// save current user setting to xml
	public void save() {
		settings.showProgress = chkProgressbar.isChecked();
		settings.brightness = sbBrightness.getProgress();
		settings.fontSize = sbFontSize.getProgress();
		settings.flipper = getIndexCheckedFromRadioGroup(rgPageTurn);
		settings.screenOrientation = getIndexCheckedFromRadioGroup(rgScreenType);
		settings.fontType = getIndexCheckedFromRadioGroup(rgFontType);
		settings.lineSpacing = getIndexCheckedFromRadioGroup(rgLineSpacing);
		
		String strXML = MainActivity.xmlHandler.xstream.toXML(settings);
		XCommon.saveTextToFile(XCommon.XML_FILE, strXML, false);
		MainActivity.settings = settings;
		Toast.makeText(this, getResources().getString(R.string.msg_save_setting), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onBackPressed() {
		save();
		super.onBackPressed();
	}
	
	public int getIndexCheckedFromRadioGroup(RadioGroup radioButtonGroup) {
		int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
		View radioButton = radioButtonGroup.findViewById(radioButtonID);
		return radioButtonGroup.indexOfChild(radioButton);
	}
	
	public void setCheckIndexForRadioGroup(RadioGroup radioButtonGroup,int id) {
		radioButtonGroup.check(id);
	}
	
	
	
	// buy books
	public void clickAds(View v) {
		XCommon.openBrowser(this, Default.URL_BOOK);
	}
	
	public void backToMainActivity(View v) {
		finish();
	}
}
