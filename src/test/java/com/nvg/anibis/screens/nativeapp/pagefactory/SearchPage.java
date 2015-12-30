package com.nvg.anibis.screens.nativeapp.pagefactory;

import com.nvg.anibis.base.nativeapp.ScreenBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import static com.nvg.anibis.support.Helpers.waitMsec;

public class SearchPage extends ScreenBase {

	/**
	 * SearchPage Constructor *
	 */

	public SearchPage(AppiumDriver<MobileElement> driver, String platform) {
		super(driver, platform);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Find elements *
	 */
	// Define the Keyword Search field
	@iOSFindBy(className = "UIATextField")
	@AndroidFindBy(id = "search_src_text")
	private MobileElement keywordTxt;

	// Define the Search button
	@iOSFindBy(name = "Suchen")
	@AndroidFindBy(accessibility = "Suchanfrage")
	private MobileElement btnSearch;

	// Define the button for searching real estates (rent)
	@iOSFindBy(name = "Immobilien (mieten)")
	@AndroidFindBy(id = "tvCatImmoRent")
	private MobileElement realEstateRentBtn;

	// Define the button for searching real estates (buy)
	@iOSFindBy(name = "Immobilien (kaufen)")
	@AndroidFindBy(id = "tvCatImmoBuy")
	private MobileElement realEstateBuyBtn;

	// Define the button for searching cars
	@iOSFindBy(name = "Autos")
	@AndroidFindBy(id = "tvCatAuto")
	private MobileElement AutoBtn;

	// Define the button for searching jobs
	@iOSFindBy(name = "Jobs")
	@AndroidFindBy(id = "llCatJob")
	private MobileElement JobBtn;

	// Define the button for searching all categories
	@iOSFindBy(name = "Alle Kategorien")
	@AndroidFindBy(id = "tvCatAll")
	private MobileElement AllCategoryBtn;

	// Define the Setting button
	@iOSFindBy(xpath= "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[6]/UIAStaticText[1]")
	@AndroidFindBy(id = "llSetting")
	private MobileElement settingBtn;

	/**
	 * Enter a text for searching and click Search button *
	 */
	public SearchResult search(String value) {
		waitMsec(500);
		keywordTxt.clear();
		keywordTxt.sendKeys(value);
		waitMsec(500);

		if (platform.equals("android")) {
			((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.ENTER);
		} else {
			btnSearch.click();
			waitMsec(500);
		}

		return new SearchResult(driver, platform);
	}
	/**
	 * Enter a text for searching and click Search button *
	 */
	
	public SettingsPage goToSettingPage() {
	    if (platform.equals("android")) androidScrollToText("Setting");

	    settingBtn.click();
		waitMsec(500);

		return new SettingsPage(driver, platform);
	
	}

}
