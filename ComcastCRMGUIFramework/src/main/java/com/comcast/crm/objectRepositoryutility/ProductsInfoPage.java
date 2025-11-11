package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsInfoPage {
	
	WebDriver driver;

	public ProductsInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id= "dtlview_Product Name")
	private WebElement actProduct;
	
	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Sales Start Date")
	private WebElement actSalesStartDate;
	
	@FindBy(id="dtlview_Sales End Date")
	private WebElement actSalesEndDate;


	public WebElement getActSalesStartDate() {
		return actSalesStartDate;
	}

	public WebElement getActSalesEndDate() {
		return actSalesEndDate;
	}

	public WebElement getActProduct() {
		return actProduct;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	

	

	

}
