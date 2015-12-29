package com.nvg.anibis.screens.nativeapp.pagefactory;



import com.nvg.anibis.base.nativeapp.ScreenBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SearchResult extends ScreenBase {
	
	/**
	 * Constructors *
	 */
	public SearchResult(AppiumDriver<MobileElement> driver, String platform) {
		super(driver, platform);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Find elements *
	 */
	// Define the number of Results
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]")
	@AndroidFindBy(className = "android.widget.TextView")
	public static MobileElement numberOfResultsTxt;
	
	
	/**
	 * Get number of results after searching *
	 */
	public int getNumberOfResults(){
		int numberOfResult = Integer.parseInt(numberOfResultsTxt.getText().split(" ")[0]);
		System.out.println("Total records searched by Mobile app:" +numberOfResult);
		return numberOfResult;
	}
	
	

}
