package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	
	
	@Test
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
//		Assert.assertEquals(actTitle, "Account Login");
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Test
	public void loginPageURLTest() {
		String actURL=loginPage.getLoginPageURL();
//		Assert.assertTrue(actURL.contains("route=account/login"));
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	public void isLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}
	
	@Test(priority=Integer.MAX_VALUE)
	public void loginTest() {
		String username= prop.getProperty("username");
		String password= prop.getProperty("password");
		System.out.println("username and password : "+username+ "  :" + password);
//		String accPageTitle=loginPage.doLogin("vijaymce050@gmail.com", "Vijay86@opencart");
		accPage=loginPage.doLogin(username, password);
//		Assert.assertEquals(accPageTitle, "My Account");
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
		
	}

}
