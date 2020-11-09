package WebDriver.Test;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class chrometest extends base {
	
	@BeforeClass
	public void init()	{
		cd = initializeDriver(CHROME);
	}
	
	@Test(priority=1)
	public void navigate()	{
		sa = new SoftAssert();
		cd.get("https://www.google.ca/");
		sa.assertEquals(cd.getCurrentUrl().contains("google.ca"),true);
		sa.assertAll();
	}
	
	@Test(priority=2)
	public void pageTitle()	{
		sa = new SoftAssert();
		sa.assertEquals(cd.getTitle().contains("Google"),true);
		sa.assertAll();
	}
	
	@Test(priority=3)
	public void clickImages() throws InterruptedException	{
		//Click on Images on Google site by traversing from parent to child
		String xpath = "//div[@class=\"gb_Xa gb_Ig gb_i gb_Hg gb_Jg gb_1f\"]/div/div[2]/a";
		cd.findElement(By.xpath(xpath)).click();
	}
	
	@Test(priority=4)
	public void navigateLinks() throws InterruptedException	{
		sa = new SoftAssert();
		cd.get("http://www.qaclickacademy.com/blog/");
		Thread.sleep(2000);
		String value = "cat-item cat-item-14";
		String xpath = "//*[@class=\"" + value + "\"]";
		String xpathclicked = "//*[@class=\"" + value + " current-cat" + "\"]";
		//navigate to and click on static child
		cd.findElement(By.xpath(xpath+"/a")).click();
		sa.assertEquals(cd.findElement(By.xpath("//*[text()=\"Appium Archive\"]")).getText(), 
				"Appium Archive");
		Thread.sleep(2000);
		//navigate to and click on first sibling
		cd.findElement(By.xpath(xpathclicked + "/following-sibling::li[1]/a")).click();
		sa.assertEquals(cd.findElement(By.xpath("//*[text()=\"Interview Questions Archive\"]")).getText(), 
				"Interview Questions Archive");
		Thread.sleep(2000);
		//navigate to and click on second sibling
		cd.findElement(By.xpath(xpath + "/following-sibling::li[2]/a")).click();
		sa.assertEquals(cd.findElement(By.xpath("//*[text()=\"QAClick Academy Archive\"]")).getText(), 
				"QAClick Academy Archive");
		Thread.sleep(2000);
		//navigate to and click on third sibling
		cd.findElement(By.xpath(xpath + "/following-sibling::li[3]/a")).click();
		sa.assertEquals(cd.findElement(By.xpath("//*[text()=\"Selenium Archive\"]")).getText(), 
				"Selenium Archive");
		Thread.sleep(2000);
		//navigate to parent and output header text
		System.out.println(cd.findElement(By.xpath(xpath + "/parent::ul/parent::li/h3")).getText());
		sa.assertAll();
	}
	
	@AfterClass
	public void tearDown()	{
		cd.quit();
	}
}