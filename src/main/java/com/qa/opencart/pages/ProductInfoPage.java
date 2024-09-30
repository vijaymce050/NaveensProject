package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
//	public Map<String, String> productMap= new HashMap<String, String>();
	public Map<String, String> productMap;
	
	private By productHeader = By.cssSelector("div#content h1");
	private By productMetaData=By.xpath("(//div[@id='content']//h1/following-sibling::ul)[1]/li");
	private By productPriceData=By.xpath("(//div[@id='content']//h1/following-sibling::ul)[1]/li");
	
	private By imagesList = By.cssSelector("ul.thumbnails img");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String productHeaderValue=eleUtil.waitForElementVisible(productHeader, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	System.out.println("Product header : "+productHeaderValue);
		return productHeaderValue;
	}
	
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	private void getProductMetaData() {
//		productMap = new HashMap<String, String>();
		List<WebElement> metaList = eleUtil.waitForElementsVisible(productMetaData, AppConstants.DEFAULT_LONG_TIMEOUT);
		for(WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue =	metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	private void getProductPriceData() {
//		productMap = new HashMap<String, String>();
		List<WebElement> priceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.DEFAULT_LONG_TIMEOUT);
		System.out.println("priceList size: "+priceList.size());
		String price = priceList.get(0).getText();
		System.out.println("price value: "+price);
		
		String exTaxPrice[] = priceList.get(1).getText().split(":");
		String exTaxPrice1 = exTaxPrice[1];
		System.out.println("exTaxPrice1 "+exTaxPrice1);
		productMap.put("productPrice", price);
		productMap.put("exTaxPrice", exTaxPrice1);
	}
	
	public Map<String, String> getProductData() {
//		productMap= new LinkedHashMap<String, String>(); // It maintains the  insertion order
//		productMap= new TreeMap<String, String>();// It maintains the  alphabetical order
		productMap= new HashMap<String, String>();
		productMap.put("productHeader", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	public int getProductImagesCount() {
		int imagesCoount = eleUtil.waitForElementsVisible(imagesList, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
	System.out.println("Total images count "+imagesCoount);
		return imagesCoount;
	}
	
	
}
