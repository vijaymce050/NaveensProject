package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

//	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	
	private static final String TEST_DATA_SHEET_PATH = "C:\\Users\\vijay\\NewEclipse\\NaveensProject\\src\\test\\resourcess\\testdata\\OpenCartTestData.xlsx";
	private static Workbook workbook;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		
		Object data [][]=null;
		System.out.println("Reading the data from the sheet "+sheetName);
		
		try {
			FileInputStream fip = new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				workbook = WorkbookFactory.create(fip);
				sheet = workbook.getSheet(sheetName);	
				int totalRows=sheet.getLastRowNum();
				System.out.println("totalRows :"+totalRows);
				int totalColumns = sheet.getRow(0).getLastCellNum();
				System.out.println("totalColumns :"+totalColumns);
				data = new Object[totalRows][totalColumns];
				
				for(int i=0; i<totalRows; i++) {
					for(int j=0; j<totalColumns; j++) {
						data[i][j]=sheet.getRow(i+1).getCell(j).toString();
						System.out.println("Data value :"+data[i][j]);
					}
				}
				
				System.out.println("Data of array is "+data);
				
				
			} catch (InvalidFormatException | IOException e) {
				e.printStackTrace();
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void main(String args[]) {
		System.out.println("Test started");
		getTestData("register");
		System.out.println("Test Ended");
		
	}
	
}
