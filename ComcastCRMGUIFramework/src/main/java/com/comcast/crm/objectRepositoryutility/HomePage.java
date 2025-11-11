package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver=null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Leads")
	private WebElement leads_Link;

	public WebElement getLeads_Link() {
		return leads_Link;
	}
	
	
	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;
	
	
	@FindBy(linkText = "More")
	private WebElement moreLink;
	
	@FindBy(linkText = "Products")
	private WebElement productslink;
	
	@FindBy(name="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;

	

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}
	
	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	public WebElement getProductsLink() {
		return productslink;
	}

	
	public void navigateToCampaignLink() {
		Actions action=new Actions(driver);
		action.moveToElement(moreLink).perform();
		campaignLink.click();
	}
	
	public void logOut() {
		Actions action=new Actions(driver);
		action.moveToElement(adminimg).perform();
		signOutLink.click();
		
	}

	
	

}
