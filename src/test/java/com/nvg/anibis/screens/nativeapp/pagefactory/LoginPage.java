package com.nvg.anibis.screens.nativeapp.pagefactory;

import static com.nvg.anibis.support.Helpers.waitMsec;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import com.nvg.anibis.base.nativeapp.ScreenBase;
import  com.nvg.anibis.screens.nativeapp.pagefactory.LoginPage;
import com.nvg.anibis.support.Helpers;
import com.nvg.anibis.utils.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSFindBy;
import junit.framework.AssertionFailedError;

public class LoginPage extends ScreenBase{
	
	/**
	 * Constructors *
	 */
	public LoginPage(AppiumDriver<MobileElement> driver,
			String platform) {
		super(driver, platform);
	}
	
	
	/**
	 * Find Elements *
	 */
	
	// Find Username textfield
	@iOSFindBy(className = "UIATextField")
	@AndroidFindBy(id = "edtUsername")
	private MobileElement usernameTxt;
	
	//Find Password textfield
	@iOSFindBy(className = "UIASecureTextField") 
	@AndroidFindBy(id = "edtPassword")
	private MobileElement passwordTxt;
	
	// Find Button Login
	@iOSFindBy(uiAutomator = ".navigationBar().rightButton()")
	@AndroidFindBy(id = "btnLogin")
	private MobileElement loginBtn;
	
	// Find Button Log Out (after Logging in successfully)
	@AndroidFindBy(id = "vgListings")
	@iOSFindBy(name = "Abmelden")
	@WithTimeout(time = 30, unit = TimeUnit.SECONDS)
	private MobileElement loggedInIdentifier; 
	

	/**
	 * 	Login with valid username and password *
	 */
	public LoginPage validLogin() {
		if (platform.equals("android")) {
			return loginWith(CommonUtils.ANDROID_LOGIN_USER, CommonUtils.IOS_LOGIN_PW);
		} else {
			return loginWith(CommonUtils.IOS_LOGIN_USER, CommonUtils.IOS_LOGIN_PW);
		}
	}
	private LoginPage loginWith(String username, String password) {
		fillName(username).fillPassword(password).loginConfirmation();
		return waitForLogin();
	}
	
	/**
	 * To wait for Log-out button is displayed and the login process is completely finished*
	 */
	private LoginPage waitForLogin() {
		waitMsec(3000);
		try {
			loggedInIdentifier.isDisplayed();
			Helpers.waitSec(2);
			return this;
		} catch (NoSuchElementException e) {
			throw new AssertionFailedError("Cannot login!");
		}
	}
	
	/**
	 * Enter Username to Login *
	 */
	private LoginPage fillName(String text) {
		fillText(usernameTxt, text);
		return this;
	}
	
	/**
	 * Enter Password to Login *
	 */
	private LoginPage fillPassword(String text) {
		fillText(passwordTxt, text);
		return this;
	}
	
	/**
	 * Click Login button to get access the profile *
	 */
	private LoginPage loginConfirmation() {
		waitMsec(500);
		loginBtn.click();
		return this;
	}


}
