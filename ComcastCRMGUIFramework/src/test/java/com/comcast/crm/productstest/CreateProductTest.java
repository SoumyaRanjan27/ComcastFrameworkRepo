package com.comcast.crm.productstest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.comcast.crm.objectRepositoryutility.CreatingNewProductsPage;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.ProductsInfoPage;
import com.comcast.crm.objectRepositoryutility.ProductsPage;

public class CreateProductTest extends com.comcast.crm.baseTest.BaseClass {

	@Test
	public void createProductTest() throws IOException {

		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();

		ProductsPage prp = new ProductsPage(driver);
		prp.getCreateProductsLink().click();

		// to read data from excel

		String product = eutil.getDataFromExcel("Products", 1, 2);

		CreatingNewProductsPage cpp = new CreatingNewProductsPage(driver);
		cpp.createProduct(product);

		// to verify Products header text
		ProductsInfoPage pip = new ProductsInfoPage(driver);
		String headerInfo = pip.getHeaderMsg().getText();
		boolean prodStatus = headerInfo.contains(product);
		Assert.assertTrue(prodStatus);
		Reporter.log("Products Header verified Successfully",true);

		// to verify product
		String actProduct = pip.getActProduct().getText();
		Assert.assertEquals(actProduct, product);
		Reporter.log("ActProduct verified Successfully",true);

	}

}
