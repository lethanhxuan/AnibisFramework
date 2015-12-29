package com.nvg.anibis.screens.nativeapp.pagefactory;

import java.util.List;
import com.nvg.anibis.base.nativeapp.ScreenBase;
import com.nvg.anibis.utils.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;

import static com.nvg.anibis.support.Helpers.waitMsec;

public class HomePage extends ScreenBase {

	/**
	 * HomePage Constructor *
	 */
	public HomePage(AppiumDriver<MobileElement> driver, String platform) {
		super(driver, platform);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find elements *
	 */
	// The list of 3 tabs on Home page (Search, Insertion, My Abinis)
	@AndroidFindBy(className = "android.support.v7.app.ActionBar$Tab")
	@iOSFindBys({ 
		@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATabBar[1]"),
		@iOSFindBy(className = "UIAButton")
		})
	private List<MobileElement> HomeTabs;
	
	/**
	 * Go to Search tab*
	 */
	public SearchPage gotoSearchPage() {
		HomeTabs.get(0).click();
		waitMsec(200);
		return new SearchPage(driver, platform);
	}
	
	/**
	 * Go to Insertion tab*
	 */
	public SearchPage gotoInsertionPage() {
		HomeTabs.get(1).click();
		waitMsec(200);
		return new SearchPage(driver, platform);
	}
	
	/**
	 * Go to My Anibis tab *
	 */
	public MyAnibisPage goToMyAnibisPage() {
		HomeTabs.get(2).click();
		waitMsec(200);
		return new MyAnibisPage(driver, CommonUtils.platform);
	}



}
