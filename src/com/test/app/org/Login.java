package com.test.app.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class Login extends Execution_main{
	
	public static WebDriver Driver;
	
	public WebDriver Login(String FilePath,String testcasename, String username,String password,String url)
	{
		System.setProperty("webdriver.chrome.driver", FilePath+"\\chromedriver\\chromedriver.exe");
		WebDriver driver=Login.Driver;
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get(url);		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		
		if(testcasename.equalsIgnoreCase("LOGIN_APPL"))
		{
			test=extent.createTest("LOGIN APPL custom report");
		}
		
		//username
		WebElement username_l=driver.findElement(By.id("user_email"));
		username_l.sendKeys(username);
		
		//password
		WebElement pass=driver.findElement(By.id("user_password"));
		pass.sendKeys(password);
		
		//login button
		driver.findElement(By.xpath("//*[@id='new_user']/div[3]/input")).click();
		
		
		//Validation
		if (testcasename.equalsIgnoreCase("LOGIN_APPL"))
		{
			String actualUrl= driver.getCurrentUrl();
			String expectedUrl="http://damp-sands-8561.herokuapp.com/";
		
			 if(actualUrl.equalsIgnoreCase(expectedUrl)) {
				 System.out.println("Login Test passed"); 
				 test.pass("Login test passed");
				 
				 } 
			 else {
				 System.out.println("Test failed"); 
				 test.fail("Login test failed");
			 }
		}
			return driver;
	}
	public WebDriver getDriver()
	{
		return Login.Driver;
	}
	
	public void setDriver(WebDriver driver)
	{
		Login.Driver=driver;
	}
 
}
