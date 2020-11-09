package FacebookTest;

import WebDriver.Test.base;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class chromeFBtest extends base {
	
	@BeforeClass
	public void init()	{
		cd = initializeDriver(CHROME);
	}
	
	@Test(priority=1)
	public void navigate()	{
		sa = new SoftAssert();
		cd.get("https://www.google.com/intl/en-GB/gmail/about/#");
		sa.assertEquals(cd.getCurrentUrl(),"https://www.google.com/intl/en-GB/gmail/about/#");
		sa.assertAll();
	}
	
	@Test(priority=2)
	public void pageTitle()	{
		sa = new SoftAssert();
		sa.assertEquals(cd.getTitle(), "Gmail - Email from Google");
		sa.assertAll();
	}
	
	@Test(priority=3)
	public void LoginTestxpath() throws InterruptedException {
		sa = new SoftAssert();
		w = cd.findElement(By.xpath("//*[@ga-event-action=\"sign in\"]"));
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);",w,"target","_self");
		js.executeScript("arguments[0].click();",w);
		Thread.sleep(2000);
		
		//Code to switch to another tab
		//ArrayList<String> tabs = new ArrayList<String> (cd.getWindowHandles());
		//cd.switchTo().window(tabs.get(1));
		
		sa.assertEquals(cd.getCurrentUrl().contains("https://accounts.google.com/signin"), true);
		sa.assertEquals(cd.getTitle(), "Gmail");
		cd.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys("gidtest71@gmail.com");
		w = cd.findElement(By.xpath("//*[text()=\"Next\"]"));
		js.executeScript("arguments[0].click();",w);
		Thread.sleep(2000);
		
		//w = cd.findElement(By.xpath("//*[text()=\"Couldn't sign you in\"]"));
		w = cd.findElement(By.xpath("//*[text()=\"Couldn't sign you in\"]"));
		sa.assertEquals(w.getText(), "Couldn't sign you in");
		sa.assertAll();
	}
	
	@Test(priority=4)
	public void negLogin1() throws InterruptedException	{
		//Test with a partial email address
		sa = new SoftAssert();
		cd.navigate().back();
		Thread.sleep(2000);
		
		w = cd.findElement(By.xpath("//input[@type=\"email\"]"));
		w.clear();
		w.sendKeys("email@");
		w = cd.findElement(By.xpath("//*[text()=\"Next\"]"));
		js.executeScript("arguments[0].click();",w);
		Thread.sleep(2000);
		
		w = cd.findElement(By.className("o6cuMc"));
		sa.assertEquals(w.getText(), "Enter a valid email or phone number");
		sa.assertAll();
	}
	
	@Test(priority=5)
	public void negLogin2() throws InterruptedException	{
		//Test with a non-existing email address
		sa = new SoftAssert();
		w = cd.findElement(By.xpath("//input[@type=\"email\"]"));
		w.clear();
		w.sendKeys("email@test.net");
		w = cd.findElement(By.xpath("//*[text()=\"Next\"]"));
		js.executeScript("arguments[0].click();",w);
		Thread.sleep(2000);
		
		w = cd.findElement(By.className("o6cuMc"));
		sa.assertEquals(w.getText(), "Couldn't find your Google Account");
		sa.assertAll();
	}
	
	@AfterClass
	public void tearDown()	{
		cd.quit();
	}
}