package SelTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import WebDriver.Test.base;

public class SelTest1 extends base {
	Select i;
	
	@BeforeClass
	public void initMethod() throws InterruptedException	{
		cd = initializeDriver(CHROME);
		cd.get("https://rahulshettyacademy.com/dropdownsPractise/#");
		Thread.sleep(3000);
	}
	
	@Test(priority=2)
	public void fromCity() throws InterruptedException	{
		sa = new SoftAssert();
		w = cd.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
		w.click();
		Thread.sleep(3000);
		cd.findElement(By.xpath("//a[@value=\"DEL\"]")).click();
		Thread.sleep(3000);
		sa.assertEquals(w.getAttribute("value"), "Delhi (DEL)");
		sa.assertAll();
	}
	
	@Test(priority=3)
	public void toCity() throws InterruptedException	{
		sa = new SoftAssert();
		w = cd.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
		cd.findElement(By.xpath("(//a[@value=\"IXZ\"])[2]")).click();
		Thread.sleep(3000);
		sa.assertEquals(w.getAttribute("value"), "Port Blair (IXZ)");
		sa.assertAll();
	}
	
	@AfterClass
	public void tearDown()	{
		cd.quit();
	}
}