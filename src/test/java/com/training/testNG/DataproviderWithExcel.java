 package com.training.testNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataproviderWithExcel {

	WebDriver driver;

	@BeforeMethod
	public void launchbrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
	}

	@Test(dataProvider = "getData")
	public void login(String username, String password) {

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.xpath("//img[@id='phHeaderLogoImage']")).isDisplayed();

	}
 
	// if @Dataprovider is in another class then we use @Test(dataProvider = "getData",dataProviderClass=classname.class)
	// for run @Dataprovider parallely  parallel=true and in testng.xml add in suite level data-provider-thread-count="3"
	
	@DataProvider(name="getData")
	public Object[][] getData() throws IOException {

		File excelFile = new File(".\\src\\test\\resources\\Testdata10.xlsx");

		FileInputStream fis = new FileInputStream(excelFile);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalColumns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[totalRows-1][totalColumns];

		for (int i = 0; i < totalRows-1; i++) {
			for (int j = 0; j < totalColumns; j++) {
				
				DataFormatter df=new DataFormatter();
				data[i][j]=df.formatCellValue(sheet.getRow(i+1).getCell(j));
				
				//System.out.println(sheet.getRow(i+1).getCell(j).getStringCellValue());
			}
		}
		workbook.close();
		fis.close();
		
         return data;
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}

}
