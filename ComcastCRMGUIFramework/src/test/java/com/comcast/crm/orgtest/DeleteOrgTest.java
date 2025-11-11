package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelFileUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.LoginPage;
import com.comcast.crm.objectRepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable, IOException {

		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// to read data from properties file

		String BROWSER = putil.getDataFromPropertiesFile("browser");
		String URL = putil.getDataFromPropertiesFile("url");
		String USERNAME = putil.getDataFromPropertiesFile("username");
		String PASSWORD = putil.getDataFromPropertiesFile("password");

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		

		LoginPage lp = new LoginPage(driver);
		// using bussiness libraries to login
		lp.LoginToApp(URL,USERNAME, PASSWORD);

		// to click on organization
		HomePage hp = new HomePage(driver);

		hp.getOrgLink().click();

		// to click on Organization link
		OrganizationsPage op = new OrganizationsPage(driver);
		op.getCreateOrgLink().click();

		// generate random num
		int ranNum = jutil.getRandomNumber();

		// to read data from Excel file
		String organization = eutil.getDataFromExcel("Org", 10, 2) + ranNum;

		// to create organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createOrg(organization);

		// verifying header msg
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderMsg().getText();
		if (headerInfo.contains(organization)) {
			System.out.println(organization + " header verified====pass====");
		} else {
			System.out.println(organization + " header not verified====Fail====");
		}

		// go back to organization page

		hp.getOrgLink().click();

		// search for Organization

		op.getSearchEdt().sendKeys(organization);
		wutil.select(op.getSearchDd(), "Organization Name");
		op.getSearchBtn().click();

		// In dynami webTable select & delete org
		driver.findElement(By.xpath("//a[text()='" + organization + "']/ancestor::tr[1]/descendant::a[text()='del']"))
				.click();

		// to logout
		wutil.switchtoAlertAndAccept(driver);
		
		hp.logOut();

		driver.quit();

	}
	}
}
