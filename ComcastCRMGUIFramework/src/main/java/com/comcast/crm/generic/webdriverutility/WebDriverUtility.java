package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void visibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wb = new WebDriverWait(driver, Duration.ofSeconds(20));
		wb.until(ExpectedConditions.visibilityOf(element));
	}

	public void toSwitchWindowOnTitle(WebDriver driver, String partialtitle) {

		Set<String> allid = driver.getWindowHandles();
		for (String id : allid) {
			String title = driver.switchTo().window(id).getTitle();
			if (title.contains(partialtitle)) {
				break;
			}
		}
	}

	public void toSwitchWindowOnUrl(WebDriver driver, String partiaurl) {

		Set<String> allid = driver.getWindowHandles();
		for (String id : allid) {
			String url= driver.switchTo().window(id).getCurrentUrl();
			if (url.contains(partiaurl)) {
				break;
			}
		}
	}

	public void switchtoFrame(WebDriver driver, int index) {

		driver.switchTo().frame(index);
	}

	public void switchtoFrame(WebDriver driver, String nameID) {

		driver.switchTo().frame(nameID);
	}

	public void switchtoFrame(WebDriver driver, WebElement ele) {

		driver.switchTo().frame(ele);
	}

	public void switchtoAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchtoAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void select(String value, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(value);
	}
	
	public void moveToElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element);
	}
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element);
	}
	public void rightClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element);
	}
}
