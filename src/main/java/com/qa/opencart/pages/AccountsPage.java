package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	ElementUtil eleUtil;
	private By logoutLink = By.linkText("Logout");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleContainsAndReturn(AppConstants.ACCOUNT_PAGE_TITLE,
				AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Account page title :" + title);
		return title;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.isElementDisplayed(logoutLink);

	}
	
	public int getTotalAccountsPageHeader() {
		return eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
	}
	
	public List<String> getAccPageHeaders() {
		List<WebElement> headersList= eleUtil.waitForElementsVisible(headers, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		List<String> headersValueList= new ArrayList<String>();
		for(WebElement e :headersList) {
			String header = e.getText();
			headersValueList.add(header);
		}
		
		return headersValueList;
		
	
	}
	
	public ResultsPage doSearch(String searchKey) {
		WebElement searchElment = eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIMEOUT);
		eleUtil.doSendKeys(searchElment, searchKey);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}

}
