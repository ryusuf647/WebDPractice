package WebDriver.Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

public class base {
	public static WebDriver cd = null;
	public static JavascriptExecutor js;
	public static final String CHROME = "chrome", FIREFOX = "firefox",
			MICROSOFTEDGE = "microsoftedge";
	public static SoftAssert sa;
	public static WebElement w;
	
	public static WebDriver initializeDriver(String d_args)	{
		switch (d_args) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", 
					"C:\\Selenium\\chromedriver_86.0.4240.22\\chromedriver.exe");
			cd = new ChromeDriver();
			break;
		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", 
					"C:\\Selenium\\geckodriver-v0.27.0\\geckodriver.exe");
			cd = new FirefoxDriver();
			break;
		case MICROSOFTEDGE:
			System.setProperty("webdriver.edge.driver",
					"C:\\Selenium\\msedgedriver_86.0.622.58\\msedgedriver.exe");
			cd = new EdgeDriver();
			break;
		}
		js = (JavascriptExecutor)cd;
		return cd;
	}
}