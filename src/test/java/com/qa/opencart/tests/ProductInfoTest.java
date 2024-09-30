package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void getProductDatTest() {
		resultsPage = accPage.doSearch("macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDataResult = productInfoPage.getProductData();
		System.out.println("productDataResult :"+productDataResult);
		softAssert.assertEquals(productDataResult.get("Brand"), "Apple");
		softAssert.assertEquals(productDataResult.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDataResult.get("Reward Points"), "800");
		softAssert.assertEquals(productDataResult.get("Availability"), "In Stock");
		softAssert.assertAll();
		
	}
	
	@Test
	public void productHeaderTest() {
		resultsPage = accPage.doSearch("macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductImagesCountData() {
		
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
		};
	}
	
	@Test(dataProvider = "getProductImagesCountData")
	public void getProductoImagesCountTest(String searchKey, String product, int imagesCount) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(product);
		int imagesCount1 = productInfoPage.getProductImagesCount();
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imagesCount);
		
		
		
	}
	
	
	
}
