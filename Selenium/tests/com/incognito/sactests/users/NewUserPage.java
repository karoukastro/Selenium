package com.incognito.sactests.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NewUserPage {
	
	private final WebDriver driver;
	
	public NewUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void createUser(String loginName, String fullName, String password, String confirm, String phone, String email, String profile, String description){
		
		WebElement formLoginName = driver.findElement(By.name("loginName"));
		WebElement formFullName = driver.findElement(By.name("name"));
		WebElement formPassword = driver.findElement(By.name("password"));
		WebElement formConfirmPassword = driver.findElement(By.name("confirmPassword"));
		WebElement formPhoneNunber = driver.findElement(By.name("phoneNumber"));
		WebElement formEmailAddress = driver.findElement(By.name("emailAddress"));
		Select cbSecurityProfile = new Select(driver.findElement(By.name("securityProfile")));
		WebElement formDescription = driver.findElement(By.name("description"));
		
		formLoginName.sendKeys(loginName);
		formFullName.sendKeys(fullName);
		formPassword.sendKeys(password);
		formConfirmPassword.sendKeys(confirm);
		formPhoneNunber.sendKeys(phone);
		formEmailAddress.sendKeys(email);
		cbSecurityProfile.selectByVisibleText(profile);
		formDescription.sendKeys(description);
		
		formDescription.submit();
		//driver.findElement(By.linkText("Save"));
	
	}

	
}
