package com.test.app.org;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class Read_Datasheet {

	public String Read_Datasheet(String FilePath,String wrkbknm,String sheetname,int iteration)
	{
		String datalst="";
		String tmp_val="";
		
		if(!(wrkbknm.equalsIgnoreCase("NA"))){
		
		try
		{
			String inputFileName=FilePath+File.separator+"\\Datasheet\\"+wrkbknm.trim()+".xls";
			File inputWorkFile=new File(inputFileName);
			FileInputStream inputStream=new FileInputStream(inputWorkFile);
			HSSFWorkbook wb=new HSSFWorkbook(inputStream);
			HSSFSheet sheet=wb.getSheet(sheetname.trim());
			HSSFRow row=sheet.getRow(0);
			int col_val=row.getLastCellNum();
			row=sheet.getRow(iteration);
			
			for(int j=1;j<=col_val-1;j++)
			{
				tmp_val=tmp_val+ getCellValueAsString(row.getCell((short) j))+"|";
			}
			
			datalst=tmp_val;
			tmp_val="";
			
			inputStream.close();
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		else
		{
			datalst="NA";
		}
		return datalst;
	}
	
	public static String getCellValueAsString(HSSFCell hssfCell) {
		String strCellValue=null;
		if(hssfCell!=null) {
			switch(hssfCell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					strCellValue=hssfCell.toString();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					Double value=hssfCell.getNumericCellValue();
					Long longval=value.longValue();
					strCellValue=new String(longval.toString());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					strCellValue=new String(new Boolean(hssfCell.getBooleanCellValue()).toString());
					break;
				case Cell.CELL_TYPE_BLANK:
					strCellValue="";
					break;
			}
		}
		return strCellValue;
		
	}

}
