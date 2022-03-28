package com.test.app.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Read_Test {
	
	String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Execution completed- Report Generation");
		extent.flush();
		
	}
	
	@BeforeTest
	@DataProvider
	public static Object[][] getTestData() throws IOException{
		String FilePath=System.getProperty("user.dir");
		String inputFile=FilePath+File.separator+"Test_Cases.xls";
		
		File inputWorkFile=new File(inputFile);
		FileInputStream inputStream=new FileInputStream(inputWorkFile);
		HSSFWorkbook wb=new HSSFWorkbook(inputStream);
		HSSFSheet sheet=wb.getSheet("Test_Case_details");
		HSSFRow row=sheet.getRow(1);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		int col_num=row.getLastCellNum();

		Object Data[][]=new Object[rowCount-1][col_num];
		
		//row loop
		for(int i=0;i<rowCount-1;i++)
		{
			HSSFRow roww=sheet.getRow(i+1);
			
			//col loop
			for(int j=0;j<col_num;j++) {
				if(roww==null) {
					Data[i][j]="";
				}
				else {
					HSSFCell cell=roww.getCell((short) j);
					String Value=cell.getStringCellValue();
					Data[i][j]=Value;
				}
			}
		}
		return Data;
	}
	
		

}
