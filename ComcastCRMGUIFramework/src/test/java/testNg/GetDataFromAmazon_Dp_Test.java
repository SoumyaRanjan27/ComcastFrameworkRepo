 package testNg;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelFileUtility;

public class GetDataFromAmazon_Dp_Test {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandname, String prodname) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.amazon.in/");

		// search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname, Keys.ENTER);
		String price = driver.findElement(By.xpath("//span[text()='" + prodname
				+ "']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']"))
				.getText();

		System.out.println(price);
	}

	@DataProvider
	public Object[][] getData() throws Throwable, Throwable {
		ExcelFileUtility eutil = new ExcelFileUtility();
		int rowcount = eutil.getRowCount("Amazon");
		Object[][] objarr = new Object[rowcount][2];
		for (int i = 0; i <= rowcount - 1; i++) {
			objarr[i][0] = eutil.getDataFromExcel("Amazon", i + 1, 0);
			objarr[i][1] = eutil.getDataFromExcel("Amazon", i + 1, 1);

		}
		return objarr;
	}

}
