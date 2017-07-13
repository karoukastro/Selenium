package com.incognito.sactests.users;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.incognito.sactests.users.NewUsersPage;

public class UsersPage {

	private final WebDriver driver;
	
	public UsersPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void logSAC(String user, String upassword){
		
		WebElement username = driver.findElement(By.id("Login_username"));
        username.sendKeys(user);
        WebElement password = driver.findElement(By.id("Login_password"));
        password.sendKeys(upassword);
        password.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public NewUsersPage openNewUserPage(){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("adminTab")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Create")).click();
		return new NewUsersPage(driver);
	}
	
	public UsersPage searchUser(String name){
		WebElement criteria = driver.findElement(By.name("criteria"));
		criteria.sendKeys(name);
		criteria.submit();
		return new UsersPage(driver);
	}
	
/*	public void updateUser(String newLogin, String fullName, String password, String confirm, String phone, String email, String profile, String description){
		WebElement formLoginName = driver.findElement(By.name("newLogin"));
		WebElement formFullName = driver.findElement(By.name("name"));
		WebElement formPassword = driver.findElement(By.name("password"));
		WebElement formConfirmPassword = driver.findElement(By.name("confirmPassword"));
		WebElement formPhoneNunber = driver.findElement(By.name("phoneNumber"));
		WebElement formEmailAddress = driver.findElement(By.name("emailAddress"));
		Select cbSecurityProfile = new Select(driver.findElement(By.name("securityProfile")));
		WebElement formDescription = driver.findElement(By.name("description"));
		
		formLoginName.sendKeys(newLogin);
		formFullName.sendKeys(fullName);
		formPassword.sendKeys(password);
		formConfirmPassword.sendKeys(confirm);
		formPhoneNunber.sendKeys(phone);
		formEmailAddress.sendKeys(email);
		cbSecurityProfile.selectByVisibleText(profile);
		formDescription.sendKeys(description);
		
		formDescription.submit();
	}*/
	
	public void leftWithoutSave(){
		driver.findElement(By.linkText("Users")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public boolean successMessage(){ // String loginName, String fullName, String phone, String email, String profile
		return driver.getPageSource().contains("Your changes were successfully saved.");
	}

	public boolean errorMessage() {
		return driver.getPageSource().contains("Error! Save failed: ");
	}
	
}
