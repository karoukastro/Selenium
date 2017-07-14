package com.incognito.sactests.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
	
	public NewUsersPage openUserPage(){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("adminTab")).click();
		driver.findElement(By.linkText("Users")).click();
		return new NewUsersPage(driver);
	}	
	
	public NewUsersPage openNewUserPage(){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("adminTab")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Create")).click();
		return new NewUsersPage(driver);
	}
	
	public UsersPage searchUser(String name){
		WebElement criteria = driver.findElement(By.id("UserHeaderSearch_searchCriteria"));
		criteria.sendKeys(name);
		criteria.submit();
		WebElement user = driver.findElement(By.linkText(name));
		user.click();
		return new UsersPage(driver);
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
	
	public void deleteUser(String name){
		UsersPage user = new UsersPage(driver);
		user.searchUser(name);
		driver.findElement(By.linkText("Delete"));
		
	/*	 String parentWindowHandle = driver.getWindowHandle(); // save the current window handle.
		  WebDriver popup = null;
		  
		  Iterator<String> windowIterator = driver.getWindowHandles();
		  while(windowIterator.hasNext()) { 
		    String windowHandle = windowIterator.next(); 
		    popup = driver.switchTo().window(windowHandle);
		    if (popup.getTitle().equals("Google") {
		      break;
		    }
		  }*/
		
	/*	
		//Guardando o windowHandle da janela inicial, porque vou precisar mudar pra ela no final do teste e, além disso, vou usar esse valor para descobrir o windowHandle da nova janela
		String windowHandleJanelaInicial = driver.getWindowHandle();
		
		//Clico no elemento que abre a nova janela
		
		WebElement elementoQueAbreNovaJanela = driver.findElement(By.linkText("Delete"));
		elementoQueAbreNovaJanela.click();

		//Qual é o windowHandle da nova janela? Não sei. Vamos pegar então todos os windowHandles e guardar numa lista. Se só temos a janela inicial e a nova abertas, essa lista vai ter apenas 2 elementos. Para descobrir o windowHandle da janela nova, basta percorrer a lista e achar o elemento que NÃO seja o windowHandle da janela inicial.
	
	//	List<String> windowHandles = (List<String>) driver.getWindowHandles();
	//	ArrayList windowHandles = new ArrayList<String> (driver.getWindowHandles());

		//Para cada windowHandle dentro da lista de windowHandles...
		for(String windowHandle : windowHandles) {
		    
		    //Se o windowHandle NÃO é igual ao windowHandle da janela inicial, eu sei que é o da nova janela, então mando o WebDriver mudar para ela
		    if( !windowHandle.equals(windowHandleJanelaInicial) ) {
		         driver.switchTo().window(windowHandle);
		         System.out.println("windowHandle");
		    }
		}
		
		//Agora estou na nova janela e posso interagir com os elementos dela
		driver.findElement(By.linkText("OK")).click()
		;
		//Fecho a janela atual (nova) e mudo para a janela inicial
		driver.close();

		driver.switchTo().window(windowHandleJanelaInicial);

	*/
		/*
		String janelaAtual = driver.getWindowHandle();
		Set<String> janelas = driver.getWindowHandles();

		for (String janela : janelas) {
		    driver.switchTo().window(janela);
		    System.out.println(janela);
		     //if(driver.getCurrentURL().equals("URL DO BROWSER")) {
		      //  break;
		    // }
		}
		
		*/
		//String nomeDaJanelaDeDestino = "Delete";
		//driver.switchTo().window("dialog-confirm");
		//driver.findElement(By.name("OK")).click();
		//driver.switchTo().window(")
		//driver.findElement(By.linkText("OK")).click();
		//Alert alert = driver.switchTo().alert();
		//alert.accept();
		
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
