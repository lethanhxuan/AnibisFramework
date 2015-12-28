package com.nvg.anibis.screens.nativeapp.pagefactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
public class InsertionPage extends HomePage {
	/**
	 * Constructors *
	 */
	InsertionPage(AppiumDriver<MobileElement> driver,
			String platform) {
		super(driver, platform);
	}


}
