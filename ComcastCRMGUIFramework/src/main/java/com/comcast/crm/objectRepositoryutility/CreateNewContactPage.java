package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {

	WebDriver driver;

	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "lastname")
	private WebElement lastnameEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement contactOrganization;
	
	@FindBy(id="search_txt")
	private WebElement orgSearchField;
	
	@FindBy(xpath ="//input[@type='button']")
	private WebElement orgSearchbtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDateEdt;
	
	@FindBy(name="support_end_date")
	private WebElement endDateEdt;

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	public WebElement getContactOrganization() {
		return contactOrganization;
	}

	public WebElement getOrgSearchField() {
		return orgSearchField;
	}

	public WebElement getOrgSearchbtn() {
		return orgSearchbtn;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}

	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public void createContact(String lastname) {
		lastnameEdt.sendKeys(lastname);
		savebtn.click();
	}

}
