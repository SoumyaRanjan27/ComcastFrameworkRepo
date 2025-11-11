 package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	
	public String getDataFromExcel(String sheetName,int rowNum,int celNum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(celNum).toString();
        wb.close();
		return data;
	}
	
	public int getRowCount(String sheetName) throws Throwable, IOException {
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		int rowCount=wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowCount;
	}
	
	public void setDataToExcel(String SheetName, int rowNum,int cellNum,String data) throws Throwable {
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
        wb.getSheet(SheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
         
        FileOutputStream fos=new FileOutputStream("./testData/testScriptData.xlsx");
        wb.write(fos);
        wb.close();
        
	}

}