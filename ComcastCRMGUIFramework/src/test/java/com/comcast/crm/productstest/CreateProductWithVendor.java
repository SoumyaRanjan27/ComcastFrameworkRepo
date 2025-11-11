package com.comcast.crm.productstest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelFileUtility;
import com.comcast.crm.generic.fileutility.PropertiesFileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateProductWithVendor {

	public static void main(String[] args) throws Throwable {

		// to create object of utility
		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil=new JavaUtility();
		WebDriverUtility wutil=new WebDriverUtility();

		// to read data from propertyfile
		String BROWSER = putil.getDataFromPropertiesFile("browser");
		String URL = putil.getDataFromPropertiesFile("url");
		String USERNAME = putil.getDataFromPropertiesFile("username");
		String PASSWORD = putil.getDataFromPropertiesFile("password");

		// to launch browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		// to login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Products")).click();

		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		// to create random num
		int ranNum = jutil.getRandomNumber();

		// to create vendor
		WebElement moreEle = driver.findElement(By.linkText("More"));
		wutil.moveToElement(driver, moreEle);
		
		driver.findElement(By.name("Vendors")).click();

		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();

		String vendorname = eutil.getDataFromExcel("Products", 7, 3);

		driver.findElement(By.name("vendorname")).sendKeys(vendorname);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//to click on products
		driver.findElement(By.linkText("Products")).click();

		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();

		// to create product
		String productname = eutil.getDataFromExcel("Products", 7, 2) + ranNum;

		driver.findElement(By.name("productname")).sendKeys(productname);
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		
		//to perform action in child window
		String parentid = driver.getWindowHandle();
		Set<String> allids = driver.getWindowHandles();
		for(String id:allids) {
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains("Vendors")) {
				break;
			}
		}
		
		driver.findElement(By.id("search_txt")).sendKeys(vendorname);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='Indian Sports']")).click();
		
		driver.switchTo().window(parentid);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify header message
		String headerinfo = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
        if(headerinfo.contains(productname)) {
        	System.out.println(productname+" product verified====pass====");
        }else {
        	System.out.println(productname+" product not verified====fail====");
        }    
        
        //to verify vendor
        String actVendorName = driver.findElement(By.id("mouseArea_Vendor Name")).getText();
        if(actVendorName.trim().equals(vendorname)){
        System.out.println(vendorname+" vendor verified=====pass====");	
        }
        else {
            System.out.println(vendorname+" vendor not verified=====pass====");		
        }
        
        driver.quit();
        
        
        
	}
}
