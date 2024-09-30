package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle=accPage.getAccountPageTitle();
//		Assert.assertEquals(actTitle, "Account Login");
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void isLogoutLinkExists() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void pageHeadersCountTest() {
		accPage.getTotalAccountsPageHeader();
		Assert.assertEquals(accPage.getTotalAccountsPageHeader(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actualHeadersList =accPage.getAccPageHeaders();
		Assert.assertEquals(actualHeadersList, AppConstants.EXPECTED_ACC_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2}
		};
	}
	
/*	@Test
	public void searchCountTest() {
		resultsPage = accPage.doSearch("macbook");
		Assert.assertEquals(resultsPage.getSearchResultsCount(), 3);
	}
*/
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey, int searchCount) {
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), searchCount);
	}

	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung Galaxy Tab 10.1"}
		};
	}
	
	// we can get a data from excel for the above method as alternative
	
	@DataProvider
	public Object[][] getSearchDataFromExcel() {
		return ExcelUtil.getTestData("search");
	}
	
	
	
	
//	@Test(dataProvider = "getSearchData")
	@Test(dataProvider = "getSearchDataFromExcel")
	public void searchTest(String searchKey, String selectProduct) {
		resultsPage = accPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(selectProduct);
//		productInfoPage.getProductHeader();
		Assert.assertEquals(productInfoPage.getProductHeader(), selectProduct);
	}
	
}
