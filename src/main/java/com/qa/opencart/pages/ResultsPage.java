package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By searchHeader =  By.cssSelector("div#content h1");
	private By searchResults = By.cssSelector("div.product-thumb");

	
	public String getSearchHeader() {
		String searchHeadervalue= 
				eleUtil.waitForElementVisible(searchHeader, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	return searchHeadervalue;
	}
	
	public int getSearchResultsCount() {
		int resultCount = eleUtil.waitForElementsVisible(searchResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Search results count===========> "+resultCount);
	return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("selecting  product "+productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
}
