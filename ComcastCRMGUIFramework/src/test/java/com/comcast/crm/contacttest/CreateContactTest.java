package com.comcast.crm.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectRepositoryutility.ContactInfoPage;
import com.comcast.crm.objectRepositoryutility.ContactPage;
import com.comcast.crm.objectRepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectRepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryutility.OrganizationsPage;

@Listeners(com.comcast.crm.Listenerutility.ListnerImpClass.class)
public class CreateContactTest extends BaseClass {

	@Test(groups = "SmokeTest")
	public void createContactTest() throws IOException {

		// to click on contacts
		UtilityClassObject.getTest().log(Status.INFO, "navigate to contact page");
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();


		// to click on Create conatct button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create contact page");
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLink().click();

		// to create random num
		int ranNum = jutil.getRandomNumber();
		

		// to read data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "To read data from excel");
		String lastname = eutil.getDataFromExcel("Contact", 1, 2) + ranNum;
		
		UtilityClassObject.getTest().log(Status.INFO, "Create A new Contact");
		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.createContact(lastname);

		// verifying header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerinfo = oip.getHeaderMsg().getText();
		boolean status = headerinfo.contains(lastname);
		Assert.assertEquals(status, true);
		Reporter.log("Header verify Successfully", true);

		// verifying header lastname
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actLastname = cip.getActLastName().getText();
		SoftAssert soft_assert = new SoftAssert();
		soft_assert.assertEquals(actLastname, lastname);
		Reporter.log("Lastname verify Successfully", true);

	}

	@Test(groups = "RegressionTest")
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLink().click();

		// to create random num
		int ranNum = jutil.getRandomNumber();

		// to read data from Excel file
		String lastname = eutil.getDataFromExcel("Contact", 4, 2) + ranNum;

		String startDate = jutil.getSystemDateYYYYDDMM();
		String endDate = jutil.getRequiredDateYYYYDDMM(30);

		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.getLastnameEdt().sendKeys(lastname);

		ccp.getStartDateEdt().clear();
		ccp.getStartDateEdt().sendKeys(startDate);
		ccp.getEndDateEdt().clear();
		ccp.getEndDateEdt().sendKeys(endDate);

		ccp.getSavebtn().click();

		// to verify date
		ContactInfoPage cif = new ContactInfoPage(driver);
		String actStartdate = cif.getActStartDate().getText();
		Assert.assertEquals(actStartdate, startDate);
		Reporter.log("Startdate verified successfully", true);

		String actEndDate = cif.getActEndDate().getText();

		Assert.assertEquals(actEndDate, endDate);
		Reporter.log("Enddate verified successfully", true);

	}

	@Test(groups = "RegressionTest")
	public void createContactWithOrganizationTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		// generate random num
		int ranNumber = jutil.getRandomNumber();

		// to read data from Excel file
		String organization = eutil.getDataFromExcel("Contact", 7, 2) + ranNumber;
		String contactLastname = eutil.getDataFromExcel("Contact", 7, 3) + ranNumber;

		// to create organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.getOrgNameEdt().sendKeys(organization);
		cop.getSaveBtn().click();

		// verifying organization header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerinfo = oip.getHeaderMsg().getText();
		boolean status1 = headerinfo.contains(organization);
		Assert.assertEquals(status1, true);
		Reporter.log("Organization header verified successfully", true);

		// navigate to contact module
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactLink().click();

		CreateNewContactPage ccp = new CreateNewContactPage(driver);
		ccp.getLastnameEdt().sendKeys(contactLastname);

		ccp.getContactOrganization().click();

		// Switch to childwindow
		String parentId = driver.getWindowHandle();
		wutil.toSwitchWindowOnUrl(driver, "Accounts");

		ccp.getOrgSearchField().sendKeys(organization);
		ccp.getOrgSearchbtn().click();

		driver.findElement(By.xpath("//a[text()='" + organization + "']")).click();

		driver.switchTo().window(parentId);

		ccp.getSavebtn().click();

		// verifying contactLastname header msg
		ContactInfoPage cip = new ContactInfoPage(driver);	
		String headerInfo = cip.getHeadermsg().getText();
		boolean status2 = headerInfo.contains(contactLastname);
		Assert.assertTrue(status2);
		Reporter.log("ContactLastname header verified successfully", true);

		// to verify actualOrgname
		String actOrgName = cip.getActContactOrganization().getText();
		Assert.assertEquals(actOrgName, actOrgName);
		Reporter.log("actualOrgname verified successfully", true);

	}

}
