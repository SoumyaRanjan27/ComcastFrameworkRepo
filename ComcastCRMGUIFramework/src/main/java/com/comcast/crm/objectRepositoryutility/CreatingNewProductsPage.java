package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewProductsPage {
	WebDriver driver;

	public CreatingNewProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
  
	
	@FindBy(name = "productname")
	private WebElement productEdt;
	
	@FindBy(name = "sales_start_date")
	private WebElement salesstartdateEdt;
	
	@FindBy(name = "sales_end_date")
	private WebElement salesEnddateEdt;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getSalesstartdateEdt() {
		return salesstartdateEdt;
	}

	public WebElement getSalesEnddateEdt() {
		return salesEnddateEdt;
	}

	public WebElement getProductEdt() {
		return productEdt;
	}
	
	public void createProduct(String product) {
		productEdt.sendKeys(product);
		saveBtn.click();
	}

}
