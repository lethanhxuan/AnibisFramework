package com.nvg.anibis.screens.nativeapp.pagefactory;

import static com.nvg.anibis.support.Helpers.waitMsec;

import com.nvg.anibis.screens.nativeapp.pagefactory.HomePage;
import com.nvg.anibis.screens.nativeapp.pagefactory.LoginPage;
import com.nvg.anibis.utils.CommonUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class MyAnibisPage extends HomePage{
	
	/**
	 * Constructors *
	 */
	public MyAnibisPage(AppiumDriver<MobileElement> driver,
			String platform) {
		super(driver, platform);
		
	}
	
	/**
	 * Find elements *
	 */
	@AndroidFindBy(id ="tvLogin")
	@iOSFindBy (xpath="//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[4]")
	private MobileElement loginBtn; 

	/**
	 * Actions *
	 */
	public LoginPage openLoginPage() {
		loginBtn.click();
		return new LoginPage(driver, CommonUtils.platform);
		
	}

}
