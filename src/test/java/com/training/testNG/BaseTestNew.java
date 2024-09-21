package com.training.testNG;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestNew {

	WebDriver driver;
	Properties prop;

	public void initialiseBrowser(Properties prop) {
		
		String browserName=prop.getProperty("browser");

		switch (browserName.toLowerCase()) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("Browser Name is invalid");
			break;
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

	}

	public Properties initproperties() {

		try {
			FileInputStream fis = new FileInputStream(".\\src\\main\\resources\\config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
}
