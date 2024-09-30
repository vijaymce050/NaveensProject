package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//input[@type='radio' and @value = '1' and @name = 'newsletter']");
	private By subscribeNo = By.xpath("//input[@type='radio' and @value = '0' and @name = 'newsletter']");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox' and @name='agree']");
	private By submit = By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMessage = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	public String getRegisterPageTitle() {
		return driver.getTitle();
	}
	
	public boolean userRegistration(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(firstName);
		eleUtil.waitForElementVisible(this.lastName, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(lastName);
		eleUtil.waitForElementVisible(this.email, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(email);
		eleUtil.waitForElementVisible(this.telephone, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(telephone);
		eleUtil.waitForElementVisible(this.password, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(password);
		eleUtil.waitForElementVisible(this.confirmPassword, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		
		else {
			eleUtil.doClick(subscribeNo);
		}
		
		eleUtil.waitForElementVisible(agreeCheckBox, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
		eleUtil.waitForElementVisible(submit, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
		
		String successMsg = eleUtil.waitForElementVisible(successMessage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		if(successMsg.equals(AppConstants.USER_REG_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			eleUtil.waitForElementPresence(this.firstName, 10);
			return true;
		}
		
		else {
			return false;
		}
		
		
		
	}

}
