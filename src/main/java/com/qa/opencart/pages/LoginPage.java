package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. private By locatorts
	// 2. public constructor
	// 3. public page actions/Methods
	
	private By username = By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPwdLink= By.linkText("Forgotten Password");
	private By logo=By.cssSelector("img.img-responsive");
	
	private By registerLink=By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getLoginPageTitle(){
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login page title :"+title);
		return title;
	}
	
	public String getLoginPageURL(){
		String url = eleUtil.getPageURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Login page url :"+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist(){
		
//		return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.isElementDisplayed(forgotPwdLink);
		
	}
	
	public boolean isLogoExist(){
//		return driver.findElement(logo).isDisplayed();
		return eleUtil.isElementDisplayed(logo);
	}
	
	
	public AccountsPage doLogin(String userName, String pwd) {
//		driver.findElement(username).sendKeys(userName);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginBtn).click();
		eleUtil.waitForElementVisible(username, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
		
		
		//To check page is redirected to post login page
//		String accPageTitle= driver.getTitle();
//		String title= eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
//		System.out.println("Title is "+title);
//		return title;
	}
	
	
	public boolean isRegisterLinkExist(){
//		return driver.findElement(logo).isDisplayed();
		return eleUtil.isElementDisplayed(registerLink);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink, AppConstants.DEFAULT_SHORT_TIMEOUT);
		return new RegisterPage(driver);
	}
	
}
