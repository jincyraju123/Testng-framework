 package com.training.testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginsalesforceCrossBrowser {
	
	WebDriver driver=null;
	
	@BeforeMethod
	@Parameters({"browser"})
	
	public void getDriver(String browser){
		
		if(browser.equalsIgnoreCase("Chrome")) {
		   WebDriverManager.chromedriver().setup();
		   driver = new ChromeDriver();
		 
		}else if(browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}

		driver.get("https://login.salesforce.com/");
		}
		
	@Test
	@Parameters({"username","password"})
	public void loginApplication(String username,String password) {
		
		WebElement usernamefield = driver.findElement(By.id("username"));
		usernamefield.sendKeys(username);

		WebElement passwordfield = driver.findElement(By.id("password"));
		passwordfield.sendKeys(password);

		WebElement loginbutton = driver.findElement(By.id("Login"));
		loginbutton.click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close(); 
		
	}

}
