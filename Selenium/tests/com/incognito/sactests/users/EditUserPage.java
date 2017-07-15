package com.incognito.sactests.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EditUserPage {

private final WebDriver driver;
	
	public EditUserPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void updateUser(String newLogin, String fullName, String password, String confirm, String phone, String email, String profile, String description){
		
		WebElement formLoginName = driver.findElement(By.name("loginName"));
		WebElement formFullName = driver.findElement(By.name("name"));
		WebElement formPhoneNunber = driver.findElement(By.name("phoneNumber"));
		WebElement formEmailAddress = driver.findElement(By.name("emailAddress"));
		Select cbSecurityProfile = new Select(driver.findElement(By.name("securityProfile")));
		WebElement formDescription = driver.findElement(By.name("description"));
		
		formLoginName.clear();
		formLoginName.sendKeys(newLogin);
		formFullName.clear();
		formFullName.sendKeys(fullName);
		formPhoneNunber.clear();
		formPhoneNunber.sendKeys(phone);
		formEmailAddress.clear();
		formEmailAddress.sendKeys(email);
		cbSecurityProfile.selectByVisibleText(profile);
		formDescription.clear();
		formDescription.sendKeys(description);
		
		formDescription.submit();
		//driver.findElement(By.linkText("Save"));
	}
	
}
