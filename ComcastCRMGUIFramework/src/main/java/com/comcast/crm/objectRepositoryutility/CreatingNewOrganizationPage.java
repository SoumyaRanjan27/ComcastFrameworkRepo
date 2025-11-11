package com.comcast.crm.objectRepositoryutility;

import java.nio.channels.SelectableChannel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {

	WebDriver driver;

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "accountname")
	private WebElement orgNameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name = "industry")
	private WebElement industryEdt;

	@FindBy(name = "accounttype")
	private WebElement typeEdt;
	
	@FindBy(id="phone")
	private WebElement phoneLink;
	
	
	
	public WebElement getPhoneLink() {
		return phoneLink;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgname) {
		orgNameEdt.sendKeys(orgname);
		saveBtn.click();
	}

	public void createOrg(String orgname, String industry,String type ) {
		orgNameEdt.sendKeys(orgname);
		Select select1= new Select(industryEdt);
		select1.selectByVisibleText(industry);
		Select select2=new Select(typeEdt);
		select2.selectByVisibleText(type);
		saveBtn.click();
	}
	
	public void createOrg(String organization,String phoneNum) {
		orgNameEdt.sendKeys(organization);
		phoneLink.sendKeys(phoneNum);
		
	}

}
