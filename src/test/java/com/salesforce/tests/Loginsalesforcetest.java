package com.salesforce.tests;

import org.testng.annotations.Test;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.salesforce.pages.LoginPage;
import com.qa.base.BaseTest;
import com.testng.report.*;
import com.salesforce.pages.*;

   public class Loginsalesforcetest extends BaseTest {
	
	WebDriver driver;
	LoginPage loginpage;
	
	@BeforeMethod
	public void beforemethod() {
		
		driver=getdriver();
		launchapplication();
		loginpage=new LoginPage(driver);  //passing the driver to find element
		
		DOMConfigurator.configure("log4j.xml");
	}
	
	@Test(priority=3)
	public void loginapplicationwithoutpassword() {
		Log.startTestCase("Login");
		loginpage.enterusername("jincy@raju.com");
		loginpage.enterpassword("");
		loginpage.clickloginbutton();
		Log.info("Login failed");
		Log.endTestCase("Login");
	}
	@Test(priority=1)
	public void loginapplication() {
		Log.startTestCase("Login");
		loginpage.enterusername("jincy@raju.com");
		loginpage.enterpassword("Test123#");
		loginpage.clickloginbutton();
		//Assert.assertTrue(loginpage.checkloginsuccess());
		loginpage.checkloginsuccess();
		Log.info("Successfully Logged");
		Log.endTestCase("Login");
	}
	@Test(enabled=false)
	public void checkrememberme() {
		Log.startTestCase("Login");
		loginpage.enterusername("jincy@raju.com");
		loginpage.enterpassword("Test123#");
		loginpage.remembermecheckbox();
		loginpage.clickloginbutton();
		loginpage.usermenudropdown();
		loginpage.clicklogout();
		loginpage.comparestring();
		Log.info("Username field <username> is displayed");
		Log.endTestCase("Login");
	}
	@Test (enabled=false)
	public void forgotpassword() {
		Log.startTestCase("Login");
		loginpage.forgotpassword();
		loginpage.username("jincy@raju.com");
		loginpage.clickcontinuebutton();
		Log.info("An email sent to the emailaccount");
		Log.endTestCase("Login");
	}
	@Test(priority=2)
	public void invalidusernameandpassword() {
		Log.startTestCase("Login");
		loginpage.enterusername("jincycom");
		loginpage.enterpassword("Welcome12345");
		loginpage.clickloginbutton();
		loginpage.compareerrormessage();
		Log.info("Error message is displayed");
		Log.endTestCase("Login");
		
	}
	
	@AfterMethod 
	public void teardown(){
				
		close();
	}
	

}
