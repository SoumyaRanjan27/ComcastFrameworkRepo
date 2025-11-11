package com.comcast.crm.baseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseUtility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelFileUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectRepositoryutility.HomePage;
import com.comcast.crm.objectRepositoryutility.LoginPage;

public class BaseClass {

	public DataBaseUtility dutil = new DataBaseUtility();
	public PropertiesFileUtility putil = new PropertiesFileUtility();
	public ExcelFileUtility eutil = new ExcelFileUtility();
	public JavaUtility jutil = new JavaUtility();
	public WebDriverUtility wutil=new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
    
	@BeforeSuite(groups= {"SmokeTest","RegressionTest"})
	public void configBs() throws Throwable {
		System.out.println("connect to DB, Report Config===");
		dutil.getDbConnection();
			
		
	}
     @Parameters("BROWSER")
	@BeforeClass(groups= {"SmokeTest","RegressionTest"})
	public void configBC( @Optional("chrome") String browser) throws IOException {
		System.out.println("==launch the Browser==");
		String BROWSER = browser;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
     } 

	@BeforeMethod(groups= {"SmokeTest","RegressionTest"})
	public void ConfigBM() throws IOException {
		System.out.println("===Login===");
		String URL=putil.getDataFromPropertiesFile("url");
		String USERNAME=putil.getDataFromPropertiesFile("username");
		String PASSWORD=putil.getDataFromPropertiesFile("password");

		LoginPage lp=new LoginPage(driver);
		lp.LoginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups= {"SmokeTest","RegressionTest"})
	public void ConfigAM() {
		System.out.println("==logout===");
		HomePage hp=new HomePage(driver);
		hp.logOut();
	}

	@AfterClass(groups= {"SmokeTest","RegressionTest"})
	public void configAC() {
		System.out.println("===close the browser===");
		driver.quit();
	}

	@AfterSuite(groups= {"SmokeTest","RegressionTest"})
	public void configAs() throws Throwable {
		System.out.println("===close Db, Report backUp===");
		dutil.closeDbconnection();
	}

}
