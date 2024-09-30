package com.qa.opencart.constants;

import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_SHORT_TIMEOUT = 5;
	public static final int DEFAULT_MEDIUM_TIMEOUT = 10;
	public static final int DEFAULT_LONG_TIMEOUT = 20;
	
	public static final  int ACCOUNTS_PAGE_HEADERS_COUNT=4;
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String URL = "https://naveenautomationlabs.com/opencart/index.php?route=account/login";

	
	public static final List<String> EXPECTED_ACC_PAGE_HEADERS_LIST= List.of("My Account", "My Orders","My Affiliate Account", "Newsletter");
	
	public static final String USER_REG_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	public static final String REG_SHEET_NAME = "register";
}
