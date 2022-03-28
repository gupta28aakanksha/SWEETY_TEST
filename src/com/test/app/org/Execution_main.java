package com.test.app.org;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Execution_main {

	//Read_Executionexcel test_case=new Read_Executionexcel();
	public String Login_Url;
	public String Username;
	public String Password;
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Execution begins");
		String FilePath=System.getProperty("user.dir");
		System.clearProperty("hudson.model.DirectoryBrowserSupport.CSP");
		System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox allow-scripts; default-src 'self'; script-src * 'unsafe-eval'; img-src *; style-src * 'unsafe-inline'; font-src *");
		htmlreporter= new ExtentHtmlReporter(FilePath+"\\Results\\TestReport_"+fileSuffix+".html");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("OS", "Window");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Test Automation");
		htmlreporter.config().setDocumentTitle("Test Report");
		htmlreporter.config().setReportName("Report");
	}
	
	@Test
	(dataProviderClass=Read_Test.class, dataProvider="getTestData")
	public void TestMethod(String TC_ID_val,String TC_name_val,	String TC_desc_val,	String TC_exec_val,	String Login_user,	String Login_pwd,String Login_url,String wrkbkname, String sheetname, String iteration1) throws Exception
	{		
			  String FilePath=System.getProperty("user.dir");
			  Read_Datasheet read_datasheet=new Read_Datasheet();
			  String dtexcelVal;
			  int iteration=Integer.parseInt(iteration1);
	
			  if(TC_exec_val.equalsIgnoreCase("Y"))
			  {
						Login lgn=new Login();
						WebDriver driver=lgn.Login(FilePath, TC_name_val, Login_user, Login_pwd, Login_url);
				//		WebDriver driver=lgn.getDriver();
						
					try
					{
								//TC_2			
						if(TC_name_val.equalsIgnoreCase("ADD_LEVEL_DIFF_DATETIME"))
						{
							test=extent.createTest("ADD_LEVEL_DIFF_DATETIME Report Creation");
							System.out.println("Execution begins for ADD_LEVEL_DIFF_DATETIME");
							String result1="false";
							dtexcelVal=read_datasheet.Read_Datasheet(FilePath, wrkbkname, sheetname, iteration);
							
							if(dtexcelVal.isEmpty() || dtexcelVal==null || dtexcelVal=="")
							{
								System.out.println("Empty datasheet");
							}
							else
							{
								Add_Level addlevel=new Add_Level(driver);
								result1=addlevel.Add_Level(dtexcelVal,"ADD_LEVEL_DIFF_DATETIME",iteration);
								if(result1.contains("true"))
								{
									test.pass("ADD_LEVEL_DIFF_DATETIME testcase passed");
									System.out.println("ADD_LEVEL_DIFF_DATETIME testcase passed");
								}
								else
								{
									test.fail("ADD_LEVEL_DIFF_DATETIME testcase failed");
									System.out.println("ADD_LEVEL_DIFF_DATETIME testcase failed");
								}
							}
						}
						//TC_3
						else if(TC_name_val.equalsIgnoreCase("ADD_LEVEL_CURRENT_DATETIME"))
						{
							test=extent.createTest("ADD_LEVEL_CURRENT_DATETIME Report Creation");
							System.out.println("Execution begins for ADD_LEVEL_CURRENT_DATETIME");
							String result1="false";
							dtexcelVal=read_datasheet.Read_Datasheet(FilePath, wrkbkname, sheetname, iteration);
							
							if(dtexcelVal.isEmpty() || dtexcelVal==null || dtexcelVal=="")
							{
								System.out.println("Empty datasheet");
							}
							else
							{
								Add_Level addlevel=new Add_Level(driver);
								result1=addlevel.Add_Level(dtexcelVal,"ADD_LEVEL_CURRENT_DATETIME",iteration);
								if(result1.contains("true"))
								{
									test.pass("ADD_LEVEL_CURRENT_DATETIME testcase passed");
									System.out.println("ADD_LEVEL_CURRENT_DATETIME testcase passed");
								}
								else
								{
									test.fail("ADD_LEVEL_CURRENT_DATETIME testcase failed");
									System.out.println("ADD_LEVEL_CURRENT_DATETIME testcase failed");
								}
							}
						}
						
						//TC_4
						else if(TC_name_val.equalsIgnoreCase("CHECK_REPORT"))
						{
							test=extent.createTest("CHECK_REPORT Report Creation");
							System.out.println("Execution begins for CHECK_REPORT");
							String result1="false";
							dtexcelVal=read_datasheet.Read_Datasheet(FilePath, wrkbkname, sheetname, iteration);
							
							if(dtexcelVal.isEmpty() || dtexcelVal==null || dtexcelVal=="")
							{
								System.out.println("Empty datasheet");
							}
							else
							{
								Check_Report checkreport=new Check_Report(driver);
								checkreport.Check_Report();
								
							}
						}
						
						//TC_5
						else if(TC_name_val.equalsIgnoreCase("CHECK_ENTRY_4ROWS"))
						{
							test=extent.createTest("CHECK_ENTRY_4ROWS Report Creation");
							System.out.println("Execution begins for CHECK_ENTRY_4ROWS");
							String result1="false";
							dtexcelVal=read_datasheet.Read_Datasheet(FilePath, wrkbkname, sheetname, iteration);
							
							if(dtexcelVal.isEmpty() || dtexcelVal==null || dtexcelVal=="")
							{
								System.out.println("Empty datasheet");
							}
							else
							{
								Check_level_4 cl=new Check_level_4(driver);
								result1=cl.Check_level_4(dtexcelVal,"CHECK_ENTRY_4ROWS",iteration);
								if(result1.contains("true"))
								{
									test.pass("CHECK_ENTRY_4ROWS testcase passed");
									System.out.println("CHECK_ENTRY_4ROWS testcase passed");
								}
								else
								{
									test.fail("CHECK_ENTRY_4ROWS testcase failed");
									System.out.println("CHECK_ENTRY_4ROWS testcase failed");
								}
							}
						}
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			  
						driver.close();
			  }
	}
	
	@AfterSuite
	public void afterSuite() throws IOException
	{
		System.out.println("Report generation after execution");
		extent.flush();
	}
	
	
}
