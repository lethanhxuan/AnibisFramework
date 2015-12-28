package com.nvg.anibis.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.nvg.anibis.support.TextScanner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class CommonUtils {

	private static Properties prop = new Properties();
	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static int DEFAULT_WAIT_TIME;
	public static String APPLICATION_NAME;
	public static String BASE_PKG;
	public static String APP_ACTIVITY;
	public static String APP_PASSWORD;
	//private static String APPIUM_PORT;
	public static String AUTOMATION_INSTRUMENTATION;
	public static String BROWSER_NAME;

	public static String ANDROID_DEVICE_NAME;
	public static String ANDROID_PLATFORM_VERSION;

	public static String IOS_DEVICE_NAME;
	public static String IOS_PLATFORM_VERSION;

	public static String NEW_COMMAND_TIMEOUT;
	public static String DEVICE_READY_TIMEOUT;
	
	public static String DEFAULT_PLATFORM;
	public static String APPIUM_SERVER_PORT;

	public static String platform;
	public static URL url;
	public static boolean realDevice = System.getProperty("real") != null;
	private static boolean jenkins = System.getProperty("jenkins") != null;
	public static Process emulator;
	
	public static String IOS_LOGIN_USER;
	public static String IOS_LOGIN_PW;
	public static String ANDROID_LOGIN_USER;
	public static String ANDROID_LOGIN_PW;
	
	/** Keep the same date prefix to identify job sets. **/
	private static Date date = new Date();
	
	
	
	// Load Config 
	public static void loadConfigProp(String propertyFileName) throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//properties//" + propertyFileName);
		prop.load(fis);

		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		APPLICATION_NAME = prop.getProperty("application.path");
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		AUTOMATION_INSTRUMENTATION = prop.getProperty("automation.instumentation");
		BROWSER_NAME = prop.getProperty("browser.name");

		DEFAULT_PLATFORM = prop.getProperty("default.platform");
		
		// load Config Properties For Android
		ANDROID_DEVICE_NAME = prop.getProperty("android.device.name");
		ANDROID_PLATFORM_VERSION = prop.getProperty("android.platform.version");

		// load Config Properties For IOS
		IOS_DEVICE_NAME = prop.getProperty("ios.device.name");
		IOS_PLATFORM_VERSION = prop.getProperty("ios.platform.version");

		NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
		DEVICE_READY_TIMEOUT = prop.getProperty("device.ready.timeout");
		APPIUM_SERVER_PORT=  prop.getProperty("appium.server.port");
		
		//load login info
		IOS_LOGIN_USER= prop.getProperty("ios.login.user");
		IOS_LOGIN_PW = prop.getProperty("ios.login.pw");
		ANDROID_LOGIN_USER = prop.getProperty("android.login.user");
		ANDROID_LOGIN_PW = prop.getProperty("android.login.pw");
	}
	
	
	
	private static String getApp() {
		switch (platform) {
		case "ios":
			if (realDevice)
				return "AnibisBeta-Real.zip";
			return "AnibisBeta.zip";
		case "android":
			return "Anibis.apk";
		default:
			throw new IllegalArgumentException("Invalid platform");
		}
	}
	
	
	// get appPath
	public static String getAppPath() throws IOException {
		String userDir = "/Users/jenkins/upload/apps";
		String localApp = getApp();
		if (!(jenkins || CommonUtils.url != null)) {
			userDir = System.getProperty("user.dir") + "/src/test/resources/app/" ;
		}
		return Paths.get(userDir, localApp).toAbsolutePath().toString();
	}
	

	public static void runAndroidSimulator() throws IOException, InterruptedException{
	Runtime rt = Runtime.getRuntime();
	//if the platform is android but it's not real device, run the below string command to start Android Simulation
	if (CommonUtils.platform.equals("android")) 
	{
		if (!CommonUtils.realDevice) {
			emulator = rt.exec("/opt/homebrew-cask/Caskroom/genymotion/2.6.0/Genymotion.app/Contents/MacOS/player.app/Contents/MacOS/player --vm-name AndroidSimulator");
			// 
			Thread.sleep(30000);
		}
	}
	}
	
	//get Driver
	public static AppiumDriver<MobileElement> getDriver(DesiredCapabilities capabilities) throws IOException {
		 
		if(System.getProperty("url") != null){
			url = new URL("http://"+ System.getProperty("url") + ":"+APPIUM_SERVER_PORT+"/wd/hub");
		}
		
		//Set the language of Simulator
		capabilities.setCapability("locale", "en_US");
		capabilities.setCapability("language", "en");
		
		capabilities.setCapability("appium-version", "1.4.13");
		capabilities.setCapability("newCommandTimeout", "-1");
		capabilities.setCapability("app", getAppPath());
		
		switch (platform) {
		case "ios": {
			capabilities.setCapability("platformName",platform);
			capabilities.setCapability("deviceName", CommonUtils.IOS_DEVICE_NAME);
			capabilities.setCapability("platformVersion", CommonUtils.IOS_PLATFORM_VERSION);
			capabilities.setCapability("sendKeyStrategy", "oneByOne");
			
			if (realDevice) {
				String deviceUDID = TextScanner.getSerial("udids.txt");
				if (deviceUDID == null)
					throw new IllegalArgumentException(
							"Device is not plugged in.");
				capabilities.setCapability("udid", deviceUDID);
				capabilities.setCapability("fullReset", true);
			}
			
			if( url!=null){
				Runtime.getRuntime().exec("sshpass -p \"07072012\" scp -v -r AnibisBeta.zip jenkins@192.168.1.148:/Users/jenkins/upload/apps");
				return new IOSDriver<MobileElement>(url, capabilities);
			}
			else{
				return new IOSDriver<MobileElement>(AppiumServer.service, capabilities);
			}
			
		}

		case "android": {
			capabilities.setCapability("name", "IS24" + date);
			capabilities.setCapability("disableAndroidWatchers", true);
			capabilities.setCapability("platformVersion", CommonUtils.ANDROID_PLATFORM_VERSION);
			capabilities.setCapability("platformName", platform);
			capabilities.setCapability("deviceName", CommonUtils.ANDROID_DEVICE_NAME);
			
			if (url != null) {
				Runtime.getRuntime().exec(
						"sshpass -p \"07072012\" scp -v -r Anibis.apk jenkins@192.168.1.148:/Users/jenkins/upload/apps");
				return new AndroidDriver<MobileElement>(url, capabilities);
			}
			else{
				return new AndroidDriver<MobileElement>(AppiumServer.service, capabilities);
			}
		
		}
		default:
			throw new IllegalArgumentException("Invalid platform");
		
		}

		}

	}
	
	
	
	
	



