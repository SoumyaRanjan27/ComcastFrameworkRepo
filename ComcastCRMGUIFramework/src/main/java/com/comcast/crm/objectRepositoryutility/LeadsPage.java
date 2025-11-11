package com.comcast.crm.objectRepositoryutility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeadsPage {
	
	@FindBy(xpath="//img[@title='Create Lead...']")
	private WebElement createLeadslink;

}
