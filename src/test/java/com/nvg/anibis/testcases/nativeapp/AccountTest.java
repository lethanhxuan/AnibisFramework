package com.nvg.anibis.testcases.nativeapp;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nvg.anibis.base.nativeapp.TestBase;
import com.nvg.anibis.screens.nativeapp.pagefactory.HomePage;
import com.nvg.anibis.screens.nativeapp.pagefactory.LoginPage;
import com.nvg.anibis.utils.CommonUtils;

public class AccountTest extends TestBase {
	

	/**
	 * Go to LogIn Screen before doing any test here*
	 */
	@BeforeTest
	public void goToLogInScreen() throws InterruptedException{
		HomePage hp = new HomePage(driver, CommonUtils.platform);
			hp.goToMyAnibisPage().openLoginPage();
			
	}
	/**
	 * Verify that user is able to login with valid credentials*
	 */
	@Test
	public void validLogInTest() throws InterruptedException{
		executeInTryCatch(driver->{
		LoginPage lip = new LoginPage(driver, CommonUtils.platform);
		 lip.validLogin();});	 
	}

}
