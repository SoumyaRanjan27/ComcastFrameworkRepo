package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

public class ContactInfoPage {
	WebDriver driver;

	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headermsg;

	@FindBy(id="dtlview_Last Name")
	private WebElement actLastName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement actEndDate;
	
	@FindBy(xpath="//td[text()='Organization Name']/following-sibling::td/a")
	private WebElement actContactOrganization;


	public WebElement getActContactOrganization() {
		return actContactOrganization;
	}

	public WebElement getActStartDate() {
		return actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}

	public WebElement getHeadermsg() {
		return headermsg;
	}

	public WebElement getActLastName() {
		return actLastName;
	}
	

}
