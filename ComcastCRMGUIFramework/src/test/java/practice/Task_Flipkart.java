package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Task_Flipkart {

	@Test
	public void Flipkart() throws EncryptedDocumentException, IOException {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get("https://www.flipkart.com/");
		
		FileInputStream fis=new FileInputStream("./testData/testScriptData.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String mobiles = wb.getSheet("Flipkart").getRow(2).getCell(0).toString();
		
		System.out.println(mobiles);
		
		
		
	}
}
