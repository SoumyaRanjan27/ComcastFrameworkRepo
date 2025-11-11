package com.comcast.crm.productstest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelFileUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectRepositoryutility.CreatingNewProductsPage;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.ProductsInfoPage;
import com.comcast.crm.objectRepositoryutility.ProductsPage;

public class CreateProductWithSalesStartAndEndDate extends BaseClass {

	@Test
	public void createProductWithSalesStartAndEndDate() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		ProductsPage prp = new ProductsPage(driver);
		prp.getCreateProductsLink().click();

		// to create random num
		int ranNum = jutil.getRandomNumber();

		// to read data Excefile
		String productname = eutil.getDataFromExcel("Products", 4, 2) + ranNum;

		CreatingNewProductsPage cpp = new CreatingNewProductsPage(driver);
		cpp.getProductEdt().sendKeys(productname);
		// to create date
		String fromDate = jutil.getSystemDateYYYYDDMM();
		String endDate = jutil.getRequiredDateYYYYDDMM(30);

		cpp.getSalesstartdateEdt().sendKeys(fromDate);
		cpp.getSalesEnddateEdt().sendKeys(endDate);
		cpp.getSaveBtn().click();

		// to verify header
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String headerinfo = pip.getHeaderMsg().getText();
		boolean prodStatus = headerinfo.contains(productname);
		Assert.assertTrue(prodStatus);
		Reporter.log("Products Header verified Successfully", true);

		// to verify date

		String actFromDate = pip.getActSalesStartDate().getText();
		Assert.assertEquals(actFromDate, fromDate);
		Reporter.log("ActFromDate verified Successfully", true);

		String actEndDate = pip.getActSalesEndDate().getText();
		Assert.assertEquals(actEndDate, endDate);
		Reporter.log("ActEndDate verified Successfully", true);
	}
}
