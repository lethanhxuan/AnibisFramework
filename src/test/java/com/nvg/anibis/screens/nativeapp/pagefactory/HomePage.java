package com.nvg.anibis.screens.nativeapp.pagefactory;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.nvg.anibis.base.nativeapp.ScreenBase;
import com.nvg.anibis.support.Helpers;
import com.nvg.anibis.utils.CommonUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import static com.nvg.anibis.support.Helpers.timeOut;
import static com.nvg.anibis.support.Helpers.waitForPopUp;
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
	
	// The list of tabs on Home page
		@AndroidFindBy(className = "android.support.v7.app.ActionBar$Tab")
		@iOSFindBy(name = "My Anibis")
		private List<MobileElement> HomeTabs;
		
	
	/**
	 * Go to My Anibis tab *
	 */	 
		public MyAnibisPage goToMyAnibisPage(){
			if (platform.toLowerCase().equals("android")) 
			{
				System.out.println("Trying to tap on My Anibis tab");
				HomeTabs.get(2).click();	
			} 
			else 
			{
				HomeTabs.get(0).click();
			}
			return new MyAnibisPage(driver,CommonUtils.platform);
			
		}

}
