package com.test.app.org;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

public class Add_Level extends Execution_main{

	public static WebDriver driver;
	
	public Add_Level(WebDriver pd)
	{
		driver=pd;
	}
	
	public String Add_Level(String input_param, String test_case_name, int iteration) {

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
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// add new button
		driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[2]/div[2]/a")).click();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		if(test_case_name.equalsIgnoreCase("ADD_LEVEL_DIFF_DATETIME"))
		{
			String year_input=arrSplit[1];
			String month_input=arrSplit[2];
			String date_input=arrSplit[3];
			String hrs_input=arrSplit[4];
			String min_input=arrSplit[5];
		
			// select year
			Select year = new Select(driver.findElement(By.name("entry[recorded_at(1i)]")));
			year.selectByVisibleText(year_input);
	
			// select month
			Select month = new Select(driver.findElement(By.name("entry[recorded_at(2i)]")));
			month.selectByVisibleText(month_input);
	
			// select date
			Select date = new Select(driver.findElement(By.name("entry[recorded_at(3i)]")));
			date.selectByVisibleText(date_input);
			
			//select hrs
			Select hrs = new Select(driver.findElement(By.name("entry[recorded_at(4i)]")));
			date.selectByVisibleText(hrs_input);
			
			//select min
			Select min = new Select(driver.findElement(By.name("entry[recorded_at(5i)]")));
			date.selectByVisibleText(min_input);

		}
		// input level
		WebElement level_text = driver.findElement(By.id("entry_level"));
		level_text.sendKeys(level_input);
		test.log(Status.INFO, "Level entered: "+level_input);

		// click on submit
		driver.findElement(By.xpath("//*[@id=\"new_entry\"]/input[3]")).click();

		WebElement alert=driver.findElement(By.xpath("//*[@id=\"page-content-wrapper\"]/div/div[1]"));
		String alert_text=alert.getText();

		if(test_case_name.equalsIgnoreCase("CHECK_ENTRY_4ROWS")&&iteration==4)
		{
			if (alert_text.equalsIgnoreCase("Maximum entries reached for this date."))
			{
				System.out.println("Maximum Entry check of 4 for a day passed successfully");
				test.pass("Alert text matched. Maximum Entry check of 4 for a day passed successfully.");
				result="true";
			}
			else 
			{
				System.out.println("Maximum Entry check of 4 for a day failed");
				test.fail("Maximum Entry check of 4 for a day passed successfully");
			}
		
		}
		else {
		if (alert_text.equalsIgnoreCase("Entry was successfully created."))
		{
			System.out.println("Entry created successfully");
			test.pass("Alert text matched. Level Entry created successfully.");
			result="true";
		}
		else 
		{
			System.out.println("Entry creation failed");
			test.fail("Entry creation failed");
		}
		}
		
			
		
		
		/*// check if entry is created in table
		WebElement table = driver
				.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div[1]/div/div[2]/table"));

		List<WebElement> table_col = table.findElements(By.tagName("th"));
		System.out.println("Total columns: " + table_col.size());

		List<WebElement> table_row = table
				.findElements(By.xpath("//*[@id='page-content-wrapper']/div/div[2]/div[1]/div/div[2]/table/tbody/tr"));
		System.out.println("Total rows: " + table_row.size());

		for (int i = 0; i < table_row.size(); i++) {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			WebElement level_cell = driver
					.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[3]/div[1]/div/div[2]/table/tbody/tr["
							+ (i + 1) + "]/td[1]"));
			level_cell.click();
			String level_read = level_cell.getText();

			String[] level_arr = level_read.split("// ");
			int level_r = Integer.parseInt(level_arr[0]);

			
			 if(level_r==3) {
			 System.out.println(" Level entry created successfully."); WebElement
			 date_cell=driver.findElement(By.xpath("//*[@id='page-content-wrapper']/div/div[3]/div[1]/div/div[2]/table/tbody/tr["+(i+1)+"]/th[1]"));
			 date_cell.click(); 
			 String date_read=date_cell.getText();

			String[] date_arr = date_read.split("//@");
			String date_read1  = date_arr[0].trim();
			System.out.println(date_read1);
	//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			
			} 
			else { System.out.println(" Level entry creation failed."); }
			  
			 

		}
*/
		return result;
	}

}
