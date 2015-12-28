package com.nvg.anibis.base.nativeapp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.nvg.anibis.support.Helpers;
import com.nvg.anibis.support.Condition;
import com.nvg.anibis.support.TextScanner;
import com.nvg.anibis.utils.AppiumServer;
import com.nvg.anibis.utils.CommonUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class TestBase {
	
	public static AppiumDriver<MobileElement> driver;
	private static AppiumServer service = new AppiumServer();
	public static int testNo = 0;
	public static String packageName;
	public static byte[] capturePage;
	protected static TouchAction action;
	public static Condition deviceCondition;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	
	@BeforeSuite
	public void setUp() throws IOException, InterruptedException{
		
		
		// load config properties
		CommonUtils.loadConfigProp("app.properties");
		CommonUtils.platform = System.getProperty("platform", CommonUtils.DEFAULT_PLATFORM);
		
		//Start appium server programmatically
		service.start();

		//Start AndroidSimulator with Genymotion and Virtual Box . It'd be faster than the default one of Appium.
		CommonUtils.runAndroidSimulator();
	
		//Run the default Android simulator/ iOS emulator of appium and start app
		System.out.println("\n[INFO] Running test for the app at: "
				+ CommonUtils.getAppPath() + "\n");
		driver = CommonUtils.getDriver (capabilities);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Try to pass initial Screen
		try {
			driver.findElementByName("Ignore").click();
		} catch (NoSuchElementException e) {
			try {
				driver.findElementByName("Dismiss").click();
			} catch (NoSuchElementException e1) {}
			}
		try {
			driver.findElementByName("Deutsch").click();
		} catch (NoSuchElementException e) {}


		}
	
	
	@AfterSuite
	public void tearDown() throws IOException{
	/*
		//Stop Appium server and driver
		if (driver != null) driver.quit();
		if (service !=null) service.stop();
		
	  //Executes a specified string command to turn off VBoxManager and AndroidSimulator
		Runtime rt = Runtime.getRuntime();
		if (CommonUtils.platform.equals("android") && !CommonUtils.realDevice) {
		try {
			rt.exec("/usr/local/bin/VBoxManage controlvm AndroidSimulator poweroff");
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommonUtils.emulator.destroyForcibly();
		}
		
		
	*/

		
}


}