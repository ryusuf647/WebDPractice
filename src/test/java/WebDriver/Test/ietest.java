package WebDriver.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ietest extends base {
	
	@BeforeClass
	public void init()	{
		cd = initializeDriver(MICROSOFTEDGE);
	}
	
	@Test
	public void navigate()	{
		sa = new SoftAssert();
		cd.get("https://www.google.ca/");
		sa.assertEquals(cd.getCurrentUrl().contains("google.ca"),true);
		sa.assertAll();
	}
	
	@Test
	public void pageTitle()	{
		sa = new SoftAssert();
		sa.assertEquals(cd.getTitle().contains("Google"),true);
		sa.assertAll();
	}
	
	@AfterClass
	public void tearDown()	{
		cd.quit();
	}
}
