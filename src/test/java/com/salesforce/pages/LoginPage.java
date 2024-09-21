package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.qa.base.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		
		super(driver);
		
	}
	@FindBy(id="username")
	WebElement username;
	@FindBy(id="password")
	WebElement password;
	@FindBy(id="Login")
	WebElement login;
	@FindBy(xpath="//img[@id='phHeaderLogoImage']")
	WebElement logo;
	@FindBy(xpath="//input[@class='r4 fl mr8']")
	WebElement rememberme;
	@FindBy(id="userNavLabel")
	WebElement usernamedropdown;
	@FindBy(xpath="//a[@title='Logout']")
	WebElement logout;
	@FindBy(id="idcard-identity")
	WebElement Actualusername;
	
	@FindBy(xpath="//a[text()='Forgot Your Password?']")
	WebElement forgotpassword;
	@FindBy(id="un")
	WebElement username1;
	@FindBy(id="continue")
	WebElement continuebutton;
	
	@FindBy(id="error")
	WebElement Actualerror;
	
	public void enterusername(String strusername) {
		
		username.sendKeys(strusername);
		
	}

	public void enterpassword(String strpassword) {
		
		password.sendKeys(strpassword);
		
	}
	public void clickloginbutton(){
		login.click();
	}
	public boolean checkloginsuccess() {
		waitforelement(logo);
		return logo.isDisplayed();
	}
	
	public void remembermecheckbox() {
		rememberme.click();
		
	}
	
	public void usermenudropdown() {
		usernamedropdown.click();
	}
	
	public void clicklogout() {
		logout.click();
		
	}
	public void comparestring() {
		waitforelement(Actualusername);
		comparetext(Actualusername.getText(), "jincy@raju.com");
	}
	public void compareerrormessage() {
		comparetext(Actualerror.getText(), "check your username and password. If you still can't log in, contact your Salesforce administrator.");
	}
 
	public void forgotpassword() {
		forgotpassword.click();
	}

	public void username(String strusername) {
		username1.sendKeys(strusername);
	}
	

	public void clickcontinuebutton() {
		continuebutton.click();
	}
	
	
	
	}
