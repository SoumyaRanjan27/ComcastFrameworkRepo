 package testNg;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest{

	@DataProvider
	public Object[][] getData() {
		Object[][] objarr = new Object[2][2];
		objarr[0][0] = "Soumya";
		objarr[0][1] = "Ranjan";
		objarr[1][0] = "Ajay";
		objarr[1][1] = "Amar";

		return objarr;
	}

	@Test(dataProvider = "getData")
	public void createContact(String firstname, String lastname) {
		System.out.println("firstname-" + firstname + ", lastname-" + lastname);

	}

}
