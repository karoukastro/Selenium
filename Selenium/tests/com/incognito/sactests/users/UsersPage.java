package com.incognito.sactests.users;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	public UsersPage openUserPage(String Name){
		//WebElement username = driver.findElement(By.name("criteria")).click();
		
		return new UsersPage(driver);
	}
	
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
