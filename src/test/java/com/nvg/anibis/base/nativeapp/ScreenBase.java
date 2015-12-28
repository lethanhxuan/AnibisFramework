package com.nvg.anibis.base.nativeapp;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindAll;
import io.appium.java_client.pagefactory.iOSFindBy;
import com.nvg.anibis.support.Helpers;
import static com.nvg.anibis.support.Helpers.timeOut;
import static com.nvg.anibis.support.Helpers.waitForPopUp;
import static com.nvg.anibis.support.Helpers.waitMsec;

public abstract class ScreenBase {


	protected static AppiumDriver<MobileElement> driver;
	protected static String platform;
	protected static boolean hasSettings = true;
	
	
	/**
	 * Constructor *
	 */
	public ScreenBase(AppiumDriver<MobileElement> driver, String platform) {
		ScreenBase.platform = platform;
		ScreenBase.driver = driver;
		loadElements();
	}
	public void loadElements(){
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5,
				TimeUnit.SECONDS), this);
	}

	/**
	 * Find elements *
	 */


	@iOSFindAll({ @iOSFindBy(uiAutomator = ".collectionViews()[0].cells()"),
			@iOSFindBy(uiAutomator = ".tableViews()[0].visibleCells()") })
	@AndroidFindBy(id = "cattext")
	private List<MobileElement> allActiveCells;

	@iOSFindBy(uiAutomator = ".navigationBar().rightButton()")
	@AndroidFindBy(id = "android:id/button1")
	protected MobileElement rightButton;

	@iOSFindBy(uiAutomator = ".navigationBar().leftButton()")
	@AndroidFindBy(id = "android:id/button0")
	protected MobileElement leftButton;

	@iOSFindBy(name = "Settings")
	@AndroidFindBy(id = "android:id/button0")
	protected MobileElement settingBack;
	

	/**
	 * To handle filling out a textfield  *
	 */
	public void fillText (MobileElement el, String text) {
		if (platform.equals("android")) {
			el.clear();
			el.sendKeys(text);
		} else {
			((IOSElement) el).setValue(text);
		}
	}

	

}
