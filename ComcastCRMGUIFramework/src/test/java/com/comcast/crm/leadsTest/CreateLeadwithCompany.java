package com.comcast.crm.leadsTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelFileUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateLeadwithCompany {

	public static void main(String[] args) throws IOException, InterruptedException {

		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();

		String URL = putil.getDataFromPropertiesFile("url");
		String BROWSER = putil.getDataFromPropertiesFile("browser");
		String USERNAME = putil.getDataFromPropertiesFile("username");
		String PASSWORD = putil.getDataFromPropertiesFile("password");

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		wutil.toMaximize(driver);
		wutil.waitForPageLoad(driver);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();

		// to create random number
		int ranNum = jutil.getRandomNumber();

		// to read data from excel
		String lastName = eutil.getDataFromExcel("Lead", 7, 2) + ranNum;
		String company = eutil.getDataFromExcel("Lead", 7, 3) + ranNum;

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("company")).sendKeys(company);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		// to verify header
		if (headerInfo.contains(lastName)) {
			System.out.println(lastName + " header verified=====pass=====");
		} else {
			System.out.println(lastName + " header not verified=====fail=====");
		}

		// go to leads list view
		driver.findElement(By.linkText("Leads")).click();

		driver.findElement(By.name("search_text")).sendKeys(lastName);

		WebElement leadDropDown = driver.findElement(By.id("bas_searchfield"));
		Select select = new Select(leadDropDown);
		select.selectByValue("lastname");

		driver.findElement(By.xpath("//input[@value=' Search Now ']")).click();

		wutil.visibilityOfElement(driver, driver.findElement(By.linkText(lastName)));

		String actLeadName = driver.findElement(By.linkText(lastName)).getText();

		// to check in result list
		if (actLeadName.equals(lastName)) {
			System.out.println(actLeadName + " is presnt=====pass=====");
		} else {
			System.out.println(actLeadName + " is not present=====fail=====");
		}
		driver.quit();

	}

}
