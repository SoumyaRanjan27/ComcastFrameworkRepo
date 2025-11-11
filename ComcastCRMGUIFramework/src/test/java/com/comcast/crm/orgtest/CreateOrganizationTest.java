package com.comcast.crm.orgtest;

import java.io.IOException;




import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.objectRepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.Listenerutility.ListnerImpClass.class)
public class CreateOrganizationTest extends BaseClass {

	@Test(groups = "SmokeTest")
	public void createOrgnizationTest() throws Throwable {

		// to click on organization
		HomePage hp = new HomePage(driver);

		hp.getOrgLink().click();

		// to click on Organization link
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		// generate random num
		int ranNum = jutil.getRandomNumber();

		// to read data from Excel file
		String organization = eutil.getDataFromExcel("Org", 1, 2) + ranNum;

		// to create organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(organization);

		// verifying header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		boolean orgStatus = headerInfo.contains(organization);
		Assert.assertTrue(orgStatus);
		Reporter.log("Organizaton header info verified successfully",true);

		// verify Actual orgname info

		String actorgname = oip.getActorgName().getText();
        Assert.assertEquals(actorgname,organization);
		Reporter.log("Organizaton info verified successfully",true);

	}

	@Test(groups = "RegressionTest")
	public void createOrgWithIndustryTest() throws Throwable, IOException {

		// to click on organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// to click on Organization Link
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		// generate random num
		int ranNum = jutil.getRandomNumber();

		// to read data from Excel file
		String organization = eutil.getDataFromExcel("Org", 4, 2) + ranNum;
		String industry = eutil.getDataFromExcel("Org", 4, 3);
		String type = eutil.getDataFromExcel("Org", 4, 4);

		// to create organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(organization, industry, type);

		// verify industry and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actindustry = oip.getActIndustryName().getText();
        Assert.assertEquals(actindustry,industry);
        Reporter.log("Industry info verified successfully",true);

		String actType = oip.getActTypeName().getText();
		Assert.assertEquals(actType,type);
        Reporter.log("Type info verified successfully",true);
		

	}

	@Test(groups = "RegressionTest")
	public void createOrgWithPhoneNumberTest() throws EncryptedDocumentException, IOException {
		// to click on org module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// to click on org lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		// generate random num
		int ranNumber = jutil.getRandomNumber();

		// to read data from Excel file
		String organization = eutil.getDataFromExcel("Org", 7, 2) + ranNumber;
		String phoneNum = eutil.getDataFromExcel("Org", 7, 3) + ranNumber;

		// to create organization wiht phonr num

		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(organization, phoneNum);
		cop.getSaveBtn().click();

		// verify phone Number
		OrganizationInfoPage oif = new OrganizationInfoPage(driver);
		String actPhoneNumber = oif.getActPhoneNum().getText();
		Assert.assertEquals(actPhoneNumber,phoneNum);
        Reporter.log("Phone Num info verified successfully",true);
		

	}
}
