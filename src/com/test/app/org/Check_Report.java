package com.test.app.org;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

public class Check_Report extends Execution_main {
	
public static WebDriver driver;
	
	public Check_Report(WebDriver pd)
	{
		driver=pd;
	}
	
	public void Check_Report() {
		//
		// click on Reports
		WebElement Reports = driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/ul/li[3]/a"));
		Reports.click();
		test.log(Status.INFO, "Checking entry for report");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement Daily_link=driver.findElement(By.linkText("Daily"));
		Daily_link.click();
		test.log(Status.INFO, "Checking entry for daily report");
		
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement txt=driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[1]/div/div[1]/h3"));
		String text=txt.getText();
		LocalDate date=java.time.LocalDate.now();
		if (text.contains(date.toString()))
		{
			System.out.println("Daily Report being displayed on current date");
			test.pass("Daily Report being displayed on current date");
		}
		else
		{
			System.out.println("Daily Report Date not same as current date");
			test.fail("Daily Report Date not same as current date");
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement Monthly_link=driver.findElement(By.linkText("Monthly"));
		Monthly_link.click();
		test.log(Status.INFO, "Checking entry for monthly report");
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement table_text=driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[1]/div/div[2]/table/tbody/tr[1]/td[1]"));
		String txxt=table_text.getText(); 
		Month currentMonth=date.getMonth();
		int currentYear=date.getYear();
		String m_y=String.valueOf(currentYear)+"-"+(currentMonth.toString()).substring(0, 3);
		if (m_y.equalsIgnoreCase(txxt))
		{
			System.out.println("Monthly Report being displayed is of current month");
			test.pass("Monthly Report being displayed is of current month: "+txxt);
		}
		else
		{
			System.out.println("Monthly Report displayed not of current month as expected");
			test.fail("Monthly Report displayed not of current month as expected: "+txxt);
		}
		
	
		String min=(driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[1]/div/div[2]/table/thead/tr/th[4]"))).getText();
		String max=(driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[1]/div/div[2]/table/thead/tr/th[5]"))).getText();
		String avg=(driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[1]/div/div[2]/table/thead/tr/th[6]"))).getText();
		
		if(min.contains("Min")&&max.contains("Max")&&avg.contains("Avg"))
		{
			System.out.println("Min Max and Avg values present");
			test.pass("Min Max and Avg values present");
		}
		else
		{
			System.out.println("Min Max and Avg values not present");
			test.fail("Min Max and Avg values not present");
		}

	}


}
