package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerMsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement actorgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement actIndustryName;
	
	@FindBy(id="dtlview_Type")
	private WebElement actTypeName;
	
	@FindBy(id="dtlview_Phone")
	private WebElement actPhoneNum;
	
	
	public WebElement getActPhoneNum() {
		return actPhoneNum;
	}

	public WebElement getActorgName() {
		return actorgName;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getActIndustryName() {
		return actIndustryName;
	}

	public WebElement getActTypeName() {
		return actTypeName;
	}
	
	
	
	
}
