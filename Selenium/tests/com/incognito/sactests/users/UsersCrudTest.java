

package com.incognito.sactests.users;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.incognito.sactests.users.UsersPage;

public class UsersCrudTest {
	
	public WebDriver driver= null;
	public UsersPage user;
    String browser="chrome";
    String url="http://172.20.4.65:2800/sac/";
    String login = "Administrator";
    String password = "incognito";
    
    @Before
    public void LaunchBrowser() {

      if(browser.equalsIgnoreCase("mozilla")){
  		  System.setProperty("webdriver.gecko.driver","/Users/ccastro/Documents/WebDriver/SeleniumGecko/geckodriver");
  		  this.driver = new FirefoxDriver();
      }
      else if(browser.equalsIgnoreCase("chrome")){
    	  System.setProperty("webdriver.chrome.driver", "/Users/ccastro/Documents/WebDriver/ChromeDriver/chromedriver");
    	  this.driver= new ChromeDriver();   
      }
          driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
          driver.navigate().to(url);       
          this.user = new UsersPage(driver);
          user.logSAC(login, password);
  }
	
   @Test
   public void testCreateUser(){
	   
	   user.openNewUserPage().createUser("testcreate", "Test Full Name", "12345", "12345", "12345678", "test@test.com.br", "CSR ( Incognito )", "Description");
	   assertTrue(user.successMessage());
   }
    
   @Test
   public void testCreateUserMandatoryFields(){
	   
	   user.openNewUserPage().createUser("testmandatoryfields", "Test Full Name", "112345", "12345", "", "", "CSR ( Incognito )", "");
	   assertTrue(user.successMessage());
   }
   
   @Test
   public void testCreateUserOnlyLoginName(){
	   
	   //Only Login Name
	   user.openNewUserPage().createUser("testnotmandatoryfields", "", "", "", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
	   //Only Full Name
	   user.openNewUserPage().createUser("", "testnotmandatoryfields", "", "", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
	   //Only Password
	   user.openNewUserPage().createUser("", "", "testnotmandatoryfields", "", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
	   //Only Confirm Password
	   user.openNewUserPage().createUser("", "", "", "testnotmandatoryfields", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
	   
	   //Only Security Profile
	   user.openNewUserPage().createUser("", "", "", "", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
   }
   
   
   
   @Test //SAC-3446
   public void testCreateUserDiferentsPassword(){
	   
	   user.openNewUserPage().createUser("testpassword", "testpassword", "testpassword", "testconfirmpassword", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
   }
   
   
   @Test 
   public void testUpdateUserAllFields(){
	   
	   String login = "testupdateuser";
	   user.openNewUserPage().createUser(login, "testupdateuser", "password", "password", "12345678", "create@test.com", "Administrator ( Incognito )", "Create Test");
	   assertTrue(user.successMessage());
	   System.out.println("Created User");
	   user.openUserPage();
	   user.searchUser(login);
	   user.updateUser(login, "newtestupdateuser", "newpassword", "newpassword", "87654321", "update@test.com", "CSR ( Incognito )", "Update Test");
	   assertTrue(user.successMessage());
	   
   }
   
   @Test 
   public void testUpdateUserWithoutMandatoryFields(){
	   
	   user.openNewUserPage().createUser("testpassword", "testpassword", "testpassword", "testconfirmpassword", "", "", "Administrator ( Incognito )", "");
	   assertTrue(user.errorMessage());
	   user.leftWithoutSave();
	   
   }
   
   @Test 
   public void testDeleteUser(){
	  
	   String login = "testdeleteuser";
	   user.openNewUserPage().createUser(login, "Test Full Name", "12345", "12345", "12345678", "test@test.com.br", "CSR ( Incognito )", "Description");
	   assertTrue(user.successMessage());
	   user.openUserPage();
	   user.deleteUser(login);
	//   assertTrue(user.successMessage());
   }
   
   @After
	public void tearsDown(){
	 //  driver.findElement(By.linkText("Logout")).click();
	 //  driver.close();
	}
   
}

