package com.nvg.anibis.screens.nativeapp.pagefactory;

import static com.nvg.anibis.support.Helpers.waitMsec;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import com.nvg.anibis.base.nativeapp.ScreenBase;
import com.nvg.anibis.support.Helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;

public class SettingsPage extends ScreenBase{
	/**
	 * Setting Page Constructors *
	 */
	public SettingsPage(AppiumDriver<MobileElement> driver, String platform) {
		super(driver, platform);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), this);
	}
	
	/**
	 * Find elements *
	 */
	
	//Define Api Server button
	@iOSFindBy(xpath = "//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[2]")
	@AndroidFindBy(id = "summary")
	MobileElement apiServerBtn;
	
	@iOSFindBy(name = "Location")
	@AndroidFindBy(name = "Set Current Location")
	MobileElement currentLocationBtn;
	
	@iOSFindBy(name = "Current")
	MobileElement currentBtn;
	
	@iOSFindBy(name = "Done")
	MobileElement doneBtn;
	
	//Define the list of Api servers
	@AndroidFindBys({
		@AndroidFindBy(id="select_dialog_listview"),
		@AndroidFindBy(className="android.widget.CheckedTextView"),	
	})
	@iOSFindBys({
		@iOSFindBy(className ="UIATableView"),
		@iOSFindBy(className ="UIATableCell")
	})
	List<MobileElement> apiServerList; 
	
	/**
	 * open current server and choose first server *
	 */
		public SettingsPage clickDone(){
			doneBtn.click();
			return this ;
		}

	/**
	 * *
	 */

	public SettingsPage chooseServer(int index){
		if(hasSettings){
			apiServerBtn.click();
			waitMsec(300);
			if(platform.equals("android")) {
				apiServerList.get(index).click();
			}
			else
			{
				System.out.println(apiServerList.get(index).getAttribute("name"));
				apiServerList.get(index).click();
				back();
				
			}
			Helpers.waitMsec(300);
		}
		return this;
	}
	
	/**
	 * *
	 */
	public SettingsPage setCurrentLocation(String location){
		if(hasSettings){
			currentLocationBtn.click();
			clickName(location);
			if(!platform.equals("android")) back();
		}
		return this;
	}
	
	/**
	 * *
	 */
	public HomePage backtoSearch(){
		if(platform.equals("android")){
			back();
		}else{
			rightButton.click();
		}
		Helpers.waitMsec(500);
		return new HomePage (driver, platform);
	}

}
