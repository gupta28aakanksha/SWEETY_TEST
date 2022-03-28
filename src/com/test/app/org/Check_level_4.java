package com.test.app.org;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Check_level_4 extends Execution_main {
	
public static WebDriver driver;
	
	public Check_level_4(WebDriver pd)
	{
		driver=pd;
	}
	
	public String Check_level_4(String input_param,String testcasename, int iteration)
	{
		String result="false";
		String arrSplit[]=input_param.split("\\|");
		String level_input=arrSplit[0];
				
		
		if(Integer.parseInt(level_input)>9||Integer.parseInt(level_input)<0)
		{
			System.out.println("Level should be in single digit integer: "+level_input);
			test.fail("Level should be in single digit integer: "+level_input);
			return "false";
		}
		// click  on Levels
		WebElement Levels = driver.findElement(By.xpath("/html/body/div/div[1]/ul/li[2]/a"));
		Levels.click();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//fetch current date
		LocalDate date=java.time.LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/uu");
		String currentDate=date.format(formatter);
		//formatter.format(date);
	//	String currentDate=date.toString();
		////*[@id="page-content-wrapper"]/div/div[2]/div[1]/div/div[2]/table/thead/tr/th[1]
		//fetch current date entries count
		WebElement table = driver
				.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div[1]/div/div[2]/table/thead"));

		List<WebElement> table_col = table.findElements(By.tagName("th"));
		System.out.println("Total columns: " + table_col.size());

		List<WebElement> table_row = table
				.findElements(By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div[1]/div/div[2]/table/tbody/tr"));
		System.out.println("Total rows: " + table_row.size());
		int count=0;
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < table_row.size(); i++) {
		
			WebElement date_cell = driver
					.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div[1]/div/div[2]/table/tbody/tr["
							+ (i + 1) + "]/th"));
			String date_s=date_cell.getText();
			String arr_date[]=date_s.split("\\@");
			String curr_dt=arr_date[0].trim();
			
			if(curr_dt.equalsIgnoreCase(currentDate))
			{
				count++;
			}

			
		}
		
		for (int k=count;k<5;k++)
		{
			Add_Level add=new Add_Level(driver);
			result=add.Add_Level(input_param, "CHECK_ENTRY_4ROWS", k);
		}
		

		

		return result;
	}

}
