package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;

/**
 * 
 * @author Vijayakumar D
 *
 */
public class DriverFactory {
	
	
	
	private WebDriver driver;
	private Properties prop;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used to init driver on the basis of  given browser name
	 * @param browsername
	 * @return it returs driver
	 */
	
//	public WebDriver initDriver(String browsername) {
	public WebDriver initDriver(Properties prop) {
		
		OptionsManager optionsManager = new OptionsManager(prop);

		String browsername= prop.getProperty("browser");
		System.out.println("Browser value taken from properties file : "+browsername);
		
		switch (browsername.toLowerCase().trim()) {
		case "chrome": 
//		driver= new  ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			System.out.println("Chrome browser is launched");
		break;
		
		case "firefox": 
//			driver= new  FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			System.out.println("Firefox browser is launched");
			break;
			
		case "edge": 
//			driver= new  EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			System.out.println("Edge browser is launched");
			break;
			
		case "safari": 
//			driver= new  SafariDriver();
			tlDriver.set(new SafariDriver());
			System.out.println("Safari browser is launched");
			break;
			
			default:
				System.out.println(AppError.BROWSER_ERROR_MSG+browsername);
				throw new BrowserException(AppError.BROWSER_ERROR_MSG+browsername);
			
		}
		
//		driver.manage().window().maximize();
		getDriver().manage().window().maximize();
//		driver.manage().deleteAllCookies();
		getDriver().manage().deleteAllCookies();
//		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
//		String url=prop.getProperty("url");
		String url=prop.getProperty("url");
//		driver.get(url);
		getDriver().get(url);
		
		return getDriver();
		
		
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method to used to init the properties from the config file
	 * @return
	 */
	
	public Properties initProp() {
		prop = new Properties();
		
		try {
			FileInputStream fis= new FileInputStream(".\\src\\test\\resourcess\\config\\config.properties");
			System.out.println("FileInputStream object is created :"+fis);
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
//	private static final String OUTPUT_FOLDER1 = "./screenprints/";
//	private static final String FILE_NAME = System;

	public static String getScreenshot(String methodName) {
		
		
//		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp dir
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp dir
		
		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + "_" + System.currentTimeMillis()+ ".png";
//		String path = "D:\\Vijay\\Resumes_Vijay\\abc.png";
//		String path = "D:\\Vijay\\Resumes_Vijay\\Screenshots\\"+System.currentTimeMillis()+ ".png";
//		String path = "C:\\Users\\vijay\\NewEclipse\\NaveensProject\\screenshots\\"+System.currentTimeMillis()+ ".png";
//		String path = "C:\\Users\\vijay\\VijayScreenshots\\"+System.currentTimeMillis()+ ".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	
	
	

}
