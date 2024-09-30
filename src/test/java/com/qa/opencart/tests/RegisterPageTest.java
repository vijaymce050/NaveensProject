package com.qa.opencart.tests;



import java.sql.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerPage=loginPage.navigateToRegisterPage();	
	}
	
	
//	@Test(priority=Integer.MAX_VALUE)
//	public void getRegisterPageTitleTest() {
//		String title=registerPage.getRegisterPageTitle();
//		System.out.println("Register page title : "+title);
//		Assert.assertTrue(title.contains("Your Account Has Been Created!"));
//	}
	
	public String randomEmail() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String email = "VijayAutomation"+System.currentTimeMillis()+"@gmail.com";
		System.out.println("Random email generated: "+email);
		return email;
	}
	
	@DataProvider
	public Object[][] userRegistrationData(){
		return new Object[][] {
			{"Vijayfn1", "Vijayln1", randomEmail(), "1234567890", "abc123","yes"},
			{"Vijayfn2", "Vijayln2", randomEmail(), "1234567890", "abc123","yes"},
			{"Vijayfn3", "Vijayln3", randomEmail(), "1234567890", "abc123","yes"},
			{"Vijayfn4", "Vijayln4", randomEmail(), "1234567890", "abc123","yes"},
			{"Vijayfn5", "Vijayln5", randomEmail(), "1234567890", "abc123","no"}
			
		};
	
	}
	
	@Test(dataProvider = "userRegistrationData")
	public void userRegistrationTest(String firstname, String lastname, String email, String telephone, String password, String subscribe) {
//		boolean isRegistrationSuccess = registerPage.userRegistration("Vijay", "test1", randomEmail(), "1234567890", "abc123", "yes");
		boolean isRegistrationSuccess = registerPage.userRegistration(firstname, lastname, email, telephone, password, subscribe);
		
		Assert.assertTrue(isRegistrationSuccess);
	
	}
	
	// to provide the data from excel sheet
	
	@DataProvider
	public Object[][] getRegData() {
		return ExcelUtil.getTestData(AppConstants.REG_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegData")
	public void userRegistrationTestByExcelData(String firstname, String lastname, String telephone, String password, String subscribe) {
		boolean isRegistrationSuccess = registerPage.userRegistration(firstname, lastname, randomEmail(), telephone, password, subscribe);
		
		Assert.assertTrue(isRegistrationSuccess);
	
	}
	
	
	
	

}
